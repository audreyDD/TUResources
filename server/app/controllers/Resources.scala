package controllers

import javax.inject._
import shared.SharedMessages
import play.api.mvc._


@Singleton
class Resources @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

    def login = Action { implicit request =>
        Ok(views.html.login())
    }

    def validLogin = Action { implicit request =>
        val postVals = request.body.asFormUrlEncoded
        postVals.map { args => 
            val username = args("username").head
            val password= args("password").head
            if(MessagesInMemoryModel.validateUser(username,password)) {
                Redirect(routes.Messages.messageBoard).withSession("username" -> username, "chatName" -> "everyone")
            }else{
                Redirect(routes.Messages.login)
            }
        
        }.getOrElse(Redirect(routes.Messages.login))
        }


}
