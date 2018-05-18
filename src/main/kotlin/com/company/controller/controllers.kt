package com.company.controller

import com.company.model.ItemJSONModel
import tornadofx.*

class PerformanceAsController(): Controller() {

    val performanceAs = mutableListOf(
            ItemJSONModel(),
            ItemJSONModel()
    ).observable()

    fun addItem(item: ItemJSONModel) {
        performanceAs.add(item)
    }


}