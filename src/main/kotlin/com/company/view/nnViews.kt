package com.company.view

import com.company.controller.NNViewHolder
import com.company.model.ItemJSONScope
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Paint
import tornadofx.*

class NNView(nnViewHolder: NNViewHolder) : Fragment() {
    override val root = hbox(5) {

        style {
            borderWidth += box(0.px, 3.px, 0.px, 0.px)
            borderColor += box(Paint.valueOf("BLACK"))
            borderStyle += BorderStrokeStyle.DOTTED
        }

        paddingRight = 5

        vbox(5) {
            children.bind(nnViewHolder.nnViewList) {
                anchorpane {
                    println(it.toString())
                    val editScope = ItemJSONScope()
                    editScope.model = it
                    val perfAnchors = find(NNViewItem::class, editScope).root
                    println(perfAnchors)
                    add(perfAnchors)
                }

            }
            button("print") {
                action {
                    nnViewHolder.nnViewList.forEach {
                        println(it.toJSON())
                    }
                }
            }
        }
        vbox(5) {
            children.bind(nnViewHolder.nnViewInsideList) {
                anchorpane {
                    println(it.toString())
                    val editScope = ItemJSONScope()
                    editScope.model = it
                    val perfAnchors = find(NNViewItemInside::class, editScope).root
                    println(perfAnchors)
                    add(perfAnchors)
                }

            }
            button("print") {
                action {
                    nnViewHolder.nnViewInsideList.forEach {
                        println(it.toJSON())
                    }
                }
            }
        }
    }
}

class NNViewItem : Fragment() {
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

class NNViewItemInside : Fragment() {
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

