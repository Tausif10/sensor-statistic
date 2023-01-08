import models.{SensorDashBoard, SensorStatistic}
import readers.InputDirectoryReader
import transformer.MeasurementTransformer

import scala.util.Try

class DashboardGenerator(inputDirectoryReader: InputDirectoryReader, measurementTransformer: MeasurementTransformer, statisticsCalculator: StatisticsCalculator) {

  def generate(directoryPath: String): Try[SensorDashBoard] = {
    val fileNameAndFileContentMapping = inputDirectoryReader.readFromDirectory(directoryPath)

   val sensorMeasurements = fileNameAndFileContentMapping
     .map(_.values.toList.flatten)
     .map(measurementTransformer.transform)


    val sensorStatistics = sensorMeasurements.map(statisticsCalculator.calculate)

    for {
      stat <- sensorStatistics
      measurements <- sensorMeasurements
      fileAndContentMapping <- fileNameAndFileContentMapping
    } yield {
      SensorDashBoard(fileAndContentMapping.keySet.size, measurements.size, measurements.filter(_.humidity.isEmpty).size, stat.sortBy(_.avgHumidity).reverse)
    }
  }

}
