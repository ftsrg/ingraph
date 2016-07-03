name := "incquery-ds"

organization := "hu.bme.mit"

version := "1.0"

scalaVersion := "2.11.7"

javaOptions += "-Xmx4G"

javaOptions += "-Xms4G"

showSuccess := false

libraryDependencies ++= Seq( 
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.twitter" % "chill_2.11" % "0.5.2",
  "com.typesafe.akka" % "akka-actor_2.11" % "2.4.0",
  "com.typesafe.akka" %% "akka-remote" % "2.4.0",
  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4.0" % "test",
  "com.goldmansachs" % "gs-collections" % "7.0.1",
  "com.goldmansachs" % "gs-collections-api" % "7.0.1",
  "com.goldmansachs" % "gs-collections-forkjoin" % "7.0.1",
  "com.goldmansachs" % "gs-collections-testutils" % "7.0.1",
  "org.yaml" % "snakeyaml" % "1.17",
  "com.twitter" % "util-eval_2.11" % "6.34.0"
)

fork := true

