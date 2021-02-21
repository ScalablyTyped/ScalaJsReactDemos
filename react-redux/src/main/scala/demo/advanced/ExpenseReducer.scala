package demo.advanced

import demo.advanced.ExpenseAction.{AddExpenseAction, EditExpenseAction, RemoveExpenseAction, SetExpenseAction}
import typings.redux.mod._
import typings.std.ReturnType
import typings.std.global.alert

import scala.scalajs.js

object ExpenseReducer {

  type AppState = ReturnType[rootReducer.type]

  trait Reducers extends js.Object {
    val expenseReducer: Reducer[ExpenseContainer.LinkStateProps, ExpenseAction]
    // other reducers
    // val otherReducer: Reducer[_, _]
  }

  lazy val rootReducer: Reducer[CombinedState[
    StateFromReducersMapObject[Reducers]
  ], ActionFromReducersMapObject[Reducers]] = combineReducers(new Reducers {
    override val expenseReducer: Reducer[ExpenseContainer.LinkStateProps, ExpenseAction] = ExpenseReducer.Reducer
  })

  lazy val Reducer: Reducer[ExpenseContainer.LinkStateProps, ExpenseAction] = (stateOpt, action) => {
    val state = stateOpt.getOrElse(ExpenseContainer.LinkStateProps.initial)

    action match {
      case SetExpenseAction(_action) => ExpenseContainer.LinkStateProps(_action.expenses)
      case EditExpenseAction(_action) =>
        alert("an implementation is missing")
        ExpenseContainer.LinkStateProps(state.expenses.map { expense =>
          if (expense.id.equals(_action.expense.id)) _action.expense
          else expense
        })
      case RemoveExpenseAction(_action) =>
        ExpenseContainer.LinkStateProps(state.expenses.filterNot(_.id.equals(_action.id)))
      case AddExpenseAction(_action) =>
        val expenses = state.expenses.foldLeft(js.Array[Expense]())(_ += _)
        ExpenseContainer.LinkStateProps(expenses += _action.expense)
      case _ => state
    }
  }

}
