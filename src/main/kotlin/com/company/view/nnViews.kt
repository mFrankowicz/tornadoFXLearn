package com.company.view

import com.company.app.Styles
import com.company.controller.NNViewHolder
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import javafx.geometry.Orientation
import tornadofx.*

class NNView(nnViewHolder: NNViewHolder) : Fragment() {

    override val root = hbox(5) {

        vbox(5) {

            runAsync {

                return@runAsync nnViewHolder.listOne

            } ui { t ->

                children.bind(t) {

                    anchorpane {

                        val editScope = ItemJSONScope()
                        editScope.model = it as ItemJSONModel
                        val nnViewAnchors = find(NNViewItem::class, editScope).root

                        this += nnViewAnchors
                    }
                }
            }


        }


        flowpane {

            runAsync {

                return@runAsync nnViewHolder.listTwo

            } ui { t->

                children.bind(t) {

                    anchorpane {

                        val editScope = ItemJSONScope()
                        editScope.model = it as ItemJSONModel
                        val nnViewItemAnchors = find(NNViewItemInside::class, editScope).root

                        this += nnViewItemAnchors

                    }
                }
            }

            orientation = Orientation.VERTICAL
            // prefWrapLength = 600.0
            paddingRight = 5

        }

        addClass(Styles.nnViewStyle)

    }
}

class NNViewItem : Fragment() {

    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    private val anchorBorders = 3

    override val root = anchorpane {

        textfield {

            runAsync {

                return@runAsync model.text

            } ui { loadedText ->

                this@textfield.text = loadedText

                model.textProperty.bindBidirectional(this.textProperty())

            }

            anchorpaneConstraints {

                topAnchor = anchorBorders
                bottomAnchor = anchorBorders
                leftAnchor = anchorBorders
                rightAnchor = anchorBorders

            }

            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            addClass(Styles.nnViewItemTextStyle)
        }

        addClass(Styles.nnViewItemStyle)
    }
}

class NNViewItemInside : Fragment() {

    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    private val anchorBorders = 3

    override val root = anchorpane {

        textfield {

            runAsync {

                return@runAsync model.text

            } ui { loadedText ->

                this@textfield.text = loadedText

                model.textProperty.bindBidirectional(this.textProperty())

            }

            anchorpaneConstraints {
                topAnchor = anchorBorders
                bottomAnchor = anchorBorders
                leftAnchor = anchorBorders
                rightAnchor = anchorBorders
            }

            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

        }
    }
}

