package com.company.view

import com.company.app.Styles
import com.company.controller.TheoryHolder
import com.company.model.ItemJSONModel
import com.company.model.ItemJSONScope
/*import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight*/
import tornadofx.*

class TheoryView(theoryHolder: TheoryHolder) : Fragment() {
    override val root = vbox(5) {
        children.bind(theoryHolder.theoryList) {
            anchorpane {
                println(it.toString())
                val editScope = ItemJSONScope()
                editScope.model = it
                val theoryAnchors = find(TheoryItemView::class, editScope).root
                println(theoryAnchors)
                add(theoryAnchors)
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
            minWidth = 200.0
            minHeight = 40.0
            prefWidth = 200.0
            prefHeight = 40.0

            model.textProperty.bindBidirectional(this.textProperty())


            anchorpaneConstraints {
                topAnchor = anchorBorders
                bottomAnchor = anchorBorders
                leftAnchor = anchorBorders
                rightAnchor = anchorBorders
            }

            addClass(Styles.theoryTextStyle)

        }
        addClass(Styles.theoryStyle)
    }
}