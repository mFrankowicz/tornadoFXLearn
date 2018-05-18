package com.company.view

import com.company.controller.PerformanceAsHolder
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Paint
import tornadofx.*

class PerformanceAsView(performanceAsHolder: PerformanceAsHolder) : Fragment() {
    override val root = vbox(5) {
        children.bind(performanceAsHolder.performanceAsItemList) {
            anchorpane {
                println(it.toString())
                val editScope = ItemJSONScope()
                editScope.model = it
                val perfAnchors = find(PerformanceAsItemView::class, editScope).root
                println(perfAnchors)
                add(perfAnchors)
            }

        }

        paddingRight = 5

        button("print") {
            action {
                performanceAsHolder.performanceAsItemList.forEach {
                    println(it.toJSON())
                }
            }
        }
        style {
            borderWidth += box(0.px, 3.px, 0.px, 0.px)
            borderColor += box(Paint.valueOf("BLACK"))
            borderStyle += BorderStrokeStyle.DOTTED
        }
    }
}

class PerformanceAsItemView() : ItemFragment<ItemJSONModel>() {
    override val scope = super.scope as ItemJSONScope
    private val model = scope.model
    private val anchorBorders = 3
    override val root = anchorpane {
        textfield(model.text) {
            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            model.textProperty.bindBidirectional(this.textProperty())


            anchorpaneConstraints {
                topAnchor = anchorBorders
                bottomAnchor = anchorBorders
                leftAnchor = anchorBorders
                rightAnchor = anchorBorders
            }
        }
    }
}