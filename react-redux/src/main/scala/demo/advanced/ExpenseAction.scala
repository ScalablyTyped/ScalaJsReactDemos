package demo.advanced

import java.util.UUID

import demo.advanced.ExpenseReducer.AppState
import demo.basic.ReduxFacade.Extractor
import typings.redux.mod.{Action, AnyAction, Dispatch}

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
sealed trait ExpenseAction extends Action[String]

object ExpenseAction {

  @js.native
  trait SetExpenseAction extends ExpenseAction {
    val expenses: js.Array[Expense] = js.native
  }

  object SetExpenseAction extends Extractor[SetExpenseAction] {
    protected val _type = "SET_EXPENSE"

    @scala.inline
    def apply(expenses: js.Array[Expense]): SetExpenseAction = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type.asInstanceOf[js.Any])
      __obj.asInstanceOf[SetExpenseAction].set("expenses", expenses)
    }
  }

  @js.native
  trait EditExpenseAction extends ExpenseAction {
    val expense: Expense = js.native
  }

  object EditExpenseAction extends Extractor[EditExpenseAction] {
    protected val _type = "EDIT_EXPENSE"

    @scala.inline
    def apply(expense: Expense): EditExpenseAction = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type.asInstanceOf[js.Any])
      __obj.asInstanceOf[EditExpenseAction].set("expense", expense)
    }
  }

  @js.native
  trait RemoveExpenseAction extends ExpenseAction {
    val id: String = js.native
  }

  object RemoveExpenseAction extends Extractor[RemoveExpenseAction] {
    protected val _type = "REMOVE_EXPENSE"

    @scala.inline
    def apply(id: String): RemoveExpenseAction = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type.asInstanceOf[js.Any])
      __obj.asInstanceOf[RemoveExpenseAction].set("id", id)
    }
  }

  @js.native
  trait AddExpenseAction extends ExpenseAction {
    val expense: Expense = js.native
  }

  object AddExpenseAction extends Extractor[AddExpenseAction] {
    protected val _type = "ADD_EXPENSE"

    @scala.inline
    def apply(expense: Expense): AddExpenseAction = {
      val __obj = js.Dynamic.literal()
      __obj.updateDynamic("type")(_type.asInstanceOf[js.Any])
      __obj.asInstanceOf[AddExpenseAction].set("expense", expense)
    }

  }

  type AppActions = ExpenseAction

  val addExpense: js.Function1[Expense, ExpenseAction] =
    (expense: Expense) => AddExpenseAction(expense): ExpenseAction

  val removeExpense: js.Function1[String, ExpenseAction] =
    (id: String) => RemoveExpenseAction(id): ExpenseAction

  val editExpense: js.Function1[Expense, ExpenseAction] =
    (expense: Expense) => EditExpenseAction(expense): ExpenseAction

  val setExpenses: js.Function1[js.Array[Expense], ExpenseAction] =
    (expenses: js.Array[Expense]) => SetExpenseAction(expenses): ExpenseAction

}
