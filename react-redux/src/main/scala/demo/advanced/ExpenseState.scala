package demo.advanced

import scala.scalajs.js

class ExpenseState(val expenses: js.Array[Expense]) extends js.Object

object ExpenseState:

  val initial: ExpenseState = ExpenseState(js.Array[Expense]())

  def apply(expenses: js.Array[Expense]): ExpenseState =
    new ExpenseState(expenses)
