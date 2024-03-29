package demo.signin

import demo.StyleBuilder
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import typings.csstype.csstypeStrings.*
import typings.materialUiCore.components.*
import typings.materialUiCore.stylesCreateMuiThemeMod.Theme
import typings.materialUiCore.materialUiCoreStrings.{contained, normal, primary, submit}
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiIcons.components as Icons
import typings.materialUiStyles.makeStylesMod.StylesHook
import typings.materialUiStyles.withStylesMod.{CSSProperties, Styles}

import scala.scalajs.js

// https://v3.material-ui.com/getting-started/page-layout-examples/sign-in/
// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/sign-in/SignIn.js
object SignIn:

  lazy val styles: StylesHook[Styles[Theme, js.Object, String]] =
    StyleBuilder[Theme, js.Object]
      .add(
        "main",
        theme =>
          CSSProperties()
            .setWidth("auto")
            .setDisplay(block)
            .setMarginLeft(theme.spacing.unit * 3)
            .setMarginRight(theme.spacing.unit * 3)
            .set(
              theme.breakpoints.up(400 + theme.spacing.unit * 2 * 2),
              CSSProperties()
                .setWidth(400)
                .setMarginLeft("auto")
                .setMarginRight("auto")
            )
      )
      .add(
        "paper",
        theme =>
          CSSProperties()
            .setMarginTop(theme.spacing.unit * 8)
            .setDisplay(flex)
            .setFlexDirection(column)
            .setAlignItems(center)
            .setPadding(s"${theme.spacing.unit * 2}px ${theme.spacing.unit * 3}px ${theme.spacing.unit * 3}px")
      )
      .add(
        "avatar",
        theme =>
          CSSProperties()
            .setMargin(theme.spacing.unit)
            .setBackgroundColor(theme.palette.secondary.main)
      )
      .add("form", theme => CSSProperties().setWidth("100%").setMarginTop(theme.spacing.unit))
      .add("submit", theme => CSSProperties().setMarginTop(theme.spacing.unit * 3))
      .hook

  val component = ScalaFnComponent[Unit] { case () =>
    val classes = styles(js.undefined)

    <.main(^.className := classes("main"))(
      CssBaseline(),
      Paper.className(classes("paper"))(
        Avatar.className(classes("avatar"))(Icons.LockOutlined()),
        Typography.variant(Style.h5).component("h1")("Sign in"),
        <.form(^.className := classes("form"))(
          FormControl
            .margin(normal)
            .required(true)
            .fullWidth(true)(
              InputLabel.htmlFor("email")("Email Address"),
              Input.id("email").name("email").autoComplete("email").autoFocus(true)
            ),
          FormControl
            .margin(normal)
            .required(true)
            .fullWidth(true)(
              InputLabel.htmlFor("password")("Password"),
              Input.id("password").name("password").autoComplete("current-password")
            ),
          FormControlLabel(Checkbox.value("remember").color(primary)).label("Remember Me"),
          Button
            .`type`(submit)
            .fullWidth(true)
            .variant(contained)
            .color(primary)
            .className(classes("submit"))("Sign in")
        )
      )
    )
  }
end SignIn
