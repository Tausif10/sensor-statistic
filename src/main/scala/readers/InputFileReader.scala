package readers

import scala.util.Try

trait InputDirectoryReader {

  def readFromDirectory(fromPath: String): Try[Map[String,List[String]]]
}
