package com.example.demo.view

import com.example.demo.model.PersonModel
import tornadofx.*

class PersonEditor : View("Person Editor") {
    val model : PersonModel by inject()

    override val root = form {
        fieldset("Edit person") {
            field("Name") {
                textfield(model.firstName).required()
            }
            field("Title") {
                textfield(model.lastName).required()
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

    // move to a controller
    private fun save() {
        model.commit()
        println("Saving ${model.item.firstName} / ${model.item.lastName}")
    }
}
