package com.example.demo.model

import javafx.beans.property.Property
import javafx.beans.property.SimpleStringProperty
import tornadofx.*

class Person(firstName: String? = null, lastName: String? = null) {
    val firstNameProperty = SimpleStringProperty(this, "firstName", firstName)
    var firstName: String by firstNameProperty

    val lastNameProperty = SimpleStringProperty(this, "firstName", lastName)
    var lastName: String by lastNameProperty
}

class PersonModel : ItemViewModel<Person>() {

    val firstName = bind(Person::firstNameProperty)
    val lastName = bind(Person::lastNameProperty)

    override fun onCommit(commits: List<Commit>) {
        // The println will only be called if findChanged is not null
        commits.findChanged(firstName)?.let { println("First-Name changed to ${it.first} from ${it.second}")}
        commits.findChanged(lastName)?.let { println("Last-Name changed to ${it.first} from ${it.second}")}
    }

    private fun <T> List<Commit>.findChanged(ref: Property<T>): Pair<T, T>? {
        val commit = find { it.property == ref && it.changed}
        return commit?.let { (it.newValue as T) to (it.oldValue as T) }
    }
}


