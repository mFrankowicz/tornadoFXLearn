package com.company.view

import com.company.app.Styles
import com.company.controller.NNViewHolder
import com.company.controller.UpdateToDbEvent
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import javafx.geometry.Orientation
import tornadofx.*

class NNView(nnViewHolder: NNViewHolder, nnViewIndex: Int) : Fragment() {

    override val root = hbox(5) {

        vbox(5) {

            runAsync {

                return@runAsync nnViewHolder.listOne

            } ui { t ->

                children.bind(t) {

                    anchorpane {

                        val editScope = ItemJSONScope()
                        editScope.model = it as ItemJSONModel
                        val nnViewAnchors = find(NNViewItem(nnViewIndex)::class, editScope).root

                        this += nnViewAnchors
                    }
                }
            }


        }


        flowpane {

            runAsync {

                return@runAsync nnViewHolder.listTwo

            } ui { t ->

                children.bind(t) {

                    anchorpane {

                        val editScope = ItemJSONScope()
                        editScope.model = it as ItemJSONModel
                        val nnViewItemAnchors = find(NNViewItemInside(nnViewIndex)::class, editScope).root

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

class NNViewItem(nnViewIndex: Int) : Fragment() {

    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    private val anchorBorders = 3

    override val root = anchorpane {

        textfield {

            runAsync {

                return@runAsync model.text

            } ui { loadedText ->

                this@textfield.text = loadedText

                this.textProperty().onChange {
                    model.textProperty.bindBidirectional(this.text.toProperty())
                    model.categoryId = 2
                    model.nnViewIndex = nnViewIndex
                    fire(UpdateToDbEvent(model))
                }

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

class NNViewItemInside(nnViewIndex: Int) : Fragment() {

    override val scope = super.scope as ItemJSONScope
    private val model = scope.model

    private val anchorBorders = 3

    override val root = anchorpane {

        when (model.categoryId) {

            // text (red black)
            3 -> {
                textfield {

                    runAsync {

                        return@runAsync model.text

                    } ui { loadedText ->

                        this@textfield.text = loadedText

                        this.textProperty().onChange {
                            model.textProperty.bindBidirectional(this.text.toProperty())
                            model.categoryId = 3
                            model.nnViewIndex = nnViewIndex
                            fire(UpdateToDbEvent(model))
                        }

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

            // artists list
            4 -> {

                textfield {
                    runAsync {

                        return@runAsync model.text

                    } ui { loadedText ->

                        this@textfield.text = loadedText
                        //it.textProperty.bindBidirectional(this.textProperty())
                        this.textProperty().onChange {
                            model.textProperty.bindBidirectional(this.text.toProperty())
                            model.categoryId = 4
                            model.nnViewIndex = nnViewIndex
                            fire(UpdateToDbEvent(model))
                        }
                    }

                    anchorpaneConstraints {

                        topAnchor = anchorBorders
                        bottomAnchor = anchorBorders
                        leftAnchor = anchorBorders
                        rightAnchor = anchorBorders
                    }

                    minWidth = 200.0
                    minHeight = 100.0
                    prefWidth = 200.0
                    prefHeight = 300.0

                }

            }

            // directions
            5 -> {

                textfield {

                    runAsync {

                        return@runAsync model.text

                    } ui { loadedText ->

                        this@textfield.text = loadedText

                        this.textProperty().onChange {
                            model.textProperty.bindBidirectional(this.text.toProperty())
                            model.categoryId = 5
                            model.nnViewIndex = nnViewIndex
                            fire(UpdateToDbEvent(model))
                        }

                    }

                    setOnKeyPressed {
                        println(model.toJSON())
                    }

                    anchorpaneConstraints {
                        topAnchor = anchorBorders
                        bottomAnchor = anchorBorders
                        leftAnchor = anchorBorders
                        rightAnchor = anchorBorders
                    }

                    minWidth = 200.0
                    minHeight = 40.0
                    prefWidth = 80.0
                    prefHeight = 40.0

                }
            }

        }
    }
}
