scalaVersion := "3.10.1"

name := "hello-zio-mcp"

libraryDependencies += "com.jamesward" %% "zio-http-mcp" % "0.13.4"

// SkillsJars: extract agent Skills with `./sbta extractSkillsJars`
skillsJarsOutputDir := Some(file(".kiro/skills"))

libraryDependencies += "com.jamesward" % "skills" % "0.0.27" % Skills

scalacOptions ++= Seq(
  "-language:strictEquality",
  "-deprecation",
  "-Werror",
)
