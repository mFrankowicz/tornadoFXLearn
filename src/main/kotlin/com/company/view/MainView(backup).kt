/*
package com.company.view

import javafx.beans.property.Property
import javafx.beans.property.SimpleStringProperty
import javafx.scene.layout.BorderPane
import javafx.scene.web.WebView
import tornadofx.*

class Person(name: String? = null, title: String? = null) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    var name by nameProperty

    val titleProperty = SimpleStringProperty(this, "title", title)
    var title by titleProperty
}


class PersonModel: ItemViewModel<Person>() {
    val name = bind(Person::nameProperty)
    val title = bind(Person::titleProperty)

    var commitsList = mutableListOf<List<Commit>>()

    override fun onCommit(commits: List<Commit>) {
        commits.findChanged(name)?.let { println("name changed from ${it.first} to ${it.second}") }
        commits.findChanged(title)?.let { println("title changed from ${it.first} to ${it.second}")}
        commitsList.add(commits)
        for (c in commitsList) {
            println("${c.first().newValue} ${c.last().newValue}")
        }
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let {(it.newValue as T) to (it.oldValue as T)}
    }

}

class PersonList: View("Person List") {
    val persons = listOf(Person("John", "Manager"), Person("Marcos", "Artist")).observable()
    val model: PersonModel by inject()

    override val root = tableview(persons) {
        title = "Person"
        column("Name", Person::nameProperty)
        column("Title", Person::titleProperty)
        bindSelected(model)

    }
}

class PersonEditor : View("Hello TornadoFX") {

    val model: PersonModel by inject()

    override val root = form {
        fieldset("Edit person") {
            field("Name") {
                textfield(model.name)
            }
            field("Title") {
                textfield(model.title)
            }
            button("Save") {
                enableWhen(model.dirty)
                action {
                    save()
                }
            }
            button("Reset").action {
                model.rollback()
            }
        }
    }

    private fun save() {

        model.commit()

        val person = model.item

        println("Saving ${person.name} / ${person.title}")
    }

}

class MainView:View("Main"){

    val t : PersonList by inject()
    val f : PersonEditor by inject()

    override val root = borderpane{
        left = t.root

        right = f.root
    }
}
*/
/*
class Deleg {

    private var value: String? = null


    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return value ?: throw IllegalStateException("Initialize first!")
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        println("$thisRef || $property || $value")
        this.value = value
    }

}
*//*


*/
