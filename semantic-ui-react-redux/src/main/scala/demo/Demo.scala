package demo

import demo.ReduxFacade.Connected
import japgolly.scalajs.react.raw.React
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{CallbackTo, ScalaFnComponent}
import typings.semanticUiReact.anon.MenuItem
import typings.semanticUiReact.cardCardMod.CardProps
import typings.semanticUiReact.headerHeaderMod.HeaderProps
import typings.semanticUiReact.progressProgressMod.ProgressProps
import typings.semanticUiReact.searchSearchMod.SearchProps
import typings.semanticUiReact.semanticUiReactStrings.large
import typings.semanticUiReact.tabTabMod.TabProps
import typings.semanticUiReact.{semanticUiReactStrings, components => Sui}

import scala.scalajs.js

object Demo {

  val CardDemo: React.Element =
    Sui.Card.withProps(CardProps().setColor(semanticUiReactStrings.orange))(
      Sui.CardHeader("CardHeader"),
      Sui.CardMeta("CardMeta"),
      Sui.Divider(),
      Sui.Search.withProps(SearchProps().setMinCharacters(1))
    ).rawElement

  val ProgressDemo: React.Element =
    Sui.Card(
      Sui.Progress.withProps(ProgressProps().setPercent(70).setWarning(true)),
      Sui.Progress.withProps(ProgressProps().setPercent(100).setWarning(false)),
    ).rawElement


  class Props(val title: String) extends js.Object


  val component = ScalaFnComponent[Connected[GithubSearch.State, GithubSearch.SearchAction] with Props] { props =>
    <.div(
      Sui.Header.withProps(HeaderProps().setSize(large))(props.title),
      Sui.Tab.withProps(TabProps().setPanes(js.Array(
        MenuItem()
          .setMenuItem("Repo search")
          .setRender(CallbackTo(GithubSearch.component(GithubSearch.Props(props.state, props.dispatch)))),
        MenuItem()
          .setMenuItem("Card")
          .setRender(CallbackTo(CardDemo)),
        MenuItem()
          .setMenuItem("Progress")
          .setRender(CallbackTo(ProgressDemo))
      )))
    )
  }

}
