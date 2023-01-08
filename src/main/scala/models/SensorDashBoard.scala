package models

case class SensorDashBoard(numOfProcessedFile: Int, numOfProcessedMeasurement: Int, numOfFailedMeasurements: Int, sensorMeasurements: List[SensorStatistic])
