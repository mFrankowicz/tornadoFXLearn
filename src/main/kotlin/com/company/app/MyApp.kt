package com.company.app

import com.company.view.MainView
import tornadofx.*

class MyApp: App(MainView::class, Styles::class){
    init {
        importStylesheet(Styles::class)
    }
}