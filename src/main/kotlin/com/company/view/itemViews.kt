package com.company.view

import com.company.app.Styles
import com.company.controller.CreateItemEvent
import com.company.controller.GlobalController
import com.company.controller.UpdateToDbEvent
import com.company.controller.SightHolder
import com.company.model.ItemJSONModel
import javafx.geometry.Insets
import org.dizitart.no2.NitriteId
import tornadofx.*
import java.util.*


class MainView : View("Main") {

    private val globalController: GlobalController by inject()

    override val root = anchorpane {

        menubar {

            menu("Create") {

                item("Sight") {

                    val collection = globalController.db.getCollection("${1}")
                    action {
                        globalController.sightsList.add(SightHolder(1, collection))
                    }
                }

            }

            menu("Insides") {
                item("Performance as") {
                    action {
                        val item = ItemJSONModel()
                        item.id = NitriteId.newId().idValue
                        item.text = "Performance as..."
                        item.sightNumber = 0
                        item.categoryId = 1
                        item.index = Random().nextInt()
                        fire(CreateItemEvent(item))
                    }

                }
                menu("nnView1") {
                    item("nnView") {

                    }
                    item("Text") {

                    }
                    item("ArtistsList") {

                    }
                    item("Direction") {

                    }
                }
                menu("nnView2") {
                    item("nnView") {

                    }
                    item("Text") {

                    }
                    item("ArtistsList") {

                    }
                    item("Direction") {

                    }
                }
                item("Theory")
            }

            anchorpaneConstraints {
                topAnchor = 0
                leftAnchor = 0
                rightAnchor = 0
            }
        }

        scrollpane {

            vbox(5) {

                runAsync {

                    globalController.getList()

                } ui { t ->

                    children.bind(t) {

                        val sights = SightItemView(it)

                        anchorpane {

                            this += sights

                            padding = Insets(2.0, 2.0, 2.0, 2.0)
                            addClass(Styles.sightAnchorPaneStyle)

                        }
                    }
                }


                setPrefSize(1200.0,700.0)
                usePrefSize

                padding = Insets(5.0,5.0, 5.0,10.0)
                addClass(Styles.sightVBoxStyle)

            }

            anchorpaneConstraints {
                topAnchor = 30
                bottomAnchor = 0
                leftAnchor = 0
                rightAnchor = 0
            }
        }
    }


}

//TODO: Convert to to RichTextFX Controllers
class SightItemView(sightHolder: SightHolder) : Fragment() {

    private val globalController: GlobalController by inject()

    private val performanceAsView = PerformanceAsView(sightHolder.performanceAsItems)
    private val nnView1 = NNView(sightHolder.nnView1Items, 1)
    private val nnView2 = NNView(sightHolder.nnView2Items, 2)
    private val theoryView = TheoryView(sightHolder.theoryItems)

    override val root = hbox {

        this += vbox {
            this += button("add") {
                action {
                    //globalController.saveToLocalDB()
                }
            }
        }

        this += performanceAsView
        this += nnView1
        this += nnView2
        this += theoryView

    }
}
