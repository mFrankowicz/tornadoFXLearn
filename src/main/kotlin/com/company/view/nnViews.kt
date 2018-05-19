package com.company.view

import com.company.app.Styles
import com.company.controller.NNViewHolder
import com.company.model.ItemJSONScope
import javafx.geometry.Orientation
import tornadofx.*

class NNView(nnViewHolder: NNViewHolder) : Fragment() {

    override val root = hbox(5) {

        vbox(5) {

            children.bind(nnViewHolder.nnViewList) {

                anchorpane {

                    val editScope = ItemJSONScope()
                    editScope.model = it
                    val nnViewAnchors = find(NNViewItem::class, editScope).root

                    this += nnViewAnchors
                }
            }
        }


        flowpane {

            children.bind(nnViewHolder.nnViewInsideList) {

                anchorpane {

                    val editScope = ItemJSONScope()
                    editScope.model = it
                    val nnViewItemAnchors = find(NNViewItemInside::class, editScope).root

                    this += nnViewItemAnchors

                }
            }

            orientation = Orientation.VERTICAL
            prefWrapLength = 600.0
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

        textfield(model.text) {

            model.textProperty.bindBidirectional(this.textProperty())

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

        textfield(model.text) {

            model.textProperty.bindBidirectional(this.textProperty())

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

