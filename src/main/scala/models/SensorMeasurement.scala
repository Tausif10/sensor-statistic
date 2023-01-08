package models

case class SensorMeasurement(sensorId: String, humidity: Option[Int])

object SensorMeasurement {
  implicit val ordering = Ordering.fromLessThan[SensorMeasurement]((first, second) =>
    first.humidity.getOrElse(0) < second.humidity.getOrElse(0))
}


