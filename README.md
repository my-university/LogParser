# LogParser
A Simple log parser built on top of Apache Spark and uses Akka HTTP for REST API

Steps for Usage
- Mention the log file path that you have to analyze in `conf/application.conf`
- Run the `Runner.scala` as you would run a normal Scala/Java main class
- A Rest API endpoint would become available at `http://localhost:8080/search`
- It has to be provided with the search keyword as below.

```

{
	"input":"Exception"
}

```
It would return the entire log message containing that text and as well as the number of messages count.

This is just a simple demo to understand how Apache Spark and Akka can be combined to work together. 
