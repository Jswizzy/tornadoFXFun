package com.example.demo.view

import com.example.demo.model.Person
import com.example.demo.model.PersonModel
import javafx.scene.layout.BorderPane
import tornadofx.*

class PersonEditor : View("Person Editor") {
    override val root = BorderPane()
    val persons = listOf(Person("John", "Manager"), Person("Jay", "Worker bee")).observable()
    val model = PersonModel(Person())

    init {
        with(root) {
            center {
                tableview(persons) {
                    column("Name", Person::nameProperty)
                    column("Title", Person::titleProperty)

                    // Update the person inside the view model on selection change
                    model.rebindOnChange(this) { selectedPerson ->
                        person = selectedPerson ?: Person()
                    }
                }
            }

            right {
                form {
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
            }
        }
    }

    private fun save() {
        // Flush changes from the text fields into the model
        model.commit()

        // The edited person is contained in the model
        val person = model.person

        // A real application would persist the person here
        println("Saving ${person.name} / ${person.title}")
    }

}
