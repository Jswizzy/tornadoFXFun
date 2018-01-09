package com.example.demo.view

import com.example.demo.app.Styles.Companion.all
import javafx.animation.Interpolator
import javafx.animation.Timeline
import javafx.application.Platform
import javafx.geometry.Orientation.VERTICAL
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.util.Duration
import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val personEditor: PersonEditor by inject()
    val personList: PersonList by inject()

    override val root = borderpane {

        top = menubar {
            menu("File") {
                item("Save", "Shortcut+S").action { println("Saving...") }
                item("Quite", "Shortcut+Q").action { Platform.exit() }
            }
            menu("Edit") {
                item("Copy", "Shortcut+C").action { println("Copying...") }
                item("Paste", "Shortcut+V").action { println("Pasting...") }
            }
        }

        center = drawer {
            item("Form", expanded = true) {
                form {
                    fieldset("Personal Info", labelPosition = VERTICAL) {
                        field("First Name") {
                            textfield()
                        }
                        field("Last Name") {
                            textfield()
                        }
                        field("Birthday") {
                            datepicker()
                        }
                    }
                    fieldset("Contact") {
                        field("Phone") {
                            textfield()
                        }
                        field("Email") {
                            textfield()
                        }
                    }
                    button("Commit") {
                        action { println("Wrote to database!") }
                    }

                    fieldset("Feedback Form", labelPosition = VERTICAL) {
                        field("Comment", VERTICAL) {
                            textarea {
                                prefRowCount = 5
                                vgrow = Priority.ALWAYS
                            }
                        }
                        buttonbar {
                            button("Send")
                        }
                    }

                    addClass(all)
                }
            }
            item("Shapes") {
                stackpane {
                    group {
                        val rect = rectangle {
                            fill = Color.BLUE
                            width = 300.0
                            height = 150.0
                        }
                        timeline {
                            keyframe(Duration.seconds(5.0)) {
                                keyvalue(rect.rotateProperty(), 360.0, interpolator = Interpolator.EASE_BOTH)
                                keyvalue(rect.arcWidthProperty(), 60.0)
                                keyvalue(rect.arcHeightProperty(), 60.0)
                            }
                            isAutoReverse = true
                            cycleCount = Timeline.INDEFINITE
                        }

                    }
                }
            }
            item("Person Editor") {
                borderpane {
                    center = personList.root
                    right = personEditor.root
                }
            }
        }
    }
}