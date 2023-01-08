import models.NotADirectory
import org.scalatest.wordspec.AnyWordSpecLike
import readers.CsvFileReader

import scala.util.{Failure, Success}

class CsvFileReaderTest extends AnyWordSpecLike {

  "CsvFileReader" should {

    "return map of file name and lines other than header in file  when directory contains the csv files" in {
      val path = "src/test/resources/"
      val csvFileReader = new CsvFileReader()
      val actualResult = csvFileReader.readFromDirectory(path)
      assert(actualResult == Success(Map("test.csv" -> List("s1, 10"), "test2.csv" -> List())))
    }

    "return error message when path is file path" in {
      val path = "src/test/resource/test.csv"
      val csvFileReader = new CsvFileReader()
      val actualResult = csvFileReader.readFromDirectory(path)
      assert(actualResult == Failure(NotADirectory("src/test/resource/test.csv is not a directory")))
    }

    "return error message when directory path is invalid" in {
      val path = "src/test/resource/invalid"
      val csvFileReader = new CsvFileReader()
      val actualResult = csvFileReader.readFromDirectory(path)
      assert(actualResult == Failure(NotADirectory("src/test/resource/invalid is not a directory")))
    }
  }

}
