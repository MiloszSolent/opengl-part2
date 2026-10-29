package com.example.opengl1

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import freemap.openglwrapper.Camera

class MainActivity : AppCompatActivity() {
    val viewModel = CameraViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val cameraPos = viewModel.camerPos.collectAsState()

            Column {
                CameraController()

                OpenGLComposable(cameraPos.value)
            }
        }
    }

    @Composable
    fun CameraController() {
        val pos = viewModel.camerPos.collectAsState().value

        Row() {
            Button(onClick = { viewModel.camerPos.value = floatArrayOf(pos[0] -1f, pos[1], pos[2]) }, Modifier.width(60.dp)) { Text("Left") }
            Button(onClick = { viewModel.camerPos.value = floatArrayOf(pos[0], pos[1] + 1, pos[2]) }, Modifier.width(60.dp)) { Text("Up") }
            Button(onClick = { viewModel.camerPos.value = floatArrayOf(pos[0], pos[1] -1f, pos[2]) }, Modifier.width(60.dp)) { Text("Down") }
            Button(onClick = { viewModel.camerPos.value = floatArrayOf(pos[0], pos[1], pos[2] +1f) }, Modifier.width(60.dp)) { Text("Forward") }
            Button(onClick = { viewModel.camerPos.value = floatArrayOf(pos[0], pos[1], pos[2] -1f) }, Modifier.width(60.dp)) { Text("Back") }
            Button(onClick = { viewModel.camerPos.value = floatArrayOf(pos[0] +1f, pos[1], pos[2]) }, Modifier.width(60.dp)) { Text("Right") }
        }
    }

    @Composable
    fun OpenGLComposable(cameraPos: FloatArray) {
        AndroidView(
            factory = { ctx ->
                val glView = OpenGLView(ctx)
                glView
            },
            update = {
                // handle changes to the OpenGLView here, e.g. reset its camera position (not shown)
                gLView -> gLView.oldCam.position = gLView.cam.position.copy()
                gLView.cam.translate(cameraPos[0], cameraPos[1], cameraPos[2])
            }
        )
    }
}