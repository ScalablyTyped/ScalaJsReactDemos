package demo

import org.scalajs.dom.document

object Main:
  def main(argv: Array[String]): Unit =
    I18n.initialize()
    App.component().renderIntoDOM(document.getElementsByTagName("body")(0))
