name := """base-play-application"""
organization := "com.dreigada"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"


libraryDependencies ++= List(
  Dependencies.slick,
  Dependencies.playSlick,
  Dependencies.playSlickEvolutions,
  Dependencies.slickCodeGen,
  Dependencies.sqliteDriver,
  evolutions,
  jdbc
)


// code generation task
lazy val generateTables = TaskKey[Seq[File]]("gen-tables")
generateTables := {
  val outputDir = (root.base / "app").getPath
  val cp = (dependencyClasspath in Compile).value
  val r = (runner in Compile).value
  val s = streams.value

  val url = "jdbc:sqlite:testDB"
  val jdbcDriver = "org.sqlite.JDBC"
  val slickDriver = "slick.driver.SQLiteDriver"
  val pkg = "queries"

  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), s.log))
  val fname = outputDir + "/demo/Tables.scala"
  Seq(file(fname))
}
