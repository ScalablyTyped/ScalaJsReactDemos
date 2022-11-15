package demo.customization

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import typings.materialUiCore.colorsMod.purple
import typings.materialUiCore.components.{Button, MuiThemeProvider}
import typings.materialUiCore.stylesCreateMuiThemeMod.{Theme, ThemeOptions}
import typings.materialUiCore.stylesCreatePaletteMod.{PaletteColorOptions, PaletteOptions}
import typings.materialUiCore.stylesCreateTypographyMod.TypographyOptions
import typings.materialUiCore.mod.PropTypes.Color
import typings.materialUiCore.stylesMod

// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/customization/themes/Palette.js
object Palette:

  val theme: Theme = stylesMod
    .createMuiTheme(
      ThemeOptions()
        .setTypography(TypographyOptions().setUseNextVariants(true))
        .setPalette(
          PaletteOptions()
            .setPrimary(
              PaletteColorOptions.SimplePaletteColorOptions(purple.`500`)
            ) // Purple and green play nicely together.
            .setSecondary(PaletteColorOptions.SimplePaletteColorOptions("#11cb5f")) // This is just green.A700 as hex.
        )
    )

  val component = ScalaFnComponent[Unit] { _ =>
    MuiThemeProvider(theme)(
      Button.color(Color.primary)(<.span("Primary")),
      Button.color(Color.secondary)(<.span("Secondary"))
    )
  }
end Palette
