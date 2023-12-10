package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.i18n._
import play.api.data.Forms._
import akka.http.javadsl.model.ws.Message

import play.api.libs.json._
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

    implicit val userDataReads:Reads[UserData] = Json.reads[UserData]
    implicit val ResourceReads:Reads[Resource] = Json.reads[Resource]
    implicit val ResourceWrites:Writes[Resource] = Json.writes[Resource]

    def withJsonBody[A](f: A => Future[Result])(implicit request: Request[AnyContent], reads: Reads[A]) = {
        request.body.asJson.map { body =>
            Json.fromJson[A](body) match {
                case JsSuccess(a, path) => f(a)
                case e @ JsError(_) => Future.successful(Redirect(routes.Application.load))
            }
        }.getOrElse(Future.successful(Redirect(routes.Application.load)))
    }

    def login = Action { implicit request =>
        Ok(views.html.login())
    }
    
    def validLogin = TODO

    def validateTU = Action.async { implicit request =>
        println("validate in TUResources")
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

    def createUserTU = Action.async { implicit request =>
        println("createUser in TUResources")
        withJsonBody[UserData] { ud =>
            model.createUser(ud.username, ud.password).map { ouserId =>
                ouserId match {
                    case Some(userid) =>
                        Ok(Json.toJson(true))
                        .withSession("username" -> ud.username, "csrfToken" -> play.filters.csrf.CSRF.getToken.map(_.value).getOrElse(""))
                    case None =>
                        println("createUser in TUResources failed")
                        Ok(Json.toJson(false))
                }
            }
        }  
    }
    
  def openCategory = Action.async { implicit request =>
    println("openCategory in TUResources")
      withJsonBody[String] { category =>
        val resources = model.getResources(category)
        //println(resources)
        resources.map(items =>  Ok(Json.toJson(items)))
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