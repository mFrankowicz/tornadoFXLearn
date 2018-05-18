package com.company.view

import com.company.controller.PerformanceAsController
import com.company.model.*
import tornadofx.*


class MainView : View("Main") {
    val sigthOne = SightView()

    override val root = borderpane {
        center = sigthOne.root
    }
}

class SightView() : View() {
    private val controller: PerformanceAsController by inject()
    override val root = vbox(5) {
        children.bind(controller.performanceAs) {
            anchorpane {
                println(it.toString())
                val editScope = ItemJSONScope()
                editScope.model = it
                val perfAnchors = find(PerformanceAsView::class, editScope).root
                println(perfAnchors)
                add(perfAnchors)
            }

        }
        button("print") {
            action {
                controller.performanceAs.forEach {
                    println(it.toJSON())
                }
            }
        }

        button("add") {
            action {
                controller.addItem(ItemJSONModel())
            }
        }
    }
}

class PerformanceAsView() : ItemFragment<ItemJSONModel>() {
    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    override val root = vbox {
        textfield(model.text) {
            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            model.textProperty.bindBidirectional(this.textProperty())
        }
        textfield(model.format) {
            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            model.formatProperty.bindBidirectional(this.textProperty())
        }
    }
}

