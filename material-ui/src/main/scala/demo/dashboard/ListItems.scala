package demo.dashboard

import japgolly.scalajs.react.vdom.html_<^._
import typings.materialUiCore.components.{ListItem, ListItemIcon, ListItemText, ListSubheader}
import typings.materialUiIcons.{components => Icon}

// https://github.com/mui-org/material-ui/blob/v3.x/docs/src/pages/getting-started/page-layout-examples/dashboard/listItems.js
object ListItems {

  val mainListItems: VdomElement =
    <.div(
      ListItem.button(true)(
        ListItemIcon(Icon.Dashboard()),
        ListItemText.primary("Dashboard")
      ),
      ListItem.button(true)(
        ListItemIcon(Icon.ShoppingCart()),
        ListItemText.primary("Orders")
      ),
      ListItem.button(true)(
        ListItemIcon(Icon.People()),
        ListItemText.primary("Customers")
      ),
      ListItem.button(true)(
        ListItemIcon(Icon.BarChart()),
        ListItemText.primary("Reports")
      ),
      ListItem.button(true)(
        ListItemIcon(Icon.Layers()),
        ListItemText.primary("Integrations")
      )
    )

  val secondaryListItems: VdomElement =
    <.div(
      ListSubheader.inset(true)("Saved reports"),
      ListItem.button(true)(
        ListItemIcon(Icon.Assignment()),
        ListItemText.primary("Current month")
      ),
      ListItem.button(true)(
        ListItemIcon(Icon.Assignment()),
        ListItemText.primary("Last quarter")
      ),
      ListItem.button(true)(
        ListItemIcon(Icon.Assignment()),
        ListItemText.primary("Year-end sale")
      )
    )
}
