package com.company.model

import javafx.beans.property.*
import org.dizitart.no2.NitriteId
import org.dizitart.no2.objects.Id
import tornadofx.*
import javax.json.*

data class ItemJSONModel(@Id var id: Long = NitriteId.newId().idValue) : JsonModel, Any() {

    val sightNumberProperty = SimpleIntegerProperty()
    var sightNumber by sightNumberProperty

    val nnViewIndexProperty = SimpleIntegerProperty()
    var nnViewIndex by nnViewIndexProperty

    val categoryIdProperty = SimpleIntegerProperty()
    var categoryId by categoryIdProperty

    val indexProperty = SimpleIntegerProperty()
    var index by indexProperty

    val textProperty = SimpleStringProperty()
    var text by textProperty

    override fun updateModel(json: JsonObject) {

        with(json) {
            id = long("id")!!
            sightNumber = int("sightNumber")!!
            categoryId = int("categoryId")!!
            index = int("index")!!
            nnViewIndex = int("nnViewIndex")!!
            text = string("text")
        }
    }

    override fun toJSON(json: JsonBuilder) {

        with(json) {
            add("id", id)
            add("sightNumber", sightNumber)
            add("categoryId", categoryId)
            add("index", index)
            add("nnViewIndex", nnViewIndex)
            add("text", text)
        }
    }
}

class ItemJSONScope : Scope(){

    var model = ItemJSONModel()

}