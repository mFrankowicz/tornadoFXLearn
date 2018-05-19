package com.company.model

import javafx.beans.property.*
import tornadofx.*
import javax.json.*

class ItemJSONModel : JsonModel, Any() {

    private val idProperty = SimpleStringProperty()
    var id by idProperty

    private val sightNumberProperty = SimpleIntegerProperty()
    private var sightNumber by sightNumberProperty

    private val categoryIdProperty = SimpleIntegerProperty()
    private var categoryId by categoryIdProperty

    private val indexProperty = SimpleIntegerProperty()
    private var index by indexProperty

    val textProperty = SimpleStringProperty()
    var text by textProperty

    private val formatProperty = SimpleStringProperty()
    private var format by formatProperty

    override fun updateModel(json: JsonObject) {

        with(json) {

            id = string("id")!!
            sightNumber = int("sightNumber")!!
            categoryId = int("categoryId")!!
            index = int("index")!!
            text = string("text")
            format = string("format")

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

    var model = ItemJSONModel()

}