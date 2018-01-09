package com.example.demo.model

import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Person(name: String? = null, title: String? = null) {
    val nameProperty = SimpleStringProperty(this, "name", name)
    var name by nameProperty

    val titleProperty = SimpleStringProperty(this, "title", title)
    var title by titleProperty
}

class PersonModel(var person: Person) : ViewModel() {
    val name = bind { person.nameProperty }
    val title = bind { person.titleProperty }
}