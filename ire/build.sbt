name := "incqueryd-core"

organization := "hu.bme.mit"

version := "1.0"

scalaVersion := "2.11.6"

javaOptions += s"-Dcurrent.datetime=${
  import java.time.format.DateTimeFormatter
  java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-mm_ss"))
}"

showSuccess := false

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"

val kamonVersion = "0.3.4"

libraryDependencies ++= Seq( 
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.w3" % "banana-rdf_2.11" % "0.8.1",
  "org.w3" % "banana-jena_2.11" % "0.8.1",
  "com.twitter" % "chill_2.11" % "0.5.2",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4-SNAPSHOT",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4-SNAPSHOT",
  "io.kamon" %% "kamon-core" % kamonVersion,
  "io.kamon" %% "kamon-statsd" % kamonVersion,
  "io.kamon" %% "kamon-log-reporter" % kamonVersion,
  "io.kamon" %% "kamon-system-metrics" % kamonVersion,
  "org.aspectj" % "aspectjweaver" % "1.8.1"
)

aspectjSettings

javaOptions <++= AspectjKeys.weaverOptions in Aspectj


fork in run := true