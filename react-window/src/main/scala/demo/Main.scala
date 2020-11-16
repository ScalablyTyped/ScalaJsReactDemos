package demo

import org.scalajs.dom
import typings.reactWindow.components._
import typings.reactVirtualizedAutoSizer.components._
import typings.reactWindow.mod.ListChildComponentProps

import scala.scalajs.js
import japgolly.scalajs.react.vdom.html_<^._

object Main {

  final val Elements: Vector[String] = Vector.tabulate(1000)(i => s"Element($i)")

  final val Row = { p: ListChildComponentProps =>
    <.div(^.style := p.style, s"Row(index=${p.index}, data=${Elements(p.index.toInt)})")
  }

  // see https://react-window.now.sh/#/examples/list/fixed-size
  def main(args: Array[String]): Unit =
    FixedSizeList(
      height = 800,
      width = 800,
      itemSize = 100,
      itemCount = Elements.size,
      children = ??? // TODO Row
    ).renderIntoDOM(dom.document.getElementById("container"))

}
