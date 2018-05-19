package com.company.view

import com.company.app.Styles
import com.company.controller.GlobalController
import com.company.controller.SightHolder
import javafx.geometry.Insets
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
                    addClass(Styles.sightAnchorPaneStyle)

                }
            }

            padding = Insets(5.0,5.0, 5.0,10.0)
            addClass(Styles.sightVBoxStyle)

        }
    }
}

//TODO: Convert to to RichTextFX Controllers
class SightItemView(sightHolder: SightHolder) : Fragment() {

    private val performanceAsView = PerformanceAsView(sightHolder.performanceAsItems)
    private val nnView1 = NNView(sightHolder.nnView1Items)
    private val nnView2 = NNView(sightHolder.nnView2Items)
    private val theoryView = TheoryView(sightHolder.theoryItems)

    override val root = hbox {

        this += label("Sight") //TODO: Pass Sight Number Reference

        this += performanceAsView
        this += nnView1
        this += nnView2
        this += theoryView

    }
}