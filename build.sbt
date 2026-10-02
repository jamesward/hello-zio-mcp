scalaVersion := "3.9.0"

name := "hello-zio-mcp"

libraryDependencies += "com.jamesward" %% "zio-http-mcp" % "0.8.2"

// sbt-mcp (loopback-only: its tools can execute build tasks)
Global / mcpEnabled := true
Global / mcpHost := "127.0.0.1"
Global / mcpPort := 5104

// SkillsJars: extract agent Skills with `./sbt extractSkillsJars`
skillsJarsOutputDir := Some(file(".kiro/skills"))

libraryDependencies += "com.jamesward" % "skills" % "0.0.10" % Skills
