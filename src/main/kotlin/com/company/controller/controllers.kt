package com.company.controller

import com.company.model.Item
import com.company.model.ItemModel
import javafx.collections.FXCollections
import tornadofx.*

class PerformanceAsController(): Controller() {
    val model: ItemModel by inject()

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