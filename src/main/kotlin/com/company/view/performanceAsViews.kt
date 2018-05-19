package com.company.view

import com.company.app.Styles
import com.company.controller.PerformanceAsHolder
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import javafx.beans.value.ObservableValue
import javafx.event.EventTarget
import javafx.scene.control.TextField
import javafx.scene.control.TextInputControl
import javafx.scene.layout.AnchorPane
import javafx.util.StringConverter
import org.fxmisc.richtext.StyleClassedTextArea
import tornadofx.*
import java.text.Format

class PerformanceAsView(performanceAsHolder: PerformanceAsHolder) : Fragment() {

    override val root = vbox(5) {

        runAsync {

            return@runAsync performanceAsHolder.listOne

        } ui {

            children.bind(it) {
                anchorpane {

                    val editScope = ItemJSONScope()
                    editScope.model = it as ItemJSONModel
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

    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    val styledTextArea = StyleClassedTextArea()

    private val anchorBorders = 3

    override val root = anchorpane {

        styledTextArea {
            runAsync {

                return@runAsync model.text

            } ui { loadedText ->

                this.appendText(loadedText)

                model.textProperty.bindBidirectional(this.text.toProperty())

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
