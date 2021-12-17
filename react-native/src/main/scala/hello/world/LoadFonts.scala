package hello.world

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.Implicits.*
import org.scalablytyped.runtime.StringDictionary
import typings.expo.components.AppLoading
import typings.expoFont.fontTypesMod.FontSource
import typings.expoFont.mod as Font
import typings.react.mod.{useEffect, useState}
import typings.reactNative.components.Text

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.util.{Failure, Success}

/* we must load these fonts manually to use antd design */
object LoadFonts:
  object Fonts:
    @JSImport("../../node_modules/@ant-design/icons-react-native/fonts/antoutline.ttf", JSImport.Namespace)
    @js.native
    val AntdIconOutline: FontSource = js.native

    @JSImport("../../node_modules/@ant-design/icons-react-native/fonts/antfill.ttf", JSImport.Namespace)
    @js.native
    val AntdIconFill: FontSource = js.native

    val All: StringDictionary[FontSource] = StringDictionary(
      "antoutline" -> AntdIconOutline,
      "antfill" -> AntdIconFill
    )
  end Fonts

  sealed trait State
  object State:
    case object Loading extends State
    case class Error(msg: String) extends State
    case object Success extends State

  val component = ScalaFnComponent[Unit] { case () =>
    val js.Tuple2(state, setState) = useState(State.Loading: State)

    useEffect(
      () =>
        Font
          .loadAsync(Fonts.All)
          .toFuture
          .onComplete {
            case Failure(exception) => setState(State.Error(exception.getMessage))
            case Success(_)         => setState(State.Success)
          },
      js.Array()
    )

    state match
      case State.Loading    => AppLoading.AutoHideSplash()
      case State.Error(msg) => Text(s"Could not load fonts: $msg")
      case State.Success    => App()
  }
end LoadFonts
