package demo

import japgolly.scalajs.react.vdom.TagMod
import japgolly.scalajs.react.vdom.Implicits._
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import org.scalajs.dom
import typings.csstype.csstypeStrings.{bold, none, normal}
import typings.csstype.mod.{DisplayLegacy, NamedColor, OverflowYProperty, PositionProperty}
import typings.downshift.components.Downshift
import typings.downshift.mod.{GetItemPropsOptions, GetPropsCommonOptions, GetRootPropsOptions}
import typings.react.components._
import typings.react.mod.CSSProperties

import scala.scalajs.js.|

// https://codesandbox.io/s/github/kentcdodds/downshift-examples/tree/master/?module=/src/ordered-examples/01-basic-autocomplete.js&moduleview=1&file=/src/downshift/ordered-examples/00-get-root-props-example.js
object Demo:
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

  div(TagMod.fromTraversableOnce(0 to 9 map (n => label(n)))).build

  val Main = ScalaFnComponent[Unit] {
    case () =>
      Downshift[Item]()
        .onChange((selection, _) =>
          Callback.alert(asOpt(selection).fold("Selection Cleared")(value => s"You selected ${value.value}"))
        )
        .itemToString(item => asOpt(item).fold("")(_.value))
        .children(ctrl =>
          div(
            label.unsafeSpread(ctrl.getLabelProps())("Enter a fruit:"),
            div
              .style(comboboxStyles)
              .unsafeSpread(
                ctrl.getRootProps(GetRootPropsOptions("refkey"), GetPropsCommonOptions().setSuppressRefError(true))
              )(
                input.unsafeSpread(ctrl.getInputProps()),
                button.unsafeSpread(ctrl.getToggleButtonProps()).`aria-label`("toggle menu")("toggle menu")
              ),
            ul.unsafeSpread(ctrl.getMenuProps())
              .style(menuStyles)(
                if !ctrl.isOpen then TagMod.empty
                else
                  TagMod.fromTraversableOnce(
                    items
                      .filter(item => asOpt(ctrl.inputValue).fold(false)(inputValue => item.value.contains(inputValue)))
                      .zipWithIndex
                      .map {
                        case (item, index) =>
                          li.unsafeSpread(
                            ctrl.getItemProps(
                              GetItemPropsOptions(item)
                                .setKey(item.value)
                                .setIndex(index)
                                .setStyle {
                                  val isSelected = asOpt(ctrl.highlightedIndex).contains(index)
                                  CSSProperties()
                                    .setBackgroundColor(if isSelected then NamedColor.lightgray else NamedColor.white)
                                    .setFontWeight(if isSelected then bold else normal)
                                }
                            )
                          )(item.value)
                            .build
                      }
                  )
              )
          ).rawNode
        )
  }

  def main(argv: Array[String]): Unit =
    Main().renderIntoDOM(dom.document.getElementById("container"))
