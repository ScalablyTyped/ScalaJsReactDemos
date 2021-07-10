package demo

import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, CallbackTo}
import org.scalajs.dom.window
import typings.react.mod.useEffect

import scala.scalajs.js

object TodoList:

  case class Props(store: TodoStore, peopleStore: PeopleStore)

  val component = ObserverComponent[Props] {
    case Props(store, peopleStore) =>
      useEffect(
        () => {
          store.assignTodo(0, Some(peopleStore.people.get()(0)))
          store.assignTodo(1, Some(peopleStore.people.get()(1)))
          peopleStore.renamePerson(0, "Michel Weststrate")
        },
        js.Array() // run an effect and clean it up only once (on mount and unmount)
      )

      val onNewTodo: Callback = CallbackTo(window.prompt("Task name", "coffee plz")) flatMap {
        case e if e.isEmpty => Callback.empty
        case task           => Callback(store.addTodo(task))
      }

      val onLoadTodo = Callback {
        store.increasePending()
        window.setTimeout(
          () => {
            store.addTodo("Random Todo " + Math.random())
            store.decreasePending()
          },
          2000
        )
      }

      val todoViews = TagMod.fromTraversableOnce {
        val ts = store.todos.get().todos
        ts.indices.map(index =>
          TodoView.component.withKey("td" + index)(
            TodoView.Props(
              todo = ts(index),
              toggle = Callback(store.toggleTodo(index)),
              rename = task => Callback(store.renameTodo(index, task))
            )
          )
        )
      }

      <.div(
        store.report.get(),
        <.ul(todoViews),
        <.ul(
          store.pendingRequests.get().c match {
            case 0 => <.div()
            case _ => <.li("Loading...")
          }
        ),
        <.button(^.onClick --> onNewTodo)("New Todo"),
        <.small("double-click a todo to edit"),
        <.div(
          <.button(^.onClick --> onLoadTodo.void)("Load async Todo")
        )
      )
  }
