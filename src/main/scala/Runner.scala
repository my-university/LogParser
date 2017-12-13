import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.server.Directives.{complete, path, _}
import akka.stream.ActorMaterializer
import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.sql.SparkSession


object Runner extends App {


  implicit val actorSystem = ActorSystem("system")
  implicit val actorMaterializer = ActorMaterializer()
  val sparkSession : SparkSession = SparkUtils.createSparkSession
  val conf : Config  = ConfigFactory.load()
  val logFilePath = conf.getString("logFilePath")

  val file = sparkSession.read.textFile(logFilePath)


  import RequestHandler._

  val route =
    path("search") {
      post {
        entity(as[SearchKeyWord]) {
          keyword =>
          complete {
            searchTextInLogs(keyword.keyword)
          }
        }
      }
    }


  def searchTextInLogs(input:String) : SearchResults = {

    import sparkSession.implicits._

    val df  = file.filter(f => f.contains(input))

    val resultsList : List[String] = df.select("value").map(r => r.getString(0)).collect.toList

    val results = SearchResults(resultsList,df.count())

    results
  }

  Http().bindAndHandle(route,"localhost",8080)



}
