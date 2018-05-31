import sbt._

object Dependencies {
  lazy val slick =   "com.typesafe.slick" %% "slick" % "3.2.0"
  lazy val playSlick = "com.typesafe.play" %% "play-slick" % "3.0.0"
  lazy val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % "3.0.0"
  lazy val slickCodeGen = "com.typesafe.slick" %% "slick-codegen" % "3.2.0"

  lazy val sqliteDriver = "org.xerial" % "sqlite-jdbc" % "3.23.1"
}
