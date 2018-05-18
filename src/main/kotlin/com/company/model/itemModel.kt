package com.company.model

import javafx.beans.property.*
import tornadofx.*
import java.util.*
import javax.json.*


class ItemJSONModel() : JsonModel {
    val idProperty = SimpleStringProperty()
    var id by idProperty

    val sightNumberProperty = SimpleIntegerProperty()
    var sightNumber by sightNumberProperty

    val categoryIdProperty = SimpleIntegerProperty()
    var categoryId by categoryIdProperty

    val indexProperty = SimpleIntegerProperty()
    var index by indexProperty

    val textProperty = SimpleStringProperty()
    var text by textProperty

    val formatProperty = SimpleStringProperty()
    var format by formatProperty

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