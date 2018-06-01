package di

import services.ExampleDAO
import controllers.HomeController
import play.api.ApplicationLoader.Context
import play.api.db.DBApi
import play.api.db.evolutions.EvolutionsComponents
import play.api.db.slick.{DbName, SlickComponents, DatabaseConfigProvider}
import play.api.db.slick.evolutions.SlickDBApi
import play.api.mvc.EssentialFilter
import play.api.routing.Router
import play.api.BuiltInComponentsFromContext
import slick.basic.{BasicProfile, DatabaseConfig}


class ApplicationComponents (context: Context)
  extends BuiltInComponentsFromContext(context)
    with controllers.AssetsComponents
    with EvolutionsComponents
    with SlickComponents{

  lazy val databaseConfigProvider: DatabaseConfigProvider = new DatabaseConfigProvider {
    def get[P <: BasicProfile]: DatabaseConfig[P] = slickApi.dbConfig[P](DbName("default"))
  }
  lazy val homeController = new HomeController(new ExampleDAO(databaseConfigProvider), controllerComponents)
  override def router: Router = new _root_.router.Routes(httpErrorHandler, homeController, assets)
  override lazy val dbApi: DBApi = SlickDBApi(slickApi)
  override lazy val httpFilters: Seq[EssentialFilter] = Seq.empty

  applicationEvolutions // force evolutions
}