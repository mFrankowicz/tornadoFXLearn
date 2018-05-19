package com.company.controller

import com.company.model.ItemJSONModel
import javafx.collections.ObservableList
import tornadofx.*
import java.util.*

class GlobalController: Controller() {

    val sight1 = SightHolder()
    val sight2 = SightHolder()

    val sightsList = mutableListOf(
            sight1,
            sight2
    ).observable()

    fun addItem(item: SightHolder) {
        sightsList.add(item)
    }

    fun getList() : ObservableList<SightHolder> {
        return sightsList
    }

}

class SightHolder {
    val performanceAsItems = PerformanceAsHolder()
    val nnView1Items = NNViewHolder()
    val nnView2Items = NNViewHolder()
    val theoryItems = TheoryHolderOneList()
}

class PerformanceAsHolder : HolderOneList() {

    private val item1 = ItemJSONModel()
    private val item2 = ItemJSONModel()

    init {

        item1.text = "Performance as Programming"
        item1.id = (UUID.randomUUID().toString())
        item2.text = "Performance as Living"
        item2.id = (UUID.randomUUID().toString())

    }

    override val listOne: ObservableList<Any?> = mutableListOf(
            item1,
            item2
    ).observable()

}

class NNViewHolder : HolderTwoLists() {

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

    override val listOne: ObservableList<Any?> = mutableListOf(
            item1,
            item2
    ).observable()

    override val listTwo: ObservableList<Any?> = mutableListOf(
            itemInside1
    ).observable()

}

class TheoryHolderOneList : HolderOneList() {

    private val item1 = ItemJSONModel()
    private val item2 = ItemJSONModel()

    init {

        item1.text = "Theory 1"
        item1.id = (UUID.randomUUID().toString())

        item2.text = "Theory 2"
        item2.id = (UUID.randomUUID().toString())

    }

    override val listOne: ObservableList<Any?> = mutableListOf(
            item1,
            item2
    ).observable()
}

open abstract class HolderOneList {

    open abstract val listOne: ObservableList<Any?>

}

open abstract class HolderTwoLists : HolderOneList() {

    open abstract val listTwo: ObservableList<Any?>

}