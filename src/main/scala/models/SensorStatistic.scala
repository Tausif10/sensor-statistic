package models

case class SensorStatistic(sensorId: String, minHumidity: Option[Int], avgHumidity:Option[Double] ,maxHumidity: Option[Int])