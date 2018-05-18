package com.company.controller

import com.company.model.ItemJSONModel
import tornadofx.*
import java.util.*

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
    val sight1 = SightHolder()
    val sight2 = SightHolder()

    val sightsList = mutableListOf(
            sight1,
            sight2
    ).observable()

    fun addItem(item: SightHolder) {
        sightsList.add(item)
    }

}

class SightHolder() {
    val performanceAsItems = PerformanceAsHolder()
    val nnView1Items = NNViewHolder()
    val nnView2Items = NNViewHolder()
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

class NNViewHolder() {
    private val item1 = ItemJSONModel()
    private val item2 = ItemJSONModel()

    private val itemInside1 = ItemJSONModel()

    init {
        item1.text = "View 1"
        item1.id = (UUID.randomUUID().toString())
        item2.text = "View 2"
        item2.id = (UUID.randomUUID().toString())

        itemInside1.text = "View Inside Item 1"
        itemInside1.id = (UUID.randomUUID().toString())
    }

    val nnViewList = mutableListOf(
            item1,
            item2
    ).observable()

    val nnViewInsideList = mutableListOf(
            itemInside1
    ).observable()
    fun addItem(item: ItemJSONModel) {
        nnViewList.add(item)
    }

    fun addItemInside(item: ItemJSONModel) {
        nnViewInsideList.add(item)
    }
}