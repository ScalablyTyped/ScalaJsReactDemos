package demo

import japgolly.scalajs.react.facade.React.{Element, RefFn, RefHandle}
import japgolly.scalajs.react.vdom.Attr.ValueType
import japgolly.scalajs.react.vdom.TopNode
import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{Callback, CallbackTo, ScalaFnComponent}
import org.scalajs.dom.HTMLElement
import typings.csstype.mod.{ClearProperty, FloatProperty, TextAlignProperty}
import typings.dndCore.interfacesMod.SourceType
import typings.react.mod.CSSProperties
import typings.reactDnd.components.DndProvider
import typings.reactDnd.connectorsMod.{ConnectDropTarget, ConnectableElement}
import typings.reactDnd.hooksApiMod.{DragSourceHookSpec, DropTargetHookSpec}
import typings.reactDnd.mod.{useDrag, useDrop}
import typings.reactDndHtml5Backend.mod.HTML5Backend
import typings.std.global.alert

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.|

object components:
  object ItemTypes:
    val BOX = "box"

  implicit def whyyyDoesItHaveToBeSoComplicated: ValueType[HTMLElement => Callback, RefFn[HTMLElement]] =
    ValueType[HTMLElement => Callback, RefFn[HTMLElement]] { (consume, f) =>
      val massaged: RefFn[HTMLElement] =
        (e: HTMLElement | Null) => Option(e.asInstanceOf[HTMLElement]).foreach(e => f(e).runNow())

      consume(massaged)
    }

  case class Collected(isOver: Boolean, canDrop: Boolean)

  class DropResult(val name: String) extends js.Object

  val dustbinStyles = CSSProperties()
    .setHeight("12rem")
    .setWidth("12rem")
    .setMarginRight("1.5rem")
    .setMarginBottom("1.5rem")
    .setColor("white")
    .setPadding("1rem")
    .setTextAlign(TextAlignProperty.center)
    .setFontSize("1rem")
    .setLineHeight("normal")
    .setFloat(FloatProperty.left)

  val Dustbin = ScalaFnComponent[Unit] { case () =>
    val js.Tuple2(Collected(canDrop, isOver), drop) =
      useDrop(
        DropTargetHookSpec[js.Object, DropResult, Collected](ItemTypes.BOX)
          .setDrop((_, _) => new DropResult("Dustbin"))
          .setCollect(monitor => Collected(monitor.isOver(), monitor.canDrop()))
      )

    val isActive = canDrop && isOver

    val backgroundColor: String =
      if isActive then "darkgreen"
      else if canDrop then "darkkhaki"
      else "#222"

    <.div(
      ^.untypedRef(elem => drop(elem.asInstanceOf[ConnectableElement], js.undefined)),
      ^.style := dustbinStyles.duplicate.setBackgroundColor(backgroundColor),
      if isActive then "Release to drop" else "Drag a box here"
    )
  }

  class Dragged(val name: String, val `type`: SourceType) extends js.Object

  val boxStyles = CSSProperties()
    .setBorder("1px dashed gray")
    .setBackgroundColor("white")
    .setPadding("0.5rem 1rem")
    .setMarginRight("1.5rem")
    .setMarginBottom("1.5rem")
    .setCursor("move")
    .setFloat(FloatProperty.left)

  val Box = ScalaFnComponent[String] { name =>
    val js.Tuple3(isDragging, drag, _) =
      useDrag(
        DragSourceHookSpec[Dragged, DropResult, Boolean](item = new Dragged(name, ItemTypes.BOX))
          .setEnd { (itemU, monitor) =>
            Callback(itemU.foreach { item =>
              val dropResult = monitor.getDropResult()
              alert(s"You dropped ${item.name} into ${dropResult.asInstanceOf[DropResult].name}!")
            })
          }
          .setCollect(monitor => monitor.isDragging())
      )

    val opacity = if isDragging then "0.4" else "1"
    <.div(
      ^.untypedRef(elem => drag(elem.asInstanceOf[ConnectableElement], js.undefined)),
      ^.style := boxStyles.duplicate.setOpacity(opacity),
      name
    )
  }
  val Container = ScalaFnComponent[Unit] { case () =>
    <.div(
      <.div(^.style := CSSProperties().setOverflow("hidden").setClear(ClearProperty.both), Dustbin()),
      <.div(
        ^.style := CSSProperties().setOverflow("hidden").setClear(ClearProperty.both),
        Box("Glass"),
        Box("Banana"),
        Box("Paper")
      )
    )
  }

  val App = ScalaFnComponent[Unit] { case () =>
    <.div(^.className := "App")(
      DndProvider.Backend(HTML5Backend)(Container())
    )
  }
end components
