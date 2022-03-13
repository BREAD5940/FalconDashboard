package org.ghrobotics.falcondashboard.livevisualizer

import edu.wpi.first.networktables.NetworkTableInstance
import javafx.geometry.Pos
import javafx.scene.input.KeyCode
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.stage.StageStyle
import kfoenix.jfxtextfield
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.livevisualizer.charts.FieldChart
import org.ghrobotics.lib.wrappers.networktables.FalconNetworkTable
import tornadofx.*

class LiveVisualizerView : View() {
    override val root = vbox {
        hbox {
            paddingAll = 10

            text("Network Table IP:   ") {
                alignment = Pos.CENTER_LEFT
            }

            jfxtextfield(Settings.ip.value) {
                prefWidth = 100.0
                setOnKeyPressed {
                    if (it.code == KeyCode.ENTER) {
                        Settings.ip.set(this@jfxtextfield.text)
                        FalconNetworkTable.getTable("Live_Dashboard").instance.startClient(Settings.ip.value)
                    }
                }
            }
        }
        stackpane {
            style {
                backgroundColor = multi(Color.LIGHTGRAY)
            }
            alignment = Pos.CENTER
            vgrow = Priority.ALWAYS
            add(FieldChart)
        }
    }
}