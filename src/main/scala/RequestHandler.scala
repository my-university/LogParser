import spray.json.{DefaultJsonProtocol, RootJsonFormat}

object RequestHandler extends DefaultJsonProtocol{

  implicit val searchKeyword: RootJsonFormat[SearchKeyWord] = jsonFormat1(SearchKeyWord)

  implicit val searchResults : RootJsonFormat[SearchResults] = jsonFormat2(SearchResults)

}
