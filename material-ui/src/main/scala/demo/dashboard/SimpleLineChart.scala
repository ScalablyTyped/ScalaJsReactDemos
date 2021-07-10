package demo.dashboard

import japgolly.scalajs.react.ScalaFnComponent
import japgolly.scalajs.react.vdom.html_<^.*
import org.scalablytyped.runtime.StringDictionary
import typings.recharts.components.*
import typings.recharts.rechartsStrings.monotone

import scala.scalajs.js

// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/dashboard/SimpleLineChart.js
object SimpleLineChart:

  class Data(val Name: String, val Visits: Int, val Orders: Int) extends js.Object

  val data: js.Array[js.Object] = js.Array(
    new Data("Mon", 200, 3400),
    new Data("Tue", 128, 2398),
    new Data("Wed", 5000, 4300),
    new Data("Thu", 4780, 2908),
    new Data("Fri", 5890, 4800),
    new Data("Sat", 4390, 3800),
    new Data("Sun", 4490, 4300)
  )

  val component = ScalaFnComponent[Unit] { case () =>
    ResponsiveContainer
      .width("99%")
      .height(320)(
        LineChart.data(data)(
          XAxis.dataKey("Name"),
          YAxis(),
          CartesianGrid.vertical(false).strokeDasharray("3 3"),
          Tooltip(),
          Legend(),
          Line("Visits").`type`(monotone).stroke("#82ca9d"),
          Line("Orders").`type`(monotone).stroke("#8884d8").activeDot(StringDictionary("r" -> 8))
        )
      )
  }
end SimpleLineChart
