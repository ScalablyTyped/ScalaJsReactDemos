package demo

import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import typings.antDesignIcons.components.AntdIcon
import typings.antDesignIconsSvg.{mod => Icons}
import typings.antd.antdStrings
import typings.antd.components.{List => AntList, _}
import typings.antd.notificationMod.{ArgsProps, IconType, default => Notification}
import typings.antd.tableInterfaceMod.{ColumnGroupType, ColumnType}
import typings.react.mod.useState

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object CSS extends js.Any

object App {

  private val css = CSS // touch to load

  val component = ScalaFnComponent[Unit] { _ =>
    val js.Tuple2(isModalVisible, updateIsModalVisible) = useState(false)
    val js.Tuple2(selectValue, updateSelectValue) = useState("lucy")

    val renderIntro = Row(
      Col.span(7),
      Col.span(10)(
        <.header(^.className := "App-header")(<.h1(^.className := "App-title")("Welcome to React (with Scala.js!)")),
        <.p(^.className := "App-intro")("To get started, edit ", <.code("App.scala"), " and save to reload.")
      ),
      Col.span(7)
    )

    def renderGrid =
      <.section(
        <.h2("Grid"),
        Row(
          Col.span(12)(<.div(^.className := "block blue1")("col-12")),
          Col.span(12)(<.div(^.className := "block blue2")("col-12"))
        ),
        Row(
          Col.span(8)(<.div(^.className := "block blue1")("col-8")),
          Col.span(8)(<.div(^.className := "block blue2")("col-8")),
          Col.span(8)(<.div(^.className := "block blue1")("col-8"))
        ),
        Row(
          Col.span(6)(<.div(^.className := "block blue1")("col-6")),
          Col.span(6)(<.div(^.className := "block blue2")("col-6")),
          Col.span(6)(<.div(^.className := "block blue1")("col-6")),
          Col.span(6)(<.div(^.className := "block blue2")("col-6"))
        )
      )

    def renderTag =
      <.section(
        <.h2("Tag"),
        Tag("Tag 1"),
        Tag.color(antdStrings.red)("red"),
        Tag.CheckableTag(true)("Checkable")
      )

    class TableItem(val key: Int, val name: String, val age: Int, val address: String) extends js.Object

    def renderTable =
      <.section(
        <.h2("Table"),
        Table[TableItem]
          .bordered(true)
          .dataSourceVarargs(
            new TableItem(1, "Mike", 32, "10 Downing St."),
            new TableItem(2, "John", 42, "10 Downing St.")
          )
          .columnsVarargs(
            ColumnType[TableItem]()
              .setTitle("Name")
              .setDataIndex("name")
              .setKey("name")
              .setRender((_, tableItem, _) => Tag(tableItem.name).rawElement),
            // TODO: is a varargs constructor missing here?
            ColumnGroupType[TableItem](
              scala.scalajs.js.Array(
                ColumnType[TableItem].setTitle("Age").setDataIndex("age").setKey("age"),
                ColumnType[TableItem].setTitle("Address").setDataIndex("address").setKey("address")
              )
            ).setTitle("Age & Address")
          )
      )

    val renderAlert = <.section(
      <.h2("Alert"),
      Alert
        .message("Success Tips")
        .description("Detailed description and advice about successful copywriting.")
        .`type`(antdStrings.success)
        .showIcon(true)
    )

    val renderButton =
      <.section(
        <.h2("Button"),
        Button.icon(AntdIcon(Icons.DownloadOutlined)).`type`(antdStrings.primary)("Download")
      )

    val renderModal = <.section(
      <.h2("Modal"),
      Button.onClick(_ => Callback(updateIsModalVisible(true)))("Open modal"),
      Modal
        .visible(isModalVisible)
        .title("Basic modal")
        .onCancel(_ => Callback(updateIsModalVisible(false)))
        .onOk(_ => Callback(updateIsModalVisible(false)))(
          <.p("Some contents..."),
          <.p("Some contents..."),
          <.p("Some contents...")
        )
    )

    val renderSelect = <.section(
      <.h2("Select"),
      Select[String]
        .defaultValue(selectValue)
        .onChange((changedValue, _) => Callback(updateSelectValue(changedValue)))(
          Option("jack")("Jack"),
          Option("lucy")("Lucy"),
          Option("disabled")("Disabled").disabled(true),
          Option("yiminghe")("Yiminghe")
        )
    )

    val renderIcon = <.section(<.h2("Icon"), AntdIcon(Icons.HomeOutlined))

    val renderInput = <.section(
      <.h2("Input"),
      Input
        .addonBefore(AntdIcon(Icons.UserOutlined))
        .placeholder("Basic usage")
        .onChange(event => Callback.log(event.target.value))
    )

    val renderPassword =
      <.section(<.h2("Password Input"), Password.addonBefore("Password").placeholder("input password"))

    val renderSpin = <.section(
      <.h2("Spin"),
      Spin
        .size(antdStrings.large)
        .spinning(true)(
          Alert
            .message("Alert message title")
            .description("Further details about the context of this alert.")
            .`type`(antdStrings.info)
            .showIcon(true)
        )
    )

    val renderForm = <.section(
      <.h2("Form"),
      Form.onFinish(store => Callback.log("Form submitted", store))(
        FormItem(
          Input.addonBefore(AntdIcon(Icons.MailTwoTone)).`type`(antdStrings.email).placeholder("input email")
        ),
        FormItem(
          Password.addonBefore(AntdIcon(Icons.LockTwoTone)).`type`(antdStrings.password).placeholder("input password")
        ),
        FormItem(Button.htmlType(antdStrings.submit).`type`(antdStrings.primary))("Log in")
      )
    )

    val renderCoordinated =
      <.section(<.h2("Form coordinated controls"), CoordinatedDemo.component("write note here"))

    val renderNotification = <.section(
      <.h2("Notification"),
      Button.onClick(_ =>
        Callback(
          Notification.open(
            ArgsProps()
              .setMessage("Notification Title")
              .setDescription(
                "This is the content of the notification. This is the content of the notification. This is the content of the notification."
              )
              .setType(IconType.success)
          )
        )
      )("Show notification")
    )

    val renderAvatar = <.section(
      <.h2("Avatar"),
      Avatar.size(antdStrings.large).icon(AntdIcon(Icons.UserOutlined))
    )

    val renderBadge = <.section(
      <.h2("Badge"),
      Badge.count(5)(Button("badge"))
    )

    val renderComment = <.section(
      <.h2("Comment"),
      Comment
        .author("Author")
        .avatar(Avatar.size(antdStrings.large).icon(AntdIcon(Icons.UserOutlined)))
        .content("Comment")
        .actionsVarargs(Button("Like").rawElement)
    )

    val renderCollapse = <.section(
      <.h2("Collapse"),
      Collapse(
        Collapse.Panel.header("Panel1")("Collapsable Content"),
        Collapse.Panel.header("Panel2")("Collapsable Content"),
        Collapse.Panel.header("Panel3")("Collapsable Content")
      )
    )

    val renderCarousel = <.section(
      <.h2("Carousel"),
      Carousel(
        <.h3(1),
        <.h3(2),
        <.h3(3)
      )
    )

    val renderCard = <.section(
      <.h2("Card"),
      Space(
        Card
          .title("Card With Meta")
          .cover(<.img(^.src := "https://os.alipayobjects.com/rmsportal/QBnOOoLaAfKPirc.png", ^.maxWidth := "200px"))(
            Card.Meta.title("Meta Title").description("Meta Description")
          ),
        Card.title("Card With Grid")(
          Card.Grid("Content"),
          Card.Grid("Content"),
          Card.Grid("Content")
        )
      )
    )

    val renderCalendar = <.section(
      <.h2("Calendar"),
      Calendar()
    )

    val renderDescriptions = <.section(
      <.h2("Descriptions"),
      Descriptions
        .title("Descriptions")
        .bordered(true)(
          Descriptions.Item.label("Product")("Cloud Database"),
          Descriptions.Item.label("Billing Mode")("Prepaid")
        )
    )

    val renderEmpty = <.section(
      <.h2("Empty"),
      Empty()
    )

    val renderList = <.section(
      <.h2("List"), {
        def item =
          AntList.Item(
            AntList.Item.Meta
              .avatar(Avatar.icon(AntdIcon(Icons.UserOutlined)))
              .title("Title")
              .description("Description")
          ): VdomElement

        AntList()(Seq.fill(3)(item): _*)
      }
    )

    val renderPopover = <.section(
      <.h2("Popover"),
      Popover
        .title("Title")
        .content("Content")(
          Button("Hover Me")
        )
    )

    val renderStatistic = <.section(
      <.h2("Statistic"),
      Statistic.title("Statistic").value(12.345).precision(2),
      Countdown.title("Countdown").value(456)
    )

    val renderTooltip = <.section(
      <.h2("Tooltip"),
      Tooltip.TooltipPropsWithOverlayRefAttributes.title("Tooltip")(<.span("Hover me"))
    )

    val renderTimeline = <.section(
      <.h2("Timeline"),
      Timeline(
        Timeline.Item("Item 1"),
        Timeline.Item("Item 2"),
        Timeline.Item("Item 3")
      )
    )

    val renderTabs = <.section(
      <.h2("Tabs"),
      Tabs(
        Tabs.TabPane.tab("Tab 1").withKey("tab1")("Content 1"),
        Tabs.TabPane.tab("Tab 2").withKey("tab2")("Content 2"),
        Tabs.TabPane.tab("Tab 3").withKey("tab3")("Content 3")
      )
    )

    <.div(^.className := "App")(
      renderIntro,
      Row(
        Col.span(2),
        Col.span(20)(
          renderGrid,
          renderTag,
          renderTable,
          renderAlert,
          renderButton,
          renderModal,
          renderSelect,
          renderIcon,
          renderInput,
          renderPassword,
          renderSpin,
          renderForm,
          renderCoordinated,
          renderNotification,
          renderAvatar,
          renderBadge,
          renderComment,
          renderCollapse,
          renderCarousel,
          renderCard,
          renderCalendar,
          renderDescriptions,
          renderEmpty,
          renderList,
          renderPopover,
          renderStatistic,
          renderTooltip,
          renderTimeline,
          renderTabs
        ),
        Col.span(2)
      )
    )
  }
}
