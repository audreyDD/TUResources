package models

case class UserData(username: String, password: String)
case class OneResource(category: String, resourceName: String)
case class User(id:Int,username: String, password: String,admin:Boolean)
case class Resource(name:String,description:String,hours:String,schedule:Boolean)