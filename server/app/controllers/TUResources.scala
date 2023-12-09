package controllers

import javax.inject._
import play.api.mvc._
import play.api.data._
import play.api.i18n._
import play.api.data.Forms._
import akka.http.javadsl.model.ws.Message

case class LoginData(username:String, password:String)

@Singleton
class TUResources @Inject()(cc: MessagesControllerComponents) extends MessagesAbstractController(cc){

    def login = Action { implicit request =>
        Ok(views.html.login())
    }
    
    def validLogin = TODO
    

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