package com.company.view

import com.company.app.Styles
import com.company.controller.GlobalController
import com.company.controller.PerformanceAsHolder
import com.company.controller.UpdateToDbEvent
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import javafx.event.EventTarget
import org.fxmisc.richtext.StyleClassedTextArea
import tornadofx.*

class PerformanceAsView(performanceAsHolder: PerformanceAsHolder) : Fragment() {

    override val root = vbox(5) {

        runAsync {

            return@runAsync performanceAsHolder.listOne

        } ui {

            children.bind(it) {
                anchorpane {

                    val editScope = ItemJSONScope()
                    editScope.model = it
                    val perfAnchors = find(PerformanceAsItemView::class, editScope).root
                    this += perfAnchors

                }
            }
        }

        paddingRight = 5

        addClass(Styles.performanceAsStyle)

    }
}

class PerformanceAsItemView : ItemFragment<ItemJSONModel>() {

    private val globalController: GlobalController by inject()

    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    private val anchorBorders = 3

    override val root = anchorpane {

        val s = styledTextArea {

            runAsync {

                return@runAsync model.text

            } ui { loadedText ->

                this.appendText(loadedText)

                this.textProperty().onChange {
                    model.textProperty.bindBidirectional(this.content.text.toProperty())
                    model.categoryId = 1
                    fire(UpdateToDbEvent(model))
                }
            }

            anchorpaneConstraints {
                topAnchor = anchorBorders
                bottomAnchor = anchorBorders
                leftAnchor = anchorBorders
                rightAnchor = anchorBorders
            }

            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            //addClass(Styles.performanceAsItemStyle)

            setStyleClass(0,text.length,Styles.performanceAsItemStyle.render())
            println(Styles.performanceAsItemStyle.render())
        }

        /*
        textfield {

            runAsync {

                return@runAsync model.text

            } ui { loadedText ->

                this@textfield.text = loadedText
                model.textProperty.bindBidirectional(this.textProperty())

            }

            anchorpaneConstraints {
                topAnchor = anchorBorders
                bottomAnchor = anchorBorders
                leftAnchor = anchorBorders
                rightAnchor = anchorBorders
            }

            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            addClass(Styles.performanceAsItemStyle)

        }
        */
    }
}



fun EventTarget.styledTextArea(value: String? = null, op: StyleClassedTextArea.() -> Unit = {}) =
        opcr(this, StyleClassedTextArea()
                .apply { if (value != null) text.plus(value) }, op)
