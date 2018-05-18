package com.company.view

import com.company.controller.GlobalController
import com.company.model.*
import tornadofx.*


class MainView : View("Main") {
    val sigthOne = SightItemView()

    override val root = borderpane {
        center = sigthOne.root
    }
}

class SightItemView() : Fragment() {
    val performanceAsView = PerformanceAsView()
    override val root = hbox(5) {
        this += performanceAsView
    }
}

class PerformanceAsView() : Fragment() {
    private val globalController: GlobalController by inject()
    override val root = vbox(5) {
        children.bind(globalController.sight1.performanceAsItems.performanceAsItemList) {
            anchorpane {
                println(it.toString())
                val editScope = ItemJSONScope()
                editScope.model = it
                val perfAnchors = find(PerformanceAsItemView::class, editScope).root
                println(perfAnchors)
                add(perfAnchors)
            }

        }
        button("print") {
            action {
                globalController.sight1.performanceAsItems.performanceAsItemList.forEach {
                    println(it.toJSON())
                }
            }
        }
    }
}

class PerformanceAsItemView() : ItemFragment<ItemJSONModel>() {
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
    }
}

