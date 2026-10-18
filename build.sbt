scalaVersion := "3.10.0"

name := "hello-zio-mcp"

libraryDependencies += "com.jamesward" %% "zio-http-mcp" % "0.9.0"

// SkillsJars: extract agent Skills with `./sbta extractSkillsJars`
skillsJarsOutputDir := Some(file(".kiro/skills"))

libraryDependencies += "com.jamesward" % "skills" % "0.0.15" % Skills

scalacOptions ++= Seq(
  "-language:strictEquality",
  "-deprecation",
  "-Werror",
)
