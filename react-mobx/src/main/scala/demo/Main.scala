package demo

import org.scalajs.dom.document

object Main {
  def main(argv: Array[String]): Unit =
    TodoList
      .component(TodoList.Props(new TodoStore, new PeopleStore))
      .renderIntoDOM(document.getElementsByTagName("body")(0))
}
