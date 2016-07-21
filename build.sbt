name := "Spell Checker"

version := "1.0.0"

scalaVersion := "2.11.7"

mainClass in (Compile, packageBin) := Some("com.yazquez.spellChecker.App")
mainClass in (Compile, run) := Some("com.yazquez.spellChecker.App")

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.0-M3"

assemblyJarName in assembly := s"${name.value.replace(' ','-')}-${version.value}.jar"
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala=false)
