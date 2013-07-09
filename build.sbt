organization := "com.unrulymedia"

name := "property-based-testing"

version := "0.1-SNAPSHOT"

scalaVersion := "2.10.1"

resolvers ++= Seq(
  "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots",
  "Sonatype Releases" at "http://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
   "org.specs2" %% "specs2" % "2.0" % "test",
   "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
)
