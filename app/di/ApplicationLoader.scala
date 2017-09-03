package di

import play.api.ApplicationLoader.Context
import play.api.{Application, ApplicationLoader => PlayApplicationLoader}

class ApplicationLoader extends PlayApplicationLoader {
  override def load(context: Context): Application = {
    val components = new ApplicationComponents(context)

    components.application
  }
}
