package demo.advanced

import java.util.UUID
import scala.scalajs.js

class Expense(val id: String, val description: String, val note: String, val amount: String, val createAt: String)
  extends js.Object

object Expense {

  val default = new Expense("", "", "", "", "")

  def apply(): Expense = new Expense(UUID.randomUUID().toString, "description", "note", "1.0", "createAt")

}
