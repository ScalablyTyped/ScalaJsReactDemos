package demo

import org.scalajs.dom

object Main:
  def main(args: Array[String]): Unit =
    components.App().renderIntoDOM(dom.document.getElementById("container"))
