package demo

import japgolly.scalajs.react.facade.React.StatelessFunctionalComponent
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom
import typings.reactWindow.components.*
import typings.reactVirtualizedAutoSizer.components.*
import typings.reactWindow.mod.ListChildComponentProps

object Main:

  final val Elements: Vector[String] = Vector.tabulate(1000)(i => s"Element($i)")

  final val Row = ScalaFnComponent { (p: ListChildComponentProps) =>
    <.div(^.style := p.style, s"Row(index=${p.index}, data=${Elements(p.index.toInt)})")
  }.toJsComponent

  // see https://react-window.now.sh/#/examples/list/fixed-size
  def main(args: Array[String]): Unit =
    val rawRow: StatelessFunctionalComponent[ListChildComponentProps] = Row.raw
    val rawRowCasted = rawRow.asInstanceOf[typings.react.mod.FunctionComponent[ListChildComponentProps]]

    FixedSizeList(
      height = 800,
      width = 800,
      itemSize = 100,
      itemCount = Elements.size,
      children = rawRowCasted
    ).renderIntoDOM(dom.document.getElementById("container"))
  end main
end Main
