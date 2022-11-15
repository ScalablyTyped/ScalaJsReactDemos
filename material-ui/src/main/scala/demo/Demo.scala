package demo

import demo.album.Album
import demo.button.{ButtonTest, SelectDemo, StyledButtonDemo, StyledButtonHooksDemo}
import demo.components.AppTheme
import demo.customization.{DarkTheme, Palette}
import demo.dashboard.Dashboard
import demo.login.Login
import demo.signin.SignIn
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalajs.dom
import typings.materialUiCore.components.{List, ListItem, ListItemIcon, ListItemText, ListSubheader, Typography}
import typings.materialUiCore.stylesCreateMuiThemeMod.{Theme, ThemeOptions}
import typings.materialUiCore.stylesCreateTypographyMod.TypographyOptions
import typings.materialUiCore.stylesMod.createMuiTheme
import typings.materialUiCore.typographyTypographyMod.Style
import typings.materialUiIcons.components as Icon
import typings.materialUiStyles.components.ThemeProvider
import typings.react.components.Fragment
import typings.reactRouter.mod.RouteProps
import typings.reactRouterDom.components.{BrowserRouter, Link, Route}

val theme: Theme = createMuiTheme(
  ThemeOptions()
    .setTypography(
      TypographyOptions().setUseNextVariants(true)
    ) // https://v3.material-ui.com/style/typography/#migration-to-typography-v2
)

/* the production build is deployed at github pages under /material-ui , while dev build is server from root of webpack-dev-server */
val basename = if scala.scalajs.runtime.linkingInfo.productionMode then "/ScalajsReactDemos/material-ui/" else ""

val Main = ScalaFnComponent[Unit] { case () =>
  ThemeProvider(theme)(
    BrowserRouter.basename(basename)(
      Route(
        RouteProps()
          .setExact(true)
          .setPath("/")
          .setRender(_ =>
            List(
              ListSubheader.inset(true)(""),
              Link[String](to = "/dashboard")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Dashboard"))
              ),
              Link[String](to = "/album")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Album"))
              ),
              Link[String](to = "/signin")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Sign In"))
              ),
              Link[String](to = "/login")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Login"))
              ),
              Link[String](to = "/button")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Buttons"))
              ),
              Link[String](to = "/select")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Select"))
              ),
              Link[String](to = "/customization")(
                ListItem.button(true)(ListItemIcon(Icon.Assignment()), ListItemText.primary("Customization"))
              )
            ).rawElement
          )
      ),
      Route(
        RouteProps()
          .setPath("/dashboard")
          .setRender(_ =>
            Fragment(
              AppTheme.component(
                AppTheme.Props(
                  title = "Dashboard page layout example - Material-UI",
                  description = "An example layout for creating an albumn.",
                  hideCredit = true
                )
              )(Dashboard.component())
            ).rawElement
          )
      ),
      Route(
        RouteProps()
          .setPath("/album")
          .setRender(_ =>
            Fragment(
              AppTheme.component(
                AppTheme.Props(
                  title = "Album page layout - Material-UI",
                  description = "An example layout for creating an album or gallery."
                )
              )(Album.component())
            ).rawElement
          )
      ),
      Route(
        RouteProps()
          .setPath("/signin")
          .setRender(_ =>
            Fragment(
              AppTheme.component(
                AppTheme.Props(
                  title = "Sign-in page layout example - Material-UI",
                  description = "An example layout for creating a sign-in page."
                )
              )(SignIn.component())
            ).rawElement
          )
      ),
      Route(
        RouteProps()
          .setPath("/login")
          .setRender(_ => Login.component().rawElement)
      ),
      Route(
        RouteProps()
          .setPath("/button")
          .setRender(_ =>
            Fragment(
              Typography.variant(Style.h4).gutterBottom(true).component("h2")("Buttons"),
              ButtonTest.component("dear user"),
              StyledButtonDemo.component(),
              StyledButtonHooksDemo.component()
            ).rawElement
          )
      ),
      Route(
        RouteProps()
          .setPath("/select")
          .setRender(_ =>
            Fragment(
              Typography.variant(Style.h4).gutterBottom(true).component("h2")("Select"),
              SelectDemo.component(scala.List("one", "two", "three"))
            ).rawElement
          )
      ),
      Route(
        RouteProps()
          .setPath("/customization")
          .setRender(_ =>
            Fragment(
              Typography.variant(Style.h4).gutterBottom(true).component("h2")("Customization"),
              DarkTheme(),
              Palette.component()
            ).rawElement
          )
      )
    )
  )
}

@main
def main: Unit =
  println("starting")
  Main().renderIntoDOM(dom.document.getElementById("container"))
