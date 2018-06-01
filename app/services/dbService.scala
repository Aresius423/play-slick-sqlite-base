package services

import javax.inject._
import slick.jdbc.H2Profile.api._
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile
import scala.concurrent.Future
import DataStructures._

class ExampleDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
	class exampleTable(tag:Tag) extends Table[ExampleData](tag, "EXAMPLE"){
		def prop1 = column[String]("prop1")
		def prop2 = column[Long]("prop2")
		def prop3   = column[String]("prop3")

		def * = (prop1, prop2, prop3) <> ((ExampleData.apply _).tupled, ExampleData.unapply)
	}
	
	lazy val Tdata = TableQuery[exampleTable]
	
	db.run(Tdata.schema.create)
	
	def getAll:Future[Seq[ExampleData]] = {
		db.run(Tdata.result)
	}
	
	def addNew: Future[Option[Int]] = {
		play.Logger.debug("hey")
		val data = Seq(ExampleData("Hello", scala.util.Random.nextInt, "World!"))
		val insertAction = Tdata ++= data
		db.run(insertAction)
	}
	
	def getID(ID:Long) = {
		Tdata.filter(_.prop2 === ID)
	}
}