package di

import controllers.HomeController
import play.api.ApplicationLoader.Context
import play.api.db.DBApi
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.{SlickComponents, SlickApi}
import play.api.db.slick.evolutions.{SlickEvolutionsComponents, SlickDBApi}
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.{BuiltInComponentsFromContext, Configuration}
import play.controllers.AssetsComponents

class ApplicationComponents(context: Context)
  extends BuiltInComponentsFromContext(context)
    with controllers.AssetsComponents
    with EvolutionsComponents
    with SlickComponents{

  lazy val homeController = new HomeController(controllerComponents)
  override def router: Router = new _root_.router.Routes(httpErrorHandler, homeController, assets)
  override lazy val dbApi: DBApi = SlickDBApi(slickApi)
  override lazy val httpFilters: Seq[EssentialFilter] = Seq.empty

  applicationEvolutions // force evolutions
}