package com.company.view

import com.company.controller.GlobalController
import com.company.controller.SightHolder
import tornadofx.*


class MainView : View("Main") {
    private val globalController: GlobalController by inject()

    override val root = scrollpane {

        vbox {
            children.bind(globalController.sightsList) {
                val sights = SightItemView(it)
                anchorpane {
                    this += sights
                }

            }

        }

    }

}


class SightItemView(itemJSONModel: SightHolder) : Fragment() {

    private val performanceAsView = PerformanceAsView(itemJSONModel.performanceAsItems)
    private val nnView1 = NNView(itemJSONModel.nnView1Items)
    private val nnView2 = NNView(itemJSONModel.nnView2Items)

    override val root = hbox {
        this += performanceAsView
        this += nnView1
        this += nnView2
    }
}