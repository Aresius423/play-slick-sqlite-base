package controllers

import services._
import javax.inject._
import play.api.mvc._
import scala.concurrent.ExecutionContext.Implicits.global

@Singleton
class HomeController @Inject()(db: ExampleDAO, cc: ControllerComponents)
    extends AbstractController(cc) {

  def index() = Action.async { implicit request: Request[AnyContent] =>
    {
      val res = db.getAll
      res.map(result => Ok(views.html.multipleResults(result)))
    }
  }

  def insertNew() = Action.async { implicit request: Request[AnyContent] =>
    {
      val res = db.addNew
      res.map(_ => Redirect("/"))
    }
  }

}
