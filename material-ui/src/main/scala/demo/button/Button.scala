package demo.button

import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import org.scalablytyped.runtime.StringDictionary
import org.scalajs.dom
import typings.csstype.mod.{ColorProperty, NamedColor}
import typings.materialUiCore.createMuiThemeMod.{Theme, ThemeOptions}
import typings.materialUiCore.spacingMod.SpacingOptions
import typings.materialUiCore.{stylesMod, components => Mui}
import typings.materialUiStyles.components.ThemeProvider
import typings.react.mod.useState
import typings.std.global.window

import scala.scalajs.js

object Button {

  val theme: Theme = stylesMod.createMuiTheme(ThemeOptions().setSpacing(SpacingOptions().setUnit(2)))

  // theme passed through react context and used in StyledButtonHooksDemo
  ThemeProvider(theme)(
    <.div(
      ButtonTest.component("dear user"),
      SelectDemo.component(List("one", "two", "three")),
      StyledButtonDemo.component(),
      StyledButtonHooksDemo.component()
    )
  ).renderIntoDOM(dom.document.getElementById("container"))
}

object ButtonTest {

  val component = ScalaFnComponent[String] { name =>
    /* use a hook to keep state */
    val js.Tuple2(state, setState) = useState(1)

    val incrementButton = Mui.Button.onClick(_ => Callback(setState(state + 1)))(
      s"Increment it, ${name}"
    )

    <.div(
      /* text field controlled by the value of the state hook above*/
      Mui.TextField.StandardTextFieldProps().value(state).disabled(true),
      incrementButton
    )
  }
}

object SelectDemo {

  val component = ScalaFnComponent[List[String]] {
    case values =>
      val js.Tuple2(chosen, setChosen) = useState[String](values.head)

      val items = values.zipWithIndex.map {
        case (value, idx) => Mui.MenuItem.value(value).withKey(idx.toString)(value): VdomNode
      }
      <.div(
        Mui.Select
          .value(chosen)
          .onChange((e, _) => Callback(setChosen(e.target.value)))(items: _*),
        Mui.TextField
          .StandardTextFieldProps()
          .value(chosen)
          .disabled(true)
      )
  }
}

object StyledButtonDemo {

  val component = ScalaFnComponent[Unit] { _ =>
    val usingWithStyles = {
      import typings.materialUiCore.withStylesMod.{CSSProperties, WithStylesOptions}

      val styleInjector =
        stylesMod.withStyles(
          StringDictionary("root" -> CSSProperties().setBackgroundColor(NamedColor.blue)),
          WithStylesOptions[String]()
        )

      Mui.Button
        .withComponent(c => styleInjector(c).asInstanceOf[js.Any])
        .onClick(_ => Callback(window.alert("clicked")))("using withStyles")
    }

    val usingReactCss = {
      import typings.react.mod.CSSProperties
      Mui.Button
        .style(CSSProperties().setBackgroundColor(NamedColor.darkred))
        .onClick(_ => Callback(window.alert("clicked")))("direct css")
    }

    <.div(usingWithStyles, usingReactCss)
  }
}

// https://v3.material-ui.com/css-in-js/basics/
object StyledButtonHooksDemo {

  import typings.materialUiStyles.makeStylesMod.StylesHook
  import typings.materialUiStyles.mod.makeStyles
  import typings.materialUiStyles.withStylesMod.{CSSProperties, StyleRulesCallback, Styles, WithStylesOptions}

  class StyleProps(val favouriteColor: ColorProperty) extends js.Object

  val useStyles: StylesHook[Styles[Theme, StyleProps, String]] = {
    val root: js.Function1[StyleProps, CSSProperties] = props =>
      CSSProperties()
        .setBackground("linear-gradient(45deg, #FE6B8B 30%, #FF8E53 90%)")
        .setBorder(0)
        .setBorderRadius(3)
        .setBoxShadow("0 3px 5px 2px rgba(255, 105, 135, .3)")
        .setColor(props.favouriteColor)
        .setHeight(48)
        .setPadding("0 30px")

    /* If you don't need direct access to theme, this could be `StyleRules[Props, String]` */
    val stylesCallback: StyleRulesCallback[Theme, StyleProps, String] = theme =>
      StringDictionary(
        "root" -> root,
        "outer" -> CSSProperties().setPadding(s"${theme.spacing.unit * 3}px")
      )

    makeStyles(stylesCallback, WithStylesOptions())
  }

  val component = ScalaFnComponent[Unit] { _ =>
    val classes = useStyles(new StyleProps(NamedColor.green))
    <.div(
      ^.className := classes("outer"),
      Mui.Button
        .className(classes("root"))
        .onClick(_ => Callback.alert("clicked"))("styles module with hook")
    )
  }
}
