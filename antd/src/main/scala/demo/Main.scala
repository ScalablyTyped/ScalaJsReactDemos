package demo

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("./index.css", JSImport.Namespace)
@js.native
object IndexCSS extends js.Object

@main
def main: Unit =
  IndexCSS
  App.component().renderIntoDOM(dom.document.getElementById("container"))
