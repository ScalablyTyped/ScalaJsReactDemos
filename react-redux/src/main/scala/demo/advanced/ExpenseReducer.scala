package demo.advanced

import demo.advanced.ExpenseAction.{AddExpenseAction, EditExpenseAction, RemoveExpenseAction, SetExpenseAction}
import typings.redux.mod.{combineReducers, Reducer}
import typings.std.ReturnType

import scala.scalajs.js

object ExpenseReducer {

  type AppState = ReturnType[rootReducer.type]

  lazy val rootReducer = combineReducers(js.Dynamic.literal("expenseReducer" -> ExpenseReducer.Reducer))

  lazy val Reducer: Reducer[ExpenseContainer.LinkStateProps, ExpenseAction] = (stateOpt, action) => {
    val state = stateOpt.getOrElse(ExpenseContainer.LinkStateProps.initial)

    action match {
      case SetExpenseAction(_action) => ExpenseContainer.LinkStateProps(_action.expenses)
      case EditExpenseAction(_action) =>
        ExpenseContainer.LinkStateProps(state.expenses.map { expense =>
          if (expense.id.equals(_action.expense.id)) _action.expense
          else expense
        })
      case RemoveExpenseAction(_action) =>
        ExpenseContainer.LinkStateProps(state.expenses.filterNot(_.id.equals(_action.id)))
      case AddExpenseAction(_action) =>
        ExpenseContainer.LinkStateProps(state.expenses += _action.expense)
      case _ => state
    }
  }

}
