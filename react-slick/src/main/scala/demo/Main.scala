package demo

import org.scalajs.dom.document
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import japgolly.scalajs.react.vdom.html_<^.*
import typings.react.mod.useState
import typings.reactSlick.components.ReactSlick

import scala.scalajs.js

object Main:

  case class State(selectedIdx: Option[Int])

  val SlickTest =
    ScalaFnComponent[js.Array[String]] { images =>
      val js.Tuple2(state, setState) = useState(State(None))

      def myOnClick(idx: Int) =
        Callback {
          println(s"clicked image $idx")
          setState(State(Some(idx)))
        }

      val renderedImages: js.Array[VdomElement] =
        images.zipWithIndex.map { case (source, idx) =>
          <.img(^.key := idx, ^.src := source, ^.onClick --> myOnClick(idx))
        }

      <.div(
        ^.style := js.Dynamic.literal(position = "relative", left = "200px", width = 500, height = 500),
        <.label(
          ^.style := js.Dynamic.literal(color = "blue"),
          s"Selected image index: ${state.selectedIdx.fold("none")(_.toString)}"
        ),
        ReactSlick
          .onInit(Callback.info("slick init"))
          .dots(true)
          .autoplay(true)
          .autoplaySpeed(1000)
          .slidesToShow(2)(renderedImages.to(Seq)*)
      )
    }

  def main(argv: Array[String]): Unit =
    SlickTest(
      js.Array(
        "https://i.pinimg.com/474x/a8/30/69/a8306979f24cbf615e1cc0a635ceb384.jpg",
        "https://i.pinimg.com/474x/b0/15/4c/b0154cfc2fe3a664ac8f679df4debf56.jpg",
        "https://i.imgur.com/FqeTKrS.jpg",
        "https://static.boredpanda.com/blog/wp-content/uploads/2019/11/cat-fluffy-squirrel-tail-bell-7-5dca63b7b11a8__700.jpg",
        "https://i.chzbgr.com/full/9428254976/hD3DA6B8F/cat"
      )
    ).renderIntoDOM(document.body)
end Main
