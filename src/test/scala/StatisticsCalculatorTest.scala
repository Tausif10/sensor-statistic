import models.{SensorMeasurement, SensorStatistic}
import org.scalatest.wordspec.AnyWordSpecLike

class StatisticsCalculatorTest extends AnyWordSpecLike {

  val statisticsCalculator = new StatisticsCalculator()

  "StatisticCalculator" should {

    "calculate min, max and avg humidity when humidity value is a number" in {
      val actualResult = statisticsCalculator.calculate(List(SensorMeasurement("s1", Some(10)), SensorMeasurement("s1", Some(20)), SensorMeasurement("s2", Some(20))))
      assert(actualResult == List(SensorStatistic("s2", Some(20), Some(20.0), Some(20)), SensorStatistic("s1", Some(10), Some(15.0), Some(20))))
    }

    "calculate min, max and avg humidity when humidity value is not a number" in {
      val actualResult = statisticsCalculator.calculate(List(SensorMeasurement("s1", None), SensorMeasurement("s1", None), SensorMeasurement("s2", Some(20))))
      assert(actualResult == List(SensorStatistic("s2", Some(20), Some(20.0), Some(20)), SensorStatistic("s1", None, None, None)))
    }

    "calculate min, max and avg humidity when records are in different file and some of humidity value is not a number" in {
      val actualResult = statisticsCalculator.calculate(List(SensorMeasurement("s1", Some(12)), SensorMeasurement("s1", None), SensorMeasurement("s1", Some(20))))
      assert(actualResult == List(SensorStatistic("s1", Some(12), Some(16.0), Some(20))))
    }

    "calculate min, max and avg humidity when records are in same file and humidity value is a number" in {
      val actualResult = statisticsCalculator.calculate(List(SensorMeasurement("s1", Some(20)), SensorMeasurement("s1", Some(25))))
      assert(actualResult == List(SensorStatistic("s1", Some(20), Some(22.5), Some(25))))
    }
  }

}
