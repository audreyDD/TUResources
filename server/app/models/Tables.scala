package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends Tables {
  val profile = slick.jdbc.PostgresProfile
}

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.jdbc.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Resources.schema ++ Users.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Resources
   *  @param itemId Database column item_id SqlType(serial), AutoInc, PrimaryKey
   *  @param resource Database column resource SqlType(varchar), Length(2000,true)
   *  @param description Database column description SqlType(varchar), Length(2000,true)
   *  @param hours Database column hours SqlType(varchar), Length(2000,true)
   *  @param group Database column group SqlType(varchar), Default(None)
   *  @param schedule Database column schedule SqlType(bool), Default(None) */
  case class ResourcesRow(itemId: Int, resource: String, description: String, hours: String, group: Option[String] = None, schedule: Option[Boolean] = None)
  /** GetResult implicit for fetching ResourcesRow objects using plain SQL queries */
  implicit def GetResultResourcesRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[String]], e3: GR[Option[Boolean]]): GR[ResourcesRow] = GR{
    prs => import prs._
    ResourcesRow.tupled((<<[Int], <<[String], <<[String], <<[String], <<?[String], <<?[Boolean]))
  }
  /** Table description of table resources. Objects of this class serve as prototypes for rows in queries. */
  class Resources(_tableTag: Tag) extends profile.api.Table[ResourcesRow](_tableTag, "resources") {
    def * = (itemId, resource, description, hours, group, schedule).<>(ResourcesRow.tupled, ResourcesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemId), Rep.Some(resource), Rep.Some(description), Rep.Some(hours), group, schedule)).shaped.<>({r=>import r._; _1.map(_=> ResourcesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column item_id SqlType(serial), AutoInc, PrimaryKey */
    val itemId: Rep[Int] = column[Int]("item_id", O.AutoInc, O.PrimaryKey)
    /** Database column resource SqlType(varchar), Length(2000,true) */
    val resource: Rep[String] = column[String]("resource", O.Length(2000,varying=true))
    /** Database column description SqlType(varchar), Length(2000,true) */
    val description: Rep[String] = column[String]("description", O.Length(2000,varying=true))
    /** Database column hours SqlType(varchar), Length(2000,true) */
    val hours: Rep[String] = column[String]("hours", O.Length(2000,varying=true))
    /** Database column group SqlType(varchar), Default(None) */
    val group: Rep[Option[String]] = column[Option[String]]("group", O.Default(None))
    /** Database column schedule SqlType(bool), Default(None) */
    val schedule: Rep[Option[Boolean]] = column[Option[Boolean]]("schedule", O.Default(None))
  }
  /** Collection-like TableQuery object for table Resources */
  lazy val Resources = new TableQuery(tag => new Resources(tag))

  /** Entity class storing rows of table Users
   *  @param id Database column id SqlType(serial), AutoInc, PrimaryKey
   *  @param username Database column username SqlType(varchar), Length(20,true)
   *  @param password Database column password SqlType(varchar), Length(200,true)
   *  @param admin Database column admin SqlType(bool), Default(None) */
  case class UsersRow(id: Int, username: String, password: String, admin: Option[Boolean] = None)
  /** GetResult implicit for fetching UsersRow objects using plain SQL queries */
  implicit def GetResultUsersRow(implicit e0: GR[Int], e1: GR[String], e2: GR[Option[Boolean]]): GR[UsersRow] = GR{
    prs => import prs._
    UsersRow.tupled((<<[Int], <<[String], <<[String], <<?[Boolean]))
  }
  /** Table description of table users. Objects of this class serve as prototypes for rows in queries. */
  class Users(_tableTag: Tag) extends profile.api.Table[UsersRow](_tableTag, "users") {
    def * = (id, username, password, admin).<>(UsersRow.tupled, UsersRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(id), Rep.Some(username), Rep.Some(password), admin)).shaped.<>({r=>import r._; _1.map(_=> UsersRow.tupled((_1.get, _2.get, _3.get, _4)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(serial), AutoInc, PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.AutoInc, O.PrimaryKey)
    /** Database column username SqlType(varchar), Length(20,true) */
    val username: Rep[String] = column[String]("username", O.Length(20,varying=true))
    /** Database column password SqlType(varchar), Length(200,true) */
    val password: Rep[String] = column[String]("password", O.Length(200,varying=true))
    /** Database column admin SqlType(bool), Default(None) */
    val admin: Rep[Option[Boolean]] = column[Option[Boolean]]("admin", O.Default(None))
  }
  /** Collection-like TableQuery object for table Users */
  lazy val Users = new TableQuery(tag => new Users(tag))
}
