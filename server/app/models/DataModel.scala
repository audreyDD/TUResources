package models

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future
import models.UserData._

class DataModel(db: Database)(implicit ec: ExecutionContext) {
    def validateUser(username: String, password: String): Future[Option[Int]] = {
      println("validateUser() in DataModel")
        val matches = db.run(Users.filter(userRow => userRow.username === username && userRow.password === password).result)
        matches.map(userRows => userRows.headOption.flatMap { userRow =>
            Some(userRow.id)
        })
        
    }

    def getResources(category:String):Future[Seq[Resource]] = {
      val matches = db.run(Resources.filter(resourceRow => resourceRow.group === category).result)
      //println(matches)
      matches.map(items => items.map(item => Resource(item.resource,item.description,item.hours,item.schedule.getOrElse(false))))

    }

    def createUser(username: String, password: String): Future[Option[Int]] = {
      println("createUser() in DataModel")
        val matches = db.run(Users.filter(userRow => userRow.username === username).result)
        matches.flatMap { userRows =>
            if (userRows.isEmpty) {
              db.run(Users += UsersRow(-1, username, password))
                .flatMap { addCount => 
                  if (addCount > 0) db.run(Users.filter(userRow => userRow.username === username).result)
                    .map(_.headOption.map(_.id))
                  else Future.successful(None)
                }
            } else Future.successful(None)
        }
        
    }
}