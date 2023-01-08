import models.{SensorDashBoard, SensorMeasurement, SensorStatistic}
import org.mockito.Mockito.when
import org.scalatest.wordspec.AnyWordSpecLike
import org.scalatestplus.mockito.MockitoSugar.mock
import readers.{CsvFileReader, InputDirectoryReader}
import transformer.MeasurementTransformer

import java.io.FileNotFoundException
import scala.util.{Failure, Success}

class DashboardGeneratorTest extends AnyWordSpecLike {

  val mockCsvFileReader = mock[InputDirectoryReader]
  val mockMeasurementTransformer = mock[MeasurementTransformer]
  val mockStatisticsCalculator = mock[StatisticsCalculator]


  "DashboardGenerator" should {

    "return sensor statistic" in {
      val dashboardGenerator = new DashboardGenerator(mockCsvFileReader, mockMeasurementTransformer, mockStatisticsCalculator)
      when(mockCsvFileReader.readFromDirectory("src/main/scala/db/resources")).thenReturn(Success(Map("file1" -> List("s1,10"))))
      when(mockMeasurementTransformer.transform( List("s1,10"))).thenReturn(List(SensorMeasurement("s1", Some(10))))
      when(mockStatisticsCalculator.calculate(List(SensorMeasurement("s1", Some(10))))).thenReturn(List(SensorStatistic("s1", Some(10), Some(10), Some(10))))

      val actualResult = dashboardGenerator.generate("src/main/scala/db/resources")
      assert(actualResult == Success(SensorDashBoard(1, 1, 0, List(SensorStatistic("s1", Some(10), Some(10.0), Some(10))))))
    }

    "return exception when fail to read input file" in {
      val dashboardGenerator = new DashboardGenerator(mockCsvFileReader, mockMeasurementTransformer, mockStatisticsCalculator)
      val exception = new FileNotFoundException()
      when(mockCsvFileReader.readFromDirectory("src/main/scala/db/resources")).thenReturn(Failure(exception))

      val actualResult = dashboardGenerator.generate("src/main/scala/db/resources")
      assert(actualResult == Failure(exception))
    }
  }

}
