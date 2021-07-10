package demo.advanced

import typings.redux.mod._

object ExpenseReducer {
  val Reducer: Reducer[ExpenseState, ExpenseAction] = (stateOpt, action) => {
    val state = stateOpt.getOrElse(ExpenseState.initial)
    action match {
      case ExpenseAction.SetExpenseAction(expenses) =>
        ExpenseState(expenses)

      case ExpenseAction.EditExpenseAction(editedExpense) =>
        ExpenseState(state.expenses.map { expense =>
          if expense.id.equals(editedExpense.id) then editedExpense
          else expense
        })

      case ExpenseAction.RemoveExpenseAction(expenseId) =>
        ExpenseState(state.expenses.filterNot(_.id.equals(expenseId)))

      case ExpenseAction.AddExpenseAction(expense) =>
        ExpenseState(state.expenses :+ expense)
      case _ =>
        state
    }
  }
}
