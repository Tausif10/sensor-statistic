package transformer

import models.SensorMeasurement
import org.scalatest.wordspec.AnyWordSpecLike

class MeasurementTransformerTest extends AnyWordSpecLike {

  private val measurementTransformer = new MeasurementTransformer()

  "MeasurementTransformer" should {

    "transform string to sensor measurement when list contains only 2 entries separated by comma" in {
      val actualResult = measurementTransformer.transform(List("s1, 10", "s2, 30"))
      assert(actualResult == List(SensorMeasurement("s1", Some(10)), SensorMeasurement("s2", Some(30))))
    }

    "return empty list of transformed sensor measurement when list contains more than 2 entries separated by comma on each line" in {
      val actualResult = measurementTransformer.transform(List("s1, 12, 10", "s2, 90, 30"))
      assert(actualResult == List.empty[SensorMeasurement])
    }

    "return empty humidity for sensor measurement when list contains 2 entries separated by comma on each line but number is separated by space" in {
      val actualResult = measurementTransformer.transform(List("s1, 12 10", "s2, 90, 30"))
      assert(actualResult == List(SensorMeasurement("s1", None)))
    }

    "return empty humidity for sensor measurement when list contains 2 entries separated by comma on each line but humidity value is not a number" in {
      val actualResult = measurementTransformer.transform(List("s1, NaN", "s2, 90, 30"))
      assert(actualResult == List(SensorMeasurement("s1", None)))
    }
  }
}
