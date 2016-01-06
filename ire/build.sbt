name := "incquery-ds"

organization := "hu.bme.mit"

version := "1.0"

scalaVersion := "2.11.7"

javaOptions += "-Xmx4G"

javaOptions += "-Xms4G"

showSuccess := false

libraryDependencies ++= Seq( 
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "org.w3" % "banana-rdf_2.11" % "0.8.1",
  "org.w3" % "banana-jena_2.11" % "0.8.1",
  "com.twitter" % "chill_2.11" % "0.5.2",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.0",
  "com.typesafe.akka" %% "akka-remote" % "2.4.0",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.0" % "test",
  "com.google.protobuf" % "protobuf-java" % "2.4.1",
  "com.goldmansachs" % "gs-collections" % "7.0.1",
  "com.goldmansachs" % "gs-collections-api" % "7.0.1",
  "com.goldmansachs" % "gs-collections-forkjoin" % "7.0.1",
  "com.goldmansachs" % "gs-collections-testutils" % "7.0.1"
)

fork := true

