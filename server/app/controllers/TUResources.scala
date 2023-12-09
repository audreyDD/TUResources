package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.i18n._
import play.api.data.Forms._
import akka.http.javadsl.model.ws.Message

import models.DataModel
import models._
import play.api.db.slick.DatabaseConfigProvider
import scala.concurrent.ExecutionContext
import play.api.db.slick.HasDatabaseConfigProvider
import slick.jdbc.JdbcProfile
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future


@Singleton
class TUResources @Inject()(protected val dbConfigProvider: DatabaseConfigProvider, cc: ControllerComponents)(implicit ec: ExecutionContext) 
    extends AbstractController(cc) with HasDatabaseConfigProvider[JdbcProfile] {

    private val model = new DataModel(db)

    implicit val userDataReads = Json.reads[UserData]
    implicit val oneResourceReads = Json.reads[OneResource]

    def withJsonBody[A](f: A => Result)(implicit request: Request[AnyContent], reads: Reads[A]) = {
        request.body.asJson.map { body =>
            Json.fromJson[A](body) match {
                case JsSuccess(a, path) => f(a)
                case e @ JsError(_) => Redirect(routes.Application.load)
            }
        }.getOrElse(Redirect(routes.Application.load))
    }

    def login = Action { implicit request =>
        Ok(views.html.login())
    }
    
    def validLogin = TODO

    def validate = Action.async { implicit request =>
    withJsonBody[UserData] { ud =>
        model.validateUser(ud.username, ud.password).map { ouserId =>
        ouserId match {
          case Some(userid) =>
            Ok(Json.toJson(true))
              .withSession("username" -> ud.username, "csrfToken" -> play.filters.csrf.CSRF.getToken.map(_.value).getOrElse(""))
          case None =>
            Ok(Json.toJson(false))
        }
      }
        }  
  }
    

    def home = Action { implicit request =>
        Ok(views.html.home())
    }

    def academic = Action { implicit request =>
        Ok(views.html.login())
    }
    def health = Action { implicit request =>
        Ok(views.html.login())
    }
    def financial = Action { implicit request =>
        Ok(views.html.login())
    }
    def emergency = Action { implicit request =>
        Ok(views.html.login())
    }

}