package demo.advanced

import typings.redux.mod.Action

import scala.scalajs.js

@js.native
sealed trait ExpenseAction extends Action[String]

object ExpenseAction {

  @js.native
  trait SetExpenseAction extends ExpenseAction {
    val expenses: js.Array[Expense] = js.native
  }

  object SetExpenseAction {
    def _type = "SET_EXPENSE"

    @scala.inline
    def apply(expenses: js.Array[Expense]): SetExpenseAction = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type)
      __obj.asInstanceOf[SetExpenseAction].set("expenses", expenses)
    }
    def unapply(a: Action[String]): Option[js.Array[Expense]] =
      if a.`type` == _type then Some(a.asInstanceOf[SetExpenseAction].expenses) else None
  }

  @js.native
  trait EditExpenseAction extends ExpenseAction {
    val expense: Expense = js.native
  }

  object EditExpenseAction {
    def _type = "EDIT_EXPENSE"

    @scala.inline
    def apply(expense: Expense): EditExpenseAction = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type)
      __obj.asInstanceOf[EditExpenseAction].set("expense", expense)
    }
    def unapply(a: Action[String]): Option[Expense] =
      if a.`type` == _type then Some(a.asInstanceOf[EditExpenseAction].expense) else None
  }

  @js.native
  trait RemoveExpenseAction extends ExpenseAction {
    val id: String = js.native
  }

  object RemoveExpenseAction {
    def _type = "REMOVE_EXPENSE"

    @scala.inline
    def apply(id: String): RemoveExpenseAction = {
      val __obj = js.Dynamic.literal("id" -> id)
      __obj.updateDynamic("type")(_type)
      __obj.asInstanceOf[RemoveExpenseAction]
    }

    def unapply(a: Action[String]): Option[String] =
      if a.`type` == _type then Some(a.asInstanceOf[RemoveExpenseAction].id) else None
  }

  @js.native
  trait AddExpenseAction extends ExpenseAction {
    val expense: Expense = js.native
  }

  object AddExpenseAction {
    def _type = "ADD_EXPENSE"
    @scala.inline
    def apply(expense: Expense): AddExpenseAction = {
      val __obj = js.Dynamic.literal("expense" -> expense)
      __obj.updateDynamic("type")(_type)
      __obj.asInstanceOf[AddExpenseAction]
    }

    def unapply(a: Action[String]): Option[Expense] =
      if a.`type` == _type then Some(a.asInstanceOf[AddExpenseAction].expense) else None
  }
}
