package demo

import org.scalajs.dom

// https://stackblitz.com/edit/react-syntax-highlighter-issue-js
// https://github.com/remarkjs/react-markdown#use-custom-renderers-syntax-highlight
object Main:

  def main(args: Array[String]): Unit =
    DocPage.component().renderIntoDOM(dom.document.getElementById("container"))
