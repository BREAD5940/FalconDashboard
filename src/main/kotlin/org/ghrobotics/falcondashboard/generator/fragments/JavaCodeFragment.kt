package org.ghrobotics.falcondashboard.generator.fragments

import edu.wpi.first.wpilibj.geometry.Rotation2d
import javafx.scene.layout.Priority
import javafx.scene.text.Font
import kfoenix.jfxtextarea
import org.ghrobotics.falcondashboard.Settings
import org.ghrobotics.falcondashboard.generator.GeneratorView
import org.ghrobotics.lib.mathematics.twodim.geometry.Pose2d
import org.ghrobotics.lib.mathematics.twodim.geometry.x_u
import org.ghrobotics.lib.mathematics.twodim.geometry.y_u
import org.ghrobotics.lib.mathematics.units.feet
import org.ghrobotics.lib.mathematics.units.inFeet
import org.ghrobotics.lib.mathematics.units.inMeters
import org.ghrobotics.lib.mathematics.units.meter
import tornadofx.*
import java.awt.Desktop
import java.net.URI
import java.text.DecimalFormat
import edu.wpi.first.wpilibj.geometry.Pose2d

class JavaCodeFragment : Fragment() {
    override val root = vbox {

        title = "Generated Java Code"

        style {
            padding = box(1.em)
        }

        prefWidth = 800.0
        prefHeight = 500.0

        jfxtextarea {
            font = Font.font("Monospaced")
            isEditable = false

            vgrow = Priority.ALWAYS

            text = buildString {


                val dm = DecimalFormat("##.###")

                append("List.of(\n")
                GeneratorView.waypoints.forEach {
                    append(
                        "    new Pose2d(${dm.format(it.translation.x_u.inMeters())}, " +
                                "${dm.format(it.translation.y_u.inMeters())}, " +
                                "new Rotation2d(${dm.format(it.rotation.radians)}))"
                    )
                    if (it != GeneratorView.waypoints.last()) append(",")
                    append("\n")
                }
                append("),\n")
            }
        }
        vbox {
            style {
                padding = box(0.5.em, 0.em, 0.em, 0.em)
            }
            add(text(" This code is generated to be used with FalconLibrary"))
            add(hyperlink("https://github.com/5190GreenHopeRobotics/FalconLibrary") {
                setOnAction {
                    Desktop.getDesktop()
                        .browse(URI("https://github.com/5190GreenHopeRobotics/FalconLibrary"))
                }
            })
        }
    }
}