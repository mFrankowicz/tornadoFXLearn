package com.company.app

import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.Color
import javafx.scene.paint.Paint
import javafx.scene.text.FontWeight
import tornadofx.*

class Styles : Stylesheet() {

    companion object {

        val sightVBoxStyle by cssclass()
        val sightAnchorPaneStyle by cssclass()

        val performanceAsItemStyle by cssclass()
        val performanceAsStyle by cssclass()

        val nnViewStyle by cssclass()
        val nnViewItemStyle by cssclass()
        val nnViewItemTextStyle by cssclass()

        val theoryStyle by cssclass()
        val theoryTextStyle by cssclass()
    }

    init {
        // General Sights Style
        //-----------------------
        sightVBoxStyle {
            borderWidth += box(5.px, 0.px, 5.px, 0.px)
            borderColor += box(Paint.valueOf("RED"))
            borderStyle += BorderStrokeStyle.SOLID
        }

        sightAnchorPaneStyle {
            borderWidth += box(2.px, 0.px, 2.px, 0.px)
            borderColor += box(Paint.valueOf("GRAY"))
            borderStyle += BorderStrokeStyle.SOLID
            padding = box(4.px)
        }


        // PerformanceAs Styles
        //-----------------------
        performanceAsStyle {
            borderWidth += box(0.px, 3.px, 0.px, 0.px)
            borderColor += box(Paint.valueOf("BLACK"))
            borderStyle += BorderStrokeStyle.DOTTED
        }

        performanceAsItemStyle {
            fontSize = 14.pt
            fontWeight = FontWeight.BOLD
            backgroundColor += Color.TRANSPARENT
            textFill = Color.BLUE
        }

        // nnView Styles
        //-----------------------
        nnViewStyle {
            borderWidth += box(0.px, 3.px, 0.px, 0.px)
            borderColor += box(Paint.valueOf("BLACK"))
            borderStyle += BorderStrokeStyle.DOTTED
        }

        nnViewItemStyle {
            backgroundColor += Color.YELLOW
            borderColor += box(Color.BLACK)
            borderWidth += box(1.px)
        }

        nnViewItemTextStyle {
            backgroundColor += Color.TRANSPARENT
        }

        // Theory Styles
        //-----------------------
        theoryStyle {
            backgroundColor += Color.GRAY
            borderColor += box(Color.BLACK)
            borderWidth += box(1.px)
            padding = box(10.px)
        }

        theoryTextStyle {
            backgroundColor += Color.TRANSPARENT
        }

    }
}