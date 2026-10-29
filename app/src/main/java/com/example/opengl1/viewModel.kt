package com.example.opengl1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class CameraViewModel : ViewModel() {
    var camerPos = MutableStateFlow<FloatArray>(floatArrayOf(0f, 0f, 0f))
}