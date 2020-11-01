package demo.advanced

import demo.advanced.ExpenseAction.{addExpense, removeExpense, AppActions}
import demo.advanced.ExpenseReducer.AppState
import japgolly.scalajs.react.component.Js
import japgolly.scalajs.react.component.ScalaFn.Component
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import japgolly.scalajs.react.{Callback, Children, CtorType, JsComponent, ScalaFnComponent}
import typings.reactRedux.mod.connect
import typings.redux.mod.{bindActionCreators, AnyAction, Dispatch}

import scala.scalajs.js

object ExpenseContainer {

  class LinkStateProps(var expenses: js.Array[Expense]) extends js.Object

  object LinkStateProps {

    val initial: LinkStateProps = LinkStateProps(js.Array[Expense]())

    def apply(expenses: js.Array[Expense]): LinkStateProps =
      new LinkStateProps(expenses)
  }

  trait LinkDispatchProps extends js.Object {
    val startAddExpense: js.Function1[Expense, Unit]
    val startEditExpense: js.Function1[String, Unit]
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
              <.button(^.onClick --> Callback(props.startRemoveExpense("")))("Remove Expense"),
              <.button(^.onClick --> Callback(props.startEditExpense("")))("Edit Expense")
            )
          )
          .toVdomArray
      ),
      <.button(^.onClick --> Callback(props.startAddExpense(Expense())))("Edit Expense")
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
        startRemoveExpense = bindActionCreators(removeExpense, dispatch.asInstanceOf[Dispatch[AnyAction]])
      )

  val connectElem: Js.Component[Props, Null, CtorType.PropsAndChildren] =
    JsComponent[Props, Children.Varargs, Null](
      connect.asInstanceOf[js.Dynamic](mapStateToProps, mapDispatchToProps)(component.toJsComponent.raw)
    )

}
