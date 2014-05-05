val VERSION = "0.1-SNAPSHOT"

val SCALA_VERSION = "2.10.4"

val ORGANIZATION = "jp.seraphr.bot"

val COMMON_DEPENDENCIES = Seq(
    "org.slf4j" % "slf4j-api" % "[1.7.0,)",
    "org.scalatest" %% "scalatest" % "2.0" % "test",
    "org.scalacheck" %% "scalacheck" % "1.10.1" % "test"
)

val COMMON_SETTINGS = Seq(
  organization := ORGANIZATION,
  version := VERSION,
  scalaVersion := SCALA_VERSION,
  testOptions in ScctTest += Tests.Argument("-oS", "-u", "target/junit"),
  EclipseKeys.withSource := true,
  EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource,
  libraryDependencies ++= COMMON_DEPENDENCIES
) ++ ScctPlugin.instrumentSettings

// root project
lazy val root = Project(
  id = "root",
  base = file("."),
  settings = Defaults.defaultSettings ++ Seq(name := "root") ++ ScctPlugin.mergeReportSettings
) aggregate (core)

// sub projects
lazy val core = Project(
  id = "bot-core",
  base = file("core"),
  settings = Defaults.defaultSettings ++ COMMON_SETTINGS ++ Seq(
    name := "bot-core"
  )
)
