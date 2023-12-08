package models
//Rerunning runMain models.CodeGen will recreate Tables
object CodeGen extends App {
    slick.codegen.SourceCodeGenerator.run(
        "slick.jdbc.PostgresProfile",
        "org.postgresql.Driver",
        "jdbc:postgresql://localhost/turesources?user=turesources&password=appo1nt!ment",
        "/users/adeerin2/TUResources/server/app/",
        "models", None, None, true, false
    )
}