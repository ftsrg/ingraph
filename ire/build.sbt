name := "project-work"

version := "1.0"

scalaVersion := "2.11.6"

javaOptions += "-Xmx6G -Xms6g"

showSuccess := false

logLevel in runMain := Level.Warn

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"

libraryDependencies +=  "com.typesafe.akka" % "akka-actor_2.11" % "2.4-SNAPSHOT"

libraryDependencies +=  "com.typesafe.akka" % "akka-testkit_2.11" % "2.4-SNAPSHOT"

libraryDependencies += "org.w3" % "banana-rdf_2.11" % "0.8.1"

libraryDependencies += "org.w3" % "banana-jena_2.11" % "0.8.1"

libraryDependencies += "com.twitter" % "chill_2.11" % "0.5.2"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
