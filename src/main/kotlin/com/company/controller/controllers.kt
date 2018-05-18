package com.company.controller

import com.company.model.Item
import com.company.model.ItemModel
import javafx.collections.FXCollections
import tornadofx.*

class PerformanceAsController(): Controller() {
    val model: ItemModel by inject()
    /*val performanceAs = FXCollections.observableArrayList<Item>(
            Item(9,1,1,0,"performanceAs1"),
            Item(10,1,1,1,"performanceAs2"),
            Item(11,2,1,0,"performanceAs3"),
            Item(12,2,1,1,"performanceAs4")
    )*/

    val performanceAs = mutableListOf(
            Item(9,1,1,0,"performanceAs1"),
            Item(10,1,1,1,"performanceAs2"),
            Item(11,2,1,0,"performanceAs3"),
            Item(12,2,1,1,"performanceAs4")
    ).observable()


    /*fun printModel(){
        model.commitsList.forEach {
            println(it.toString())
        }
    }*/

    fun addItem(item: Item) {
        performanceAs.add(item)
    }

    fun modifyItem(item: Item){
        val iterate = performanceAs.listIterator()
        while (iterate.hasNext()) {
            val oldItem = iterate.next()
            if (oldItem.equals(item)) iterate.set(item)
        }
        println("The list now is: ")
        performanceAs.forEach { println(it.textProperty) }
    }

}

class CommitsHistory : Controller() {
    val commitsList = mutableListOf<List<Commit>>().observable()

    fun addCommit(commit: List<Commit>) {
        commitsList.add(commit)
    }

    fun printCommitsList() {
        println("commits: ")
        commitsList.forEach {
            println("${it.first().oldValue} --> ${it.first().newValue}")
        }
        println("--------------------------------------------------------------")
    }
}

/*
class ItemController: Controller() {
    val performanceAsController = PerformanceAsController()
    val nnViewOneController = NNViewOneController()
    val nnViewTwoController = NNViewTwoController()
    val theoryController = TheoryController()
    val referenceController = ReferenceController()
}

class PerformanceAsController: Controller() {
    val performanceAsItemModel = ItemJSONModel().copy<ItemJSONModel>()

    val performanceAs = listOf(
            Item(9,1,1,0,"performanceAs1", null),
            Item(10,1,1,1,"performanceAs2", null),
            Item(11,2,1,0,"performanceAs3", null),
            Item(12,2,1,1,"performanceAs4", null)
    )
}

class NNViewOneController: Controller() {
    val nnViewOneModel = ItemJSONModel()

    val nnViews = listOf(
            Item(5,1,2,0,"nnViewOne1", null),
            Item(6,2,2,0,"nnViewOne2",null)

    )
}

class NNViewTwoController: Controller() {
    val nnViewTwoModel = ItemJSONModel()

    val nnTwoViews = listOf(
            Item(7,1,3,0,"nnViewTwo1", null),
            Item(8,2,3,0,"nnViewTwo2", null)
    )
}

class TheoryController: Controller() {
    val theoryModel = ItemJSONModel()

    val theories = listOf(
            Item(2,1,4,0,"theory1", null),
            Item(3,1,4,1,"theory2", null),
            Item(4,2,4,0,"theory3", null)
    )
}

class ReferenceController: Controller() {
    val referenceModel = ItemJSONModel()

    val references = listOf(
            Item(0,1,5,0,"Reference1", null),
            Item(1,2,5,0,"Reference2", null)
    )


}
*/