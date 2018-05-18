package com.company.controller

import com.company.model.ItemJSONModel
import javafx.beans.property.SimpleLongProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.*
import java.util.*
import javax.naming.ldap.Control

/*class GlobalController(): Controller() {

    private val item1 = ItemJSONModel()
    private val item2 = ItemJSONModel()
    init {
        item1.text = "Performance as Programming"
        item1.id = (UUID.randomUUID().toString())
        item2.text = "Performance as Living"
        item2.id = (UUID.randomUUID().toString())
    }

    val performanceAsList = mutableListOf(
            item1,
            item2
    ).observable()

    fun addItem(item: ItemJSONModel) {
        performanceAsList.add(item)
    }

}*/

class GlobalController(): Controller() {
    val sight1 = Sight()

    val sightsList = mutableListOf(
            sight1
    ).observable()

    fun addItem(item: Sight) {
        sightsList.add(item)
    }

}

class Sight() {
    val performanceAsItems = PerformanceAsHolder()
}

class PerformanceAsHolder() {
    private val item1 = ItemJSONModel()
    private val item2 = ItemJSONModel()
    init {
        item1.text = "Performance as Programming"
        item1.id = (UUID.randomUUID().toString())
        item2.text = "Performance as Living"
        item2.id = (UUID.randomUUID().toString())
    }

    val performanceAsItemList = mutableListOf(
            item1,
            item2
    ).observable()

    fun addItem(item: ItemJSONModel) {
        performanceAsItemList.add(item)
    }
}