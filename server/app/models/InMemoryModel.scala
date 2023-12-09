package models

import collection.mutable

object InMemoryModel {
    private val categories = Seq[String]("academic", "health", "financial", "emergency")
    private val users = mutable.Map[String, String]("student" -> "password", "mlewis" -> "prof", "a" -> "a")
                                        //Category -> (resourceName, Description, hourse, can be scheduled (1 being true))
    private val resources = mutable.Map[String, Seq[Seq[String]]](
        "academic" ->   Seq(Seq("ACM", "Get help with any CS homework in 257!", "M-Th 4-9pm", "0"),
                            Seq("QRS", "Get help with any math homework in the library!", "M-F 4-9pm", "0"),
                            Seq("Writing Center", "Get help with any writing assignments in the library!", "M-F 3-5pm", "1", "M T W TH F, pm 3 4 5")),
        "health" ->     Seq(Seq("Counseling", "Get biweekly counseling sessions or see a psychiatrist.", "M-F 9am-5pm", "1", "M T W TH F, am 9 10 11 pm 12 1 2 3 5"))
    )

    def getCategories(): Seq[String] = {
        categories
    }

    def validateUser(username: String, password: String): Boolean = {
        users.get(username).map(_ == password).getOrElse(false)
    }

    def createUser(username: String, password: String): Boolean = {
        if (users.contains(username)) false else {
            users(username) = password
            true
        }
    }

    def getResources(category: String): Seq[Seq[String]] = {
        resources.get(category).getOrElse(Nil)
    }

    def getOneResource(category: String, resourceName: String): Seq[String] = {
        val categoryMatch = resources.get(category).getOrElse(Nil)
        var ans = Seq[String]()
        
        for(r <- categoryMatch) {
            if (r(0) == resourceName) {
                ans = r
            }
        }

        ans

    }

}