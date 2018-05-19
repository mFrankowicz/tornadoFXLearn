package com.company.view

import com.company.app.Styles
import com.company.controller.PerformanceAsHolder
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import tornadofx.*

class PerformanceAsView(performanceAsHolder: PerformanceAsHolder) : Fragment() {

    override val root = vbox(5) {

        children.bind(performanceAsHolder.performanceAsItemList) {

            anchorpane {

                val editScope = ItemJSONScope()
                editScope.model = it
                val perfAnchors = find(PerformanceAsItemView::class, editScope).root

                this += perfAnchors

            }
        }

        paddingRight = 5

        addClass(Styles.performanceAsStyle)

    }
}

class PerformanceAsItemView() : ItemFragment<ItemJSONModel>() {

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

            addClass(Styles.performanceAsItemStyle)

        }
    }
}