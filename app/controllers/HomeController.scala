package controllers

import play.api.mvc._

class HomeController() extends Controller {

  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index())
  }

}
