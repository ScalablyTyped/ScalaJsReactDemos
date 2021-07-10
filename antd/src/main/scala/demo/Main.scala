package demo

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object Main:
  def main(args: Array[String]): Unit =
    IndexCSS
    App.component().renderIntoDOM(dom.document.getElementById("container"))

@JSImport("./index.css", JSImport.Namespace)
@js.native
object IndexCSS extends js.Object
