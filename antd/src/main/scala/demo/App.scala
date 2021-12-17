package demo

import japgolly.scalajs.react.vdom.html_<^.*
import japgolly.scalajs.react.{Callback, ScalaFnComponent}
import typings.antDesignIcons.components.AntdIcon
import typings.antDesignIconsSvg.downOutlinedMod.default as DownOutlinedIcon
import typings.antDesignIconsSvg.downloadOutlinedMod.default as DownloadOutlinedIcon
import typings.antDesignIconsSvg.homeOutlinedMod.default as HomeOutlinedIcon
import typings.antDesignIconsSvg.lockTwoToneMod.default as LockTwoToneIcon
import typings.antDesignIconsSvg.mailTwoToneMod.default as MailTwoToneIcon
import typings.antDesignIconsSvg.shopOutlinedMod.default as ShopOutlinedIcon
import typings.antDesignIconsSvg.userOutlinedMod.default as UserOutlinedIcon
import typings.antd.antdStrings
import typings.antd.components.{List as AntList, *}
import typings.antd.notificationMod.{ArgsProps, IconType, default as Notification}
import typings.antd.tableInterfaceMod.{ColumnGroupType, ColumnType}
import typings.rcSelect.interfaceMod.OptionData
import typings.react.mod.{useState, CSSProperties}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("antd/dist/antd.css", JSImport.Default)
@js.native
object CSS extends js.Any

object App:

  private val css = CSS // touch to load

  val component = ScalaFnComponent[Unit] { _ =>
    val js.Tuple2(isModalVisible, updateIsModalVisible)     = useState(false)
    val js.Tuple2(selectValue, updateSelectValue)           = useState("lucy")
    val js.Tuple2(multiSelectValue, updateMultiSelectValue) = useState(List("a10", "c12"))

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
        Table[TableItem]()
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
                ColumnType[TableItem]().setTitle("Age").setDataIndex("age").setKey("age"),
                ColumnType[TableItem]().setTitle("Address").setDataIndex("address").setKey("address")
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
        Button.icon(AntdIcon(DownloadOutlinedIcon)).`type`(antdStrings.primary)("Download")
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
      Select[String]()
        .defaultValue(selectValue)
        .onChange((changedValue, _) => Callback(updateSelectValue(changedValue)))(
          Option("jack")("Jack"),
          Option("lucy")("Lucy"),
          Option("disabled")("Disabled").disabled(true),
          Option("yiminghe")("Yiminghe")
        )
    )

    val renderMultiSelect = <.section(
      <.h2("Multiple select"),
      Select[js.Array[String]]()
        .defaultValue(js.Array(multiSelectValue*))
        .mode(antdStrings.multiple)
        .onChange((changedValue, _) => Callback(updateMultiSelectValue(changedValue.toList)))(
          (10 until 36).map { n =>
            val s = s"${(n + 87).toChar}${n.toString}"
            Select.Option(s)(s).withKey(s).build
          }.toVdomArray
        )
    )

    val renderGroupSelect = <.section(
      <.h2("Select with grouped options"),
      Select[String]()
        .defaultValue(selectValue)
        .onChange((changedValue, _) => Callback(updateSelectValue(changedValue)))(
          Select.OptGroup.label("Manager")(
            Select.Option("jack")("Jack"),
            Select.Option("lucy")("Lucy")
          ),
          Select.OptGroup.label("Engineer")(
            Select.Option("yiminghe")("Yiminghe")
          )
        )
    )

    val renderIcon = <.section(<.h2("Icon"), AntdIcon(HomeOutlinedIcon))

    val renderInput = <.section(
      <.h2("Input"),
      Input
        .addonBefore(AntdIcon(UserOutlinedIcon))
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
        Form.Item(
          Input.addonBefore(AntdIcon(MailTwoToneIcon)).`type`(antdStrings.email).placeholder("input email")
        ),
        Form.Item(
          Password.addonBefore(AntdIcon(LockTwoToneIcon)).`type`(antdStrings.password).placeholder("input password")
        ),
        Form.Item(Button.htmlType(antdStrings.submit).`type`(antdStrings.primary))("Log in")
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

    def menu: VdomElement =
      Menu.onClick(mi =>
        Callback(
          Notification.open(
            ArgsProps()
              .setMessage("Selected menu item")
              .setDescription(
                s"Menu Item with key '${mi.key}' was selected"
              )
              .setType(IconType.success)
          )
        )
      )(
        MenuItem.withKey("1")("Option 1"),
        MenuItem.withKey("2")("Option 2"),
        MenuItem.withKey("3")("Option 3")
      )

    def renderDropdown: VdomElement =
      <.section(
        <.h2("Dropdown with Menu"),
        Dropdown(menu.rawElement).className("spaced")(
          Button("Dropdown Button", AntdIcon(DownOutlinedIcon))
        ),
        Dropdown(menu.rawElement)
          .triggerVarargs(antdStrings.click)
          .className("spaced")(
            Button("Dropdown Button, responds to click", AntdIcon(DownOutlinedIcon))
          )
      )

    def renderMenu =
      <.section(<.h2("Menu"), menu)

    val js.Tuple2(text, setText) = useState("")
    def renderAutocomplete =
      <.section(
        <.h2("Autocomplete"),
        AutoComplete
          .style(CSSProperties().setWidth("100%"))
          .value(text)
          .filterOption(true) // Filter options by input
          .defaultActiveFirstOption(true) // Make first option active - enter to select
          .options(
            js.Array(
              OptionData("Alphabet"),
              OptionData("Baguette").set(
                "label",
                <.span(AntdIcon(ShopOutlinedIcon), " Baguette").rawElement
              ), // Set label as a ReactElement for customised display
              OptionData("Bicycle"),
              OptionData("Croissant")
            )
          )
          .onChange { case (text, _) => Callback(setText(text)) }
      )

    def renderFooter =
      <.div(^.height := "100px")

    val renderAvatar = <.section(
      <.h2("Avatar"),
      Avatar.size(antdStrings.large).icon(AntdIcon(UserOutlinedIcon))
    )

    val renderBadge = <.section(
      <.h2("Badge"),
      Badge.count(5)(Button("badge"))
    )

    val renderComment = <.section(
      <.h2("Comment"),
      Comment
        .author("Author")
        .avatar(Avatar.size(antdStrings.large).icon(AntdIcon(UserOutlinedIcon)))
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
              .avatar(Avatar.icon(AntdIcon(UserOutlinedIcon)))
              .title("Title")
              .description("Description")
          ): VdomElement

        AntList()(Seq.fill(3)(item)*)
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
          renderMultiSelect,
          renderGroupSelect,
          renderIcon,
          renderInput,
          renderPassword,
          renderSpin,
          renderForm,
          renderCoordinated,
          renderNotification,
          renderDropdown,
          renderMenu,
          renderAutocomplete,
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
        renderFooter,
        Col.span(2)
      )
    )
  }
end App
