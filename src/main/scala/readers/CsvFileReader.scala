package readers

import models.NotADirectory

import java.io.File
import scala.io.Source
import scala.util.Try

class CsvFileReader extends InputDirectoryReader {
  override def readFromDirectory(fromPath: String): Try[Map[String, List[String]]] = {
    Try {
      val file = new File(fromPath)
      if (file.isDirectory) {
        val files = file.listFiles(x => x.getName.endsWith(".csv")).toList
        files.foldLeft(Map.empty[String, List[String]])((mapOfFileNameAndFileContent, file) =>
          mapOfFileNameAndFileContent + (file.getName -> Source.fromFile(file).getLines().drop(1).toList))
      } else {
        throw NotADirectory(s"$fromPath is not a directory")
      }
    }
  }

  def excludeHeaderLine(records: List[String], header: String): List[String] = {
    records.drop(1)
  }

}

