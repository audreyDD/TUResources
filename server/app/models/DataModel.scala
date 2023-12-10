package models

import slick.jdbc.PostgresProfile.api._
import scala.concurrent.ExecutionContext
import models.Tables._
import scala.concurrent.Future

class DataModel(db: Database)(implicit ec: ExecutionContext) {
    def validateUser(username: String, password: String): Future[Option[Int]] = {
        val matches = db.run(Users.filter(userRow => userRow.username === username && userRow.password === password).result)
        matches.map(userRows => userRows.headOption.flatMap { userRow =>
            Some(userRow.id)
        })
        
    }

    def createUser(username: String, password: String): Future[Option[Int]] = {
      /*
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
        }*/
        ???
    }
}