package demo

import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom
import typings.react.mod.useState
import typings.react.reactStrings.submit
import typings.semanticUiReact.genericMod.{SemanticICONS, SemanticSIZES, SemanticWIDTHSSTRING}
import typings.semanticUiReact.semanticUiReactStrings.left
import typings.semanticUiReact.components as Sui

import scala.language.implicitConversions
import scala.scalajs.js

val App = ScalaFnComponent { case () =>
  val js.Tuple2(isModalVisible, updateIsModalVisible) = useState(false)

  <.div(
    Sui.Grid(
      Sui.GridColumn.width(SemanticWIDTHSSTRING.`1`),
      Sui.GridColumn.width(SemanticWIDTHSSTRING.`14`)(
        Sui.Divider.horizontal(true)(
          Sui.Header.as("h4")(Sui.Icon.name(SemanticICONS.tag), "Button and Icon")
        ),
        <.p(Sui.Button.primary(true)("Primary")),
        <.p(Sui.Icon.name(SemanticICONS.recycle)),
        <.p(
          Sui.Button.icon(true)(
            Sui.Icon.name(SemanticICONS.recycle)
          )
        ),
        <.p(
          Sui.Button
            .labelPosition(left)
            .icon(true)(
              Sui.Icon.name(SemanticICONS.pause),
              "Pause"
            )
        ),
        Sui.Divider.horizontal(true)(
          Sui.Header.as("h4")(
            Sui.Icon.name(SemanticICONS.tag),
            "Form and Checkbox"
          )
        ),
        Sui.Form(
          Sui.FormField(
            <.label("First Name"),
            <.input(^.placeholder := "First Name")
          ),
          Sui.FormField(
            <.label("Last Name"),
            <.input(^.placeholder := "Last Name")
          ),
          Sui.FormField(
            Sui.Checkbox.label("I agree to the Terms and Conditions")
          ),
          Sui.FormField(Sui.Checkbox.label("I agree to the Cookie Policy").toggle(true)),
          Sui.Button.`type`(submit)("OK!")
        ),
        Sui.Divider.horizontal(true)(
          Sui.Header.as("h4")(Sui.Icon.name(SemanticICONS.tag), "Card and Image")
        ),
        Sui.Card(
          Sui.Image
            .size(SemanticSIZES.medium)
            .wrapped(true)
            .ui(false)
            .set("src", "https://react.semantic-ui.com/images/avatar/large/matthew.png"),
          Sui.CardContent(
            Sui.CardHeader("Matthew"),
            Sui.CardMeta(<.span(^.className := "date")("Joined in 2015")),
            Sui.CardDescription("Matthew is a musician living in Nashville.")
          ),
          Sui.CardContent.extra(true)(
            <.a(Sui.Icon.name(SemanticICONS.user), "22 Friends")
          )
        ),
        Sui.Divider.horizontal(true)(
          Sui.Header.as("h4")(Sui.Icon.name(SemanticICONS.tag), "Modal")
        ),
        <.p(Sui.Button.primary(true).onClick((_, _) => Callback(updateIsModalVisible(true)))("Show modal"))
      ),
      Sui.GridColumn.width(SemanticWIDTHSSTRING.`1`)
    ),
    Sui.Modal
      .onClose((_, _) => Callback(updateIsModalVisible(false)))
      .open(isModalVisible)(
        Sui.ModalHeader("Select a Photo"),
        Sui.ModalContent.image(true)(
          Sui.Image
            .size(SemanticSIZES.medium)
            .fluid(true)
            .wrapped(true)
            .set("src", "https://react.semantic-ui.com/images/avatar/large/rachel.png"),
          Sui.ModalDescription(
            Sui.Header("Default Profile Image"),
            <.p("We've found the following gravatar image associated with your e-mail address."),
            <.p("Is it okay to use this photo?")
          )
        )
      )
  )
}

@main
def main: Unit =
  App(()).renderIntoDOM(dom.document.getElementById("container"))
