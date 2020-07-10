package demo

import org.scalajs.dom

object Main {
  def main(args: Array[String]): Unit =
    App.component().renderIntoDOM(dom.document.getElementById("container"))
}
