package demo

import japgolly.scalajs.react.vdom.VdomNode
import japgolly.scalajs.react.vdom.Implicits._
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import org.scalajs.dom
import typings.StBuildingComponent
import typings.csstype.csstypeStrings.{bold, none, normal}
import typings.csstype.mod.{DisplayLegacy, NamedColor, OverflowYProperty, PositionProperty}
import typings.downshift.components.Downshift
import typings.downshift.mod.{GetItemPropsOptions, GetPropsCommonOptions, GetRootPropsOptions}
import typings.react.components._
import typings.react.mod.CSSProperties

import scala.scalajs.js
import scala.scalajs.js.|

// https://codesandbox.io/s/github/kentcdodds/downshift-examples/tree/master/?module=/src/ordered-examples/01-basic-autocomplete.js&moduleview=1&file=/src/downshift/ordered-examples/00-get-root-props-example.js
object Demo {
  // missing in html dsl
  implicit class SpreadOps[B <: StBuildingComponent[_]](private val b: B) {
    def spread(obj: js.Any): B = {
      typings.std.global.Object.assign(b.args(1), obj)
      b
    }
  }

  // missing in scala
  def asOpt[T](t: T | Null): Option[T] = Option(t.asInstanceOf[T])

  val menuStyles = CSSProperties()
    .setMaxHeight(80)
    .setMaxWidth(300)
    .setOverflowY(OverflowYProperty.scroll)
    .setBackgroundColor("#eee")
    .setPadding(0)
    .setListStyle(none)
    .setPosition(PositionProperty.relative)

  val comboboxStyles = CSSProperties().setDisplay(DisplayLegacy.`inline-block`).setMarginLeft("5px")

  case class Item(value: String)

  val items = Seq(Item("apple"), Item("pear"), Item("orange"), Item("grape"), Item("banana"))

  val Main = ScalaFnComponent[Unit] {
    case () =>
      Downshift[Item]
        .onChange((selection, _) =>
          Callback.alert(asOpt(selection).fold("Selection Cleared")(value => s"You selected ${value.value}"))
        )
        .itemToString(item => asOpt(item).fold("")(_.value))
        .children { ctrl =>
          val renderedItems: Seq[VdomNode] =
            if (!ctrl.isOpen) Nil
            else
              items
                .filter(item => asOpt(ctrl.inputValue).fold(false)(inputValue => item.value.contains(inputValue)))
                .zipWithIndex
                .map {
                  case (item, index) =>
                    li().spread(
                      ctrl.getItemProps(
                        GetItemPropsOptions(item)
                          .setKey(item.value)
                          .setIndex(index)
                          .setStyle {
                            val isSelected = asOpt(ctrl.highlightedIndex).contains(index)
                            CSSProperties()
                              .setBackgroundColor(if (isSelected) NamedColor.lightgray else NamedColor.white)
                              .setFontWeight(if (isSelected) bold else normal)
                          }
                      )
                    )(item.value): VdomNode
                }

          div(
            label().spread(ctrl.getLabelProps())("Enter a fruit:"),
            div
              .style(comboboxStyles)
              .spread(
                ctrl.getRootProps(GetRootPropsOptions("refKey"), GetPropsCommonOptions().setSuppressRefError(true))
              )(
                input().spread(ctrl.getInputProps()),
                button()
                  .spread(ctrl.getToggleButtonProps())
                  .`aria-label`("toggle menu")(
                    "toggle menu"
                  )
              ),
            ul().spread(ctrl.getMenuProps()).style(menuStyles)(renderedItems: _*)
          ).rawNode
        }
  }

  def main(argv: Array[String]): Unit =
    Main().renderIntoDOM(dom.document.getElementById("container"))
}
