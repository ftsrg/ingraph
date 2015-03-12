name := "project-work"

version := "1.0"

scalaVersion := "2.11.6"

libraryDependencies +=  "com.typesafe.akka" % "akka-actor_2.11" % "2.4-SNAPSHOT"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"