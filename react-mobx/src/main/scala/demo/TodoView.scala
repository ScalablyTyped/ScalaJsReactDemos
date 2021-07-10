package demo

import japgolly.scalajs.react.{Callback, CallbackTo}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom.window

object TodoView:

  case class Props(todo: Todo, toggle: Callback, rename: String => Callback)

  val component = ObserverComponent[Props] {
    case Props(todo, toggle, rename) =>
      val onRename = CallbackTo(window.prompt("Task name", todo.task)) flatMap {
        case e if e.isEmpty => Callback.empty
        case task           => rename(task)
      }

      <.li(^.onDoubleClick --> onRename)(
        <.input(
          ^.`type` := "checkbox",
          ^.checked := todo.completed,
          ^.onChange --> toggle
        ),
        todo.task,
        todo.assignee match {
          case Some(person) => <.small(person.name)
          case None         => <.span()
        }
      )
  }
