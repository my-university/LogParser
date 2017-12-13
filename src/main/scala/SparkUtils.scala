import org.apache.spark.sql.{Dataset, SparkSession}

object SparkUtils {


  def createSparkSession : SparkSession = {

    val session = SparkSession
      .builder
      .master("local")
      .appName("SparkLogAnalyzer")
      .getOrCreate()

    session
  }

  def readFileAsDataSet(sparkSession: SparkSession, path:String) : Dataset[String] = {
    sparkSession.read.textFile("logs/log-001.log")
  }

}
