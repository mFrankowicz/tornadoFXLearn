package com.company.controller

import com.company.model.ItemJSONModel
import javafx.collections.ObservableList
import org.dizitart.kno2.documentOf
import org.dizitart.kno2.filters.and
import org.dizitart.kno2.filters.eq
import org.dizitart.kno2.getCollection
import org.dizitart.kno2.nitrite
import org.dizitart.no2.Document
import org.dizitart.no2.NitriteCollection
import tornadofx.*
import java.io.File
import tornadofx.EventBus.RunOn.*

object SaveToDbRequest : FXEvent(BackgroundThread)

class UpdateToDbEvent(val message: ItemJSONModel): FXEvent()

class CreateItemEvent(val message: ItemJSONModel): FXEvent()

class GlobalController: Controller() {

    val db = nitrite("root", "root") {
        file = File("src/main/resources/root_bd")
        autoCommitBufferSize = 2048
        compress = true
        autoCompact = false

    }

    var sightsList = mutableListOf<SightHolder>(
    ).observable()

    fun loadFromLocalDB() {
        db.listCollectionNames().forEach {
            db.getCollection(it) {
                println(this.name)
                val sights = SightHolder(it.toInt(), this)
                sightsList.add(sights)
                this.find().forEach {
                    println(it)
                }
            }
        }
    }

    fun addItem(item: SightHolder) {
        sightsList.add(item)
    }

    fun getList() : ObservableList<SightHolder> {
        return sightsList
    }

    fun generateDocumentTextOf(item: ItemJSONModel): Document {

        var docu = documentOf(
                "id" to item.id,
                "sight_number" to item.sightNumber,
                "category_id" to item.categoryId,
                "index" to item.index,
                "text" to item.text.toString())
        return docu
    }


    init {

        loadFromLocalDB()

        subscribe<UpdateToDbEvent> { event ->
            val obj = event.message
            val collection = db.getCollection("${obj.sightNumber}") {
                if (this.find("id" eq obj.id).hasMore()) {
                    println(obj)
                } else {
                    println(obj.index)
                }
                this.update("index" eq obj.index,
                        generateDocumentTextOf(obj)
                )
            }

        }

        subscribe<CreateItemEvent> { event ->
            val obj = event.message
            val collection = db.getCollection("${obj.sightNumber}") {
                this.insert(
                        generateDocumentTextOf(obj)
                )

                /*this.update(
                        generateDocumentTextOf(obj)
                )*/

                when(obj.categoryId) {
                    1 -> {
                        sightsList[obj.sightNumber].performanceAsItems.listOne.add(obj)
                    }
                    2 -> {
                        when(obj.nnViewIndex) {
                            0 -> {
                                sightsList[obj.sightNumber].nnView1Items.listOne.add(obj)
                            }

                            1 -> {
                                sightsList[obj.sightNumber].nnView2Items.listOne.add(obj)
                            }
                        }
                    }
                    3 -> {
                        when(obj.nnViewIndex) {
                            0 -> {
                                sightsList[obj.sightNumber].nnView1Items.listTwo.add(obj)
                            }

                            1 -> {
                                sightsList[obj.sightNumber].nnView2Items.listTwo.add(obj)
                            }
                        }
                    }
                    4 -> {
                        when(obj.nnViewIndex) {
                            0 -> {
                                sightsList[obj.sightNumber].nnView1Items.listTwo.add(obj)
                            }

                            1 -> {
                                sightsList[obj.sightNumber].nnView2Items.listTwo.add(obj)
                            }
                        }
                    }
                    5 -> {
                        when(obj.nnViewIndex) {
                            0 -> {
                                sightsList[obj.sightNumber].nnView1Items.listTwo.add(obj)
                            }

                            1 -> {
                                sightsList[obj.sightNumber].nnView2Items.listTwo.add(obj)
                            }
                        }
                    }
                    6 -> {
                        sightsList[obj.sightNumber].theoryItems.listOne.add(obj)
                    }
                }
            }
        }
    }

}

class SightHolder(number: Int, collection: NitriteCollection) {
    val sightNumber = number
    val performanceAsItems = PerformanceAsHolder(sightNumber, collection)
    val nnView1Items = NNViewHolder(sightNumber, collection)
    val nnView2Items = NNViewHolder(sightNumber, collection)
    val theoryItems = TheoryHolderOneList(sightNumber, collection)
}

fun initLists(s_n: Int, c_t: Int, list: ObservableList<ItemJSONModel>, collection: NitriteCollection)  {
    collection.find(("sight_number" eq s_n) and ("category_id" eq c_t)).forEach {
        val item = ItemJSONModel()
        item.id = it["id"] as Long
        item.text = it["text"].toString()
        item.index = it["index"] as Int
        item.categoryId = it["category_id"] as Int
        item.sightNumber = it["sight_number"] as Int
        list.add(item)
    }
}

class PerformanceAsHolder(number: Int, collection: NitriteCollection) {

    var listOne = mutableListOf<ItemJSONModel>().observable()

    init {
        initLists(number, 1, listOne, collection)
    }


}

class NNViewHolder(number: Int, collection: NitriteCollection) {

    var listOne = mutableListOf<ItemJSONModel>().observable()
    var listTwo = mutableListOf<ItemJSONModel>().observable()
    init {
        initLists(number, 2, listOne, collection)
        initLists(number, 3, listTwo, collection)
        initLists(number, 4, listTwo, collection)
        initLists(number, 5, listTwo, collection)
    }

}

class TheoryHolderOneList(number: Int, collection: NitriteCollection) {

    var listOne = mutableListOf<ItemJSONModel>().observable()
    init {
        initLists(number, 6, listOne, collection)
    }


}