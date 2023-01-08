import models.{SensorMeasurement, SensorStatistic}

import scala.annotation.tailrec

class StatisticsCalculator() {
  def calculate(sensorMeasurements: List[SensorMeasurement]): List[SensorStatistic] = {
    sensorMeasurements
        .groupBy(measurement => measurement.sensorId).map {
            case (senorId, sensorMeasurements) if sensorMeasurements.exists(_.humidity.isEmpty) && sensorMeasurements.filter(_.humidity.nonEmpty).nonEmpty =>
              SensorStatistic(senorId, sensorMeasurements.filter(_.humidity.nonEmpty).min.humidity, avgHumidity(sensorMeasurements), sensorMeasurements.filter(_.humidity.nonEmpty).max.humidity)
            case (sensorId, sensorMeasurements) =>
              SensorStatistic(sensorId, sensorMeasurements.min.humidity, avgHumidity(sensorMeasurements), sensorMeasurements.max.humidity)
      }.toList
  }

  private def avgHumidity(sensorMeasurements: List[SensorMeasurement]): Option[Double] = {

    @tailrec
    def sumOfHumidity(sensorMeasurements: List[SensorMeasurement], sum: Double = 0): Double = {
      sensorMeasurements match {
        case head :: tail if tail.nonEmpty => sumOfHumidity(sensorMeasurements.tail, head.humidity.getOrElse(0) + sum)
        case head :: Nil => head.humidity.getOrElse(0) + sum
      }
    }


    if (sensorMeasurements.forall(_.humidity.isEmpty)) {
      None
    } else {
      val validMeasurements = sensorMeasurements.filter(_.humidity.isDefined)
      val avg = sumOfHumidity(validMeasurements) / validMeasurements.size
      Some(math.ceil(avg * 100) / 100 )
    }
  }
}
