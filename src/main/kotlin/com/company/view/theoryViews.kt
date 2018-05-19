package com.company.view

import com.company.app.Styles
import com.company.controller.TheoryHolder
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
import tornadofx.*

class TheoryView(theoryHolder: TheoryHolder) : Fragment() {

    override val root = vbox(5) {

        children.bind(theoryHolder.theoryList) {

            anchorpane {

                val editScope = ItemJSONScope()
                editScope.model = it
                val theoryAnchors = find(TheoryItemView::class, editScope).root

                this += theoryAnchors

            }
        }

        paddingRight = 5

    }
}

class TheoryItemView() : ItemFragment<ItemJSONModel>() {

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

            addClass(Styles.theoryTextStyle)

        }

        addClass(Styles.theoryStyle)

    }
}