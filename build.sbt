name := """monocle-101"""

version := "1.0"

scalaVersion := "2.11.7"

val monocleVersion = "1.2.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.4" % "test"
libraryDependencies += "com.github.julien-truffaut" %% "monocle-core" % monocleVersion
libraryDependencies += "com.github.julien-truffaut" %% "monocle-macro" % monocleVersion


