package demo

import org.scalajs.dom

@main
def main: Unit =
  components.App().renderIntoDOM(dom.document.getElementById("container"))
