package models

case class UserData(username: String, password: String)
case class OneResource(category: String, resourceName: String)
case class Resource(name:String,description:String,hours:String,admin:Boolean)