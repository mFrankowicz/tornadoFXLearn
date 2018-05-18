package com.company.view

import com.company.controller.GlobalController
import com.company.controller.SightHolder
import javafx.geometry.Insets
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Paint
import tornadofx.*


class MainView : View("Main") {
    private val globalController: GlobalController by inject()

    override val root = scrollpane {

        vbox(5) {
            children.bind(globalController.sightsList) {
                val sights = SightItemView(it)
                anchorpane {
                    this += sights

                    padding = Insets(2.0,2.0, 2.0,2.0)

                    style {
                        borderWidth += box(2.px, 0.px, 2.px, 0.px)
                        borderColor += box(Paint.valueOf("GRAY"))
                        borderStyle += BorderStrokeStyle.SOLID
                    }
                }

            }
            padding = Insets(5.0,5.0, 5.0,10.0)

            style {
                borderWidth += box(5.px, 0.px, 5.px, 0.px)
                borderColor += box(Paint.valueOf("GRAY"))
                borderStyle += BorderStrokeStyle.SOLID
            }

        }

    }

}

//TODO: Convert to to RichTextFX Controllers
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