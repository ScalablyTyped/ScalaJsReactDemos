package demo

import org.scalajs.dom.document

@main
def main: Unit =
  TodoList(TodoListProps(new TodoStore, new PeopleStore))
    .renderIntoDOM(document.getElementsByTagName("body")(0))
