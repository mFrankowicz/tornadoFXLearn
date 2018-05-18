package com.company.view

import com.company.controller.CommitsHistory
import com.company.controller.PerformanceAsController
import com.company.model.Item
import com.company.model.ItemModel
import com.company.model.ItemScope
import javafx.application.Platform
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.control.TextField
import tornadofx.*
import tornadofx.getValue
import tornadofx.setValue

class MainView : View("Main") {
    val sigthOne = SightView()
    val performanceAsController: PerformanceAsController by inject()


    override val root = borderpane {
        center = sigthOne.root
    }
}

class SightView() : View() {
    private val controller: PerformanceAsController by inject()
    private val commitsHistory: CommitsHistory by inject(DefaultScope)
    val modelGlobal: ItemModel by inject()
    override val root = vbox(5) {
        children.bind(controller.performanceAs) {
            anchorpane {
                println(it.toString())
                val editScope = ItemScope()
                editScope.model.item = it
                val perfAnchors = find(PerformanceAsView::class, editScope).root
                println(perfAnchors)
                add(perfAnchors)
                //println("model ${editScope.model.item.id} ${editScope.model.item.textProperty}")
            }

        }
        button("print") {

            action {
                controller.performanceAs.forEach {
                    println(it.textProperty)
                }
            }
        }

        button("add") {
            action {
                controller.addItem(Item(13, 2, 3, 3, "PerformanceAs5", ""))
            }
        }
    }
}

class PerformanceAsView() : ItemFragment<Item>() {
    // val performanceAsController: PerformanceAsController by inject()
    override val scope = super.scope as ItemScope
    private val model = scope.model

    val textFieldProperty = SimpleObjectProperty<TextField>()
    var textField: TextField by textFieldProperty

    override val root = vbox {
        textfield(model.text) {
            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            model.rebindOnChange(this.textProperty()) { s ->
                //model.text.bindBidirectional(s.toProperty())
                model.commit()
            }

            // bind(performanceAsController.performanceAsItemModel.textProperty)

            // model.rebindOnChange(this.textProperty().onChange {  })
            // model.text.bindBidirectional(text.toProperty())
        }
    }
}
/*
class Deleg {

    private var value: String? = null


    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value ?: throw IllegalStateException("Initialize first!")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef || $property || $value")
        this.value = value
    }

}
*/

