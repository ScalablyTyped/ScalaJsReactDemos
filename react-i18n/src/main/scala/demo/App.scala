package demo

import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^._
import typings.react.mod.CSSProperties
import typings.reactI18next.mod.useTranslation
import typings.reactI18next.components.Trans

import scala.scalajs.js

object App {
  val component = ScalaFnComponent[Unit] { _ =>
    val js.Tuple3(t, i18n, _) = useTranslation()
    val index = 11
    <.div(^.className := "App")(
      <.div(^.className := "App-header")(
        // note: `t` on the line under needs type parameters in order to not run into a `ClassCastException`.
        // Better write a small facade around it to constrain the interface a bit if you want to use it
        <.h2(t[String, String, js.Object]("Welcome to React")),
        <.button(^.onClick --> Callback(i18n.changeLanguage(I18n.de)), "de"),
        <.button(^.onClick --> Callback(i18n.changeLanguage(I18n.en)), "en")
      ),
      <.div(^.className := "App-intro")(
        Trans()("To get started, edit ", <.code("src/App.js"), " and save to reload."),
        Trans.i18nKey("welcome")("trans"),
        Trans()(index + 1, <.a("xxx"))
      ),
      <.div(^.style := CSSProperties().setMarginTop(40))(
        "Learn more ",
        <.a(^.href := "https://react.i18next.com")("https://react.i18next.com")
      )
    )
  }
}
