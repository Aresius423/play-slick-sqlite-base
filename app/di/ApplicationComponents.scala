package di

import controllers.HomeController
import play.api.ApplicationLoader.Context
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.SlickComponents
import play.api.db.slick.evolutions.SlickEvolutionsComponents
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{BuiltInComponentsFromContext, Configuration}

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
//    with AssetsComponents
    with EvolutionsComponents
    with SlickComponents
    with SlickEvolutionsComponents
    with ControllerComponents
    with RulesComponents {

  private lazy val assets = new controllers.Assets(httpErrorHandler)

  override def router: Router = new _root_.router.Routes(httpErrorHandler, homeController, assets)

  override lazy val httpFilters: Seq[EssentialFilter] = Seq.empty

  applicationEvolutions // force evolutions
}


trait ControllerComponents {
  self: RulesComponents =>

  lazy val homeController = new HomeController()
}

trait RulesComponents {
  def configuration: Configuration

}