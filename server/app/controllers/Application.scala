package controllers

import javax.inject._

import play.api.mvc._
import models._
import play.api.libs.json._
import play.api.i18n._
import models.UserData

@Singleton
class Application @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  
  implicit val userDataReads:Reads[UserData] = Json.reads[UserData]
  implicit val oneResourceReads:Reads[OneResource] = Json.reads[OneResource]

  def withJsonBody[A](f: A => Result)(implicit request: Request[AnyContent], reads: Reads[A]) = {
        request.body.asJson.map { body =>
            Json.fromJson[A](body) match {
                case JsSuccess(a, path) => f(a)
                case e @ JsError(_) => Redirect(routes.Application.load)
            }
        }.getOrElse(Redirect(routes.Application.load))
    }

  def index = Action { implicit request =>
    Ok(views.html.jsonMain())
  }

  def load = Action { implicit request =>
    Ok(views.html.jsonMain())
  }

  def validate = Action { implicit request =>
    println("validate in Application")
    withJsonBody[UserData] { ud =>
            if(InMemoryModel.validateUser(ud.username, ud.password)) {
                Ok(Json.toJson(true))
                    .withSession("username" -> ud.username, "csrfToken" -> play.filters.csrf.CSRF.getToken.get.value)
            } else {
                Ok(Json.toJson(false))
            }
        }  
  }

  def openCategory = Action { implicit request =>
      withJsonBody[String] { category =>
        val resources = InMemoryModel.getResources(category)
        Ok(Json.toJson(resources))
      }
  }

  def schedule = Action { implicit request =>
      withJsonBody[OneResource] { data =>
        val resource = InMemoryModel.getOneResource(data.category, data.resourceName)
        Ok(Json.toJson(resource))
      }
  }

}

