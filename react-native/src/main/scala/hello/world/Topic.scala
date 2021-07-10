package hello.world

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.Implicits.*
import typings.reactNative.components.Text
import typings.reactRouter.mod.*

import scala.scalajs.js

object Topic:

  trait Param extends js.Object:
    val topicId: String

  val component = ScalaFnComponent[`match`[Topic.Param]] { m =>
    Text.style(Styles.topicStyle)("Topic: " + m.params.topicId)
  }
