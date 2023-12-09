
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
  lazy val schema: profile.SchemaDescription = Resources.schema
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Resources
   *  @param itemId Database column item_id SqlType(serial), AutoInc, PrimaryKey
   *  @param resource Database column resource SqlType(varchar), Length(2000,true)
   *  @param description Database column description SqlType(varchar), Length(2000,true)
   *  @param hours Database column hours SqlType(varchar), Length(2000,true) */
  case class ResourcesRow(itemId: Int, resource: String, description: String, hours: String)
  /** GetResult implicit for fetching ResourcesRow objects using plain SQL queries */
  implicit def GetResultResourcesRow(implicit e0: GR[Int], e1: GR[String]): GR[ResourcesRow] = GR{
    prs => import prs._
    ResourcesRow.tupled((<<[Int], <<[String], <<[String], <<[String]))
  }
  /** Table description of table resources. Objects of this class serve as prototypes for rows in queries. */
  class Resources(_tableTag: Tag) extends profile.api.Table[ResourcesRow](_tableTag, "resources") {
    def * = (itemId, resource, description, hours).<>(ResourcesRow.tupled, ResourcesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = ((Rep.Some(itemId), Rep.Some(resource), Rep.Some(description), Rep.Some(hours))).shaped.<>({r=>import r._; _1.map(_=> ResourcesRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column item_id SqlType(serial), AutoInc, PrimaryKey */
    val itemId: Rep[Int] = column[Int]("item_id", O.AutoInc, O.PrimaryKey)
    /** Database column resource SqlType(varchar), Length(2000,true) */
    val resource: Rep[String] = column[String]("resource", O.Length(2000,varying=true))
    /** Database column description SqlType(varchar), Length(2000,true) */
    val description: Rep[String] = column[String]("description", O.Length(2000,varying=true))
    /** Database column hours SqlType(varchar), Length(2000,true) */
    val hours: Rep[String] = column[String]("hours", O.Length(2000,varying=true))
  }
  /** Collection-like TableQuery object for table Resources */
  lazy val Resources = new TableQuery(tag => new Resources(tag))
}

