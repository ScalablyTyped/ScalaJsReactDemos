package demo.advanced

import demo.advanced.ExpenseAction._
import demo.advanced.ExpenseReducer.AppState
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{Callback, Children, CtorType, JsComponent, ScalaFnComponent}
import typings.reactRedux.mod.connect
import typings.redux.mod.{AnyAction, Dispatch, bindActionCreators}

import scala.scalajs.js

object ExpenseContainer {

  class LinkStateProps(val expenses: js.Array[Expense]) extends js.Object

  object LinkStateProps {

    val initial: LinkStateProps = LinkStateProps(js.Array[Expense]())

    def apply(expenses: js.Array[Expense]): LinkStateProps =
      new LinkStateProps(expenses)
  }

  trait LinkDispatchProps extends js.Object {
    val startAddExpense: js.Function1[Expense, Unit]
    val startSetExpenses: js.Function1[js.Array[Expense], Unit]
    val startEditExpense: js.Function1[Expense, Unit]
    val startRemoveExpense: js.Function1[String, Unit]
  }

  type Props = LinkDispatchProps with LinkStateProps

  val component = ScalaFnComponent[Props] { props =>
    <.div(
      <.h1(s"Expense Page"),
      <.div(
        props.expenses
          .map(expense =>
            <.div(
              <.p(expense.description),
              <.p(expense.amount),
              <.p(expense.note),
              <.button(^.onClick --> Callback(props.startRemoveExpense(expense.id)))("Remove Expense"),
              <.button(^.onClick --> Callback(props.startEditExpense(expense)))("Edit Expense")
            )
          )
          .toVdomArray,
      ),
      <.button(^.onClick --> Callback(props.startSetExpenses(js.Array[Expense](Expense()))))("Set Expense"),
      <.button(^.onClick --> Callback(props.startAddExpense(Expense())))("Add Expense"),
    )
  }

  val mapStateToProps: js.Function1[AppState, js.Dynamic] =
    (state: AppState) => {
      val expenses = state.asInstanceOf[js.Dynamic].expenseReducer.expenses
      js.Dynamic.literal(expenses = expenses)
    }

  val mapDispatchToProps: js.Function1[Dispatch[AppActions], js.Dynamic] =
    (dispatch: Dispatch[AppActions]) =>
      js.Dynamic.literal(
        startAddExpense = bindActionCreators(addExpense, dispatch.asInstanceOf[Dispatch[AnyAction]]),
        startSetExpenses = bindActionCreators(setExpenses, dispatch.asInstanceOf[Dispatch[AnyAction]]),
        startRemoveExpense = bindActionCreators(removeExpense, dispatch.asInstanceOf[Dispatch[AnyAction]]),
        startEditExpense = bindActionCreators(editExpense, dispatch.asInstanceOf[Dispatch[AnyAction]]),
      )

  val connectElem: Js.Component[Props, AppState with js.Object, CtorType.PropsAndChildren] =
    JsComponent[Props, Children.Varargs, AppState with js.Object](
      connect.asInstanceOf[js.Dynamic](mapStateToProps, mapDispatchToProps)(component.toJsComponent.raw)
    )

}
