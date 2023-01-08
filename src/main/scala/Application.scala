import readers.CsvFileReader
import transformer.MeasurementTransformer

import scala.io.StdIn
import scala.util.{Failure, Success}

object Application extends App {

  val directoryPath = StdIn.readLine("enter directory path: ")
  private val inputDirectoryReader = new CsvFileReader()
  private val measurementTransformer = new MeasurementTransformer()
  private val statisticCalculator = new StatisticsCalculator()
  val dashboardGenerator = new DashboardGenerator(inputDirectoryReader, measurementTransformer, statisticCalculator)

  dashboardGenerator.generate(directoryPath) match {
    case Success(sensorDashboard) =>
      val outputFormat =
        s"""Num of processed files: ${sensorDashboard.numOfProcessedFile}
           |Num of processed measurements: ${sensorDashboard.numOfProcessedMeasurement}
           |Num of failed measurements: ${sensorDashboard.numOfFailedMeasurements}
           |
           |Sensors with highest avg humidity:
           |
           |sensor-id,min,avg,max
           |${sensorDashboard.sensorMeasurements.map(sensorMeasurement => s"${sensorMeasurement.sensorId}, ${sensorMeasurement.minHumidity.getOrElse("NaN")}, ${sensorMeasurement.avgHumidity.getOrElse("NaN")}, ${sensorMeasurement.maxHumidity.getOrElse("NaN")}").mkString("\n")}
           |""".stripMargin
      println(outputFormat)
    case Failure(exception) =>
      println(s"fail to get the statistic. error: $exception")
  }

}
