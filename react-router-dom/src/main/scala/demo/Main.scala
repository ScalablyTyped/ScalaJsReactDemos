package demo

import org.scalajs.dom

object Main:
  def main(args: Array[String]): Unit =
    val container = Option(dom.document.getElementById("root")).getOrElse {
      val elem = dom.document.createElement("div")
      elem.id = "root"
      dom.document.body.appendChild(elem)
      elem
    }

    App.component().renderIntoDOM(container)
end Main
