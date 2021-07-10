package demo.advanced

import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import typings.redux.mod.Dispatch

import scala.scalajs.js

// https://www.youtube.com/watch?v=OXxul6AvXNs
// https://github.com/cmcaboy/redux-typed/tree/typed
object ExpenseContainer:

  @js.native
  trait Props extends js.Object:
    val state: ExpenseState
    val dispatch: Dispatch[ExpenseAction]

  val component = ScalaFnComponent[Props] { (props: Props) =>
    <.div(
      <.h1(s"Expense Page"),
      <.div(
        props.state.expenses
          .map(expense =>
            <.div(^.key := expense.id)(
              <.p(expense.description),
              <.p(expense.amount),
              <.p(expense.note),
              <.button(^.onClick --> Callback(props.dispatch(ExpenseAction.RemoveExpenseAction(expense.id))))("Remove Expense"),
              <.button(^.onClick --> Callback(props.dispatch(ExpenseAction.EditExpenseAction(expense))))("Edit Expense")
            )
          )
          .toVdomArray
      ),
      <.button(^.onClick --> Callback(props.dispatch(ExpenseAction.SetExpenseAction(js.Array(Expense())))))(
        "Set Expense"
      ),
      <.button(^.onClick --> Callback(props.dispatch(ExpenseAction.AddExpenseAction(Expense()))))("Add Expense")
    )
  }
