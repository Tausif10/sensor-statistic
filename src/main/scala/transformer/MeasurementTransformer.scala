package transformer

import models.SensorMeasurement

import scala.util.Try

class MeasurementTransformer {

  def transform(records: List[String]): List[SensorMeasurement] = {

    def splitStringByCommas(str: String): List[String] = {
      str.split(",").toList
    }

    def extractSensorMeasurement(measurement: List[String]): SensorMeasurement = {
      SensorMeasurement(measurement.head.trim, Try(measurement.last.trim.replace(",", "").toInt).toOption)
    }

    records.collect {
      case line if splitStringByCommas(line).size == 2 => extractSensorMeasurement(splitStringByCommas(line))
    }
  }
}
