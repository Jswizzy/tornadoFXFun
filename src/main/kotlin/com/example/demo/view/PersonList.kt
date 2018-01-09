package com.example.demo.view

import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import tornadofx.*

class PersonList : View("Person List") {
    // get from a controller
    val persons = listOf(Person("John", "Manager"), Person("Jay", "Worker bee")).observable()
    val model : PersonModel by inject()

    override val root = tableview(persons) {
        title = "Person"
        column("Name", Person::firstNameProperty)
        column("Title", Person::lastNameProperty)
        bindSelected(model)
    }
}
