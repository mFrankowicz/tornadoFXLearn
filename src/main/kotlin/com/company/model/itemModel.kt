package com.company.model

import com.company.controller.CommitsHistory
import com.company.controller.PerformanceAsController
import javafx.beans.property.*
import tornadofx.*
import javax.json.*
/*
data class Item(val id: Long,
                val sightNumber: Int,
                val categoryId: Int,
                val index: Int,
                val text: String,
                val format: JsonArray?){}

*/

class Item(id: Long? = null,
           sighNumber: Int? = null,
           categoryId: Int? = null,
           index: Int? = null,
           text: String? = null,
           style: String? = null
           ) {

    val idProperty = SimpleLongProperty(this, "id", id!!)
    var id by idProperty

    val sightNumberProperty = SimpleIntegerProperty(this, "sightNumber", sighNumber!!)
    var sightNumber by sightNumberProperty

    val categoryIdProperty = SimpleIntegerProperty(this, "categoryId", categoryId!!)
    var categoryId by categoryIdProperty

    val indexProperty = SimpleIntegerProperty(this, "index", index!!)
    var index by indexProperty

    val textProperty = SimpleStringProperty(this, "text", text)
    var text by textProperty

    val styleProperty = SimpleStringProperty(this, "style", style)
    var style by styleProperty
}

class ItemModel : ItemViewModel<Item>() {

    val commitsHistory: CommitsHistory by inject(DefaultScope)

    val id = bind(Item::id)
    val sightNumber = bind(Item::sightNumber)
    val categoryId = bind(Item::categoryId)
    val index = bind(Item::index)
    var text = bind(Item::text)
    var style = bind(Item::style)

    override fun onCommit(commits: List<Commit>) {
        super .onCommit()
        commits.findChanged(text)?.let {
            println("text changed from ${it.first} to ${it.second}")
            // commitsHistory.addCommit(commits)
        }
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let {(it.oldValue as T) to (it.newValue as T)}
    }
}

class ItemScope : Scope() {
    val model = ItemModel()
}


class ItemJSONModel() : JsonModel {
    val idProperty = SimpleLongProperty()
    var id by idProperty

    val sightNumberProperty = SimpleIntegerProperty()
    var sightNumber by sightNumberProperty

    val categoryIdProperty = SimpleIntegerProperty()
    var categoryId by categoryIdProperty

    val indexProperty = SimpleIntegerProperty()
    var index by indexProperty

    val textProperty = SimpleStringProperty()
    var text by textProperty

    val formatProperty = SimpleObjectProperty<JsonArray>()
    var format by formatProperty

    override fun updateModel(json: JsonObject) {
        with(json) {
            id = long("id")!!
            sightNumber = int("sightNumber")!!
            categoryId = int("categoryId")!!
            index = int("index")!!
            text = string("text")
            format = jsonArray("format")
        }
    }

    override fun toJSON(json: JsonBuilder) {
        with(json) {
            add("id", id)
            add("sightNumber", sightNumber)
            add("categoryId", categoryId)
            add("index", index)
            add("text", text)
            add("format", format)
        }
    }
}

class ItemJSONScope : Scope(){
    val model = ItemJSONModel()
}