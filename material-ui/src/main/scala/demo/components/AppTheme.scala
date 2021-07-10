package demo.components

import demo.StyleBuilder
import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalablytyped.runtime.StringDictionary
import typings.classnames.mod as classNames
import typings.csstype.csstypeStrings.absolute
import typings.materialUiCore.colorManipulatorMod.darken
import typings.materialUiCore.colorsMod.{blue, pink}
import typings.materialUiCore.components.*
import typings.materialUiCore.createMuiThemeMod.{Direction, Theme, ThemeOptions}
import typings.materialUiCore.createPaletteMod.{ColorPartial, PaletteColorOptions, PaletteOptions}
import typings.materialUiCore.createTypographyMod.TypographyOptions
import typings.materialUiCore.materialUiCoreStrings.{center, textSecondary}
import typings.materialUiCore.mod.PropTypes.Color
import typings.materialUiCore.stylesMod.createMuiTheme
import typings.materialUiStyles.makeStylesMod.StylesHook
import typings.materialUiStyles.withStylesMod.{CSSProperties, Styles}

import scala.scalajs.js

// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/modules/components/AppTheme.js
object AppTheme:

  // https://github.com/mui-org/material-ui/blob/v3.x/docs/src/modules/styles/themeInitialState.js
  val theme: Theme = createMuiTheme(
    ThemeOptions()
      .setDirection(Direction.ltr)
      .setTypography(TypographyOptions().setUseNextVariants(true))
      .setPalette(
        PaletteOptions()
          .setPrimary(ColorPartial().combineWith(blue))
          .setSecondary(
            PaletteColorOptions.SimplePaletteColorOptions(darken(pink.A400, 0.08))
          ) // Darken so we reach the AA contrast ratio level.
      )
  )

  lazy val styles: StylesHook[Styles[Theme, js.Object, String]] =
    StyleBuilder[Theme, js.Object]
      .add(
        "credit",
        theme => CSSProperties().setMarginTop(theme.spacing.unit * 6).setMarginBottom(theme.spacing.unit * 4)
      )
      .add("hideCredit", CSSProperties().setPosition(absolute).set("top", 0))
      .hook

  case class Props(description: String, hideCredit: Boolean = false, title: String)

  val component = ScalaFnComponent.withChildren[Props] { case (props, children) =>
    val classes = styles(js.undefined)
    MuiThemeProvider(theme)(
      children,
      Typography
        .color(textSecondary)
        .align(center)
        .className(
          classNames(StringDictionary[js.Any](classes("credit") -> true, classes("hideCredit") -> props.hideCredit))
        )(
          "Built with ",
          <.span(^.role := "img", ^.aria.label := "Love")("❤️"),
          " by the ",
          Link.color(Color.inherit).href("www.scalablytyped.org")("ScalablyTyped Material-UI"),
          " team."
        )
    )
  }
end AppTheme
