package org.firstinspires.ftc.teamcode.testing.opmodes

import org.firstinspires.ftc.teamcode.testing.Robot
import com.asiankoala.koawalib.command.KOpMode
import com.asiankoala.koawalib.command.commands.Cmd
import com.asiankoala.koawalib.command.commands.GVFCmd
import com.asiankoala.koawalib.command.commands.WaitCmd
import com.asiankoala.koawalib.command.commands.WaitUntilCmd
import com.asiankoala.koawalib.command.group.SequentialGroup
import com.asiankoala.koawalib.logger.Logger
import com.asiankoala.koawalib.logger.LoggerConfig
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.math.Vector
import com.asiankoala.koawalib.math.angleWrap
import com.asiankoala.koawalib.math.radians
import com.asiankoala.koawalib.path.*
import com.asiankoala.koawalib.path.gvf.SimpleGVFController
import com.asiankoala.koawalib.util.OpModeState
import com.qualcomm.robotcore.eventloop.opmode.Autonomous

@Autonomous
class TestAuto : KOpMode() {
    private val robot by lazy { Robot(startPose) }

    private val startPose = Pose(-59.0, -36.0, 180.0.radians)

    private val kN = 0.6
    private val kOmega = 1.0 / 30.0
    private val kF = 4.0
    private val kS = 1.0
    private val epsilon = 1.0

    private lateinit var mainCommand: Cmd

    private fun defaultGVFCmd(path: Path, vararg cmds: Pair<Cmd, Vector>): GVFCmd {
        return GVFCmd(
            robot.drive,
            SimpleGVFController(path, 0.6, 1.0 / 30.0, 4.0, 1.0, 1.0, 2.0)
        )
    }

    private val path1 = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        Pose(startPose.x, startPose.y, 0.0),
        Pose(-9.0, -34.0, 45.0.radians)
    )

    private val intakePath = HermitePath(
        { 270.0.radians },
        Pose(-9.0, -34.0, 250.0.radians),
        Pose(-12.0, -53.0, 270.0.radians)
    )

    private val depositPath = HermitePath(
        FLIPPED_HEADING_CONTROLLER,
        Pose(-12.0, -53.0, 90.0.radians),
        Pose(-9.0, -34.0, 30.0.radians)
    )

    private val intakePath2 = HermitePath(
        { 90.0.radians },
        Pose(-9.0, -34.0, 90.0.radians),
        Pose(-12.0, 47.0, 90.0.radians)
    )
//    val example_path = Path(HermiteSplineInterpolator(
//        HeadingController { it.angle + 180.0.radians },
//        Pose(0.0, 0.0, 0.0),
//        Pose(24.0, 24.0, 0.0)
//    ))

    override fun mInit() {

        Logger.config = LoggerConfig(
            isLogging = true,
            false,
            isDashboardEnabled = true,
            isTelemetryEnabled = true
        )

       mainCommand = SequentialGroup(
            WaitUntilCmd {opModeState == OpModeState.START},
            GVFCmd(
                robot.drive,
                SimpleGVFController(path1, 0.6, 30.0, 4.0, 0.7, 3.0, 3.0)
            ),
           WaitCmd(0.5),
            GVFCmd(
               robot.drive,
               SimpleGVFController(intakePath, 0.6, 30.0, 4.0, 0.4, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(depositPath, 0.5, 25.0, 2.0, 0.7, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(intakePath, 0.6, 30.0, 4.0, 0.4, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(depositPath, 0.5, 25.0, 2.0, 0.7, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(intakePath, 0.6, 30.0, 4.0, 0.4, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(depositPath, 0.5, 25.0, 2.0, 0.7, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(intakePath, 0.6, 30.0, 4.0, 0.4, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(depositPath, 0.5, 25.0, 2.0, 0.7, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(intakePath, 0.6, 30.0, 4.0, 0.4, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(depositPath, 0.5, 25.0, 2.0, 0.7, 3.0, 3.0)
           ),
           WaitCmd(0.5),
           GVFCmd(
               robot.drive,
               SimpleGVFController(intakePath2, 0.6, 20.0, 4.0, 0.7, 3.0, 3.0)
           )
        )
        mainCommand.schedule()
    }
}