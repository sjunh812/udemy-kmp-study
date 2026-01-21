package com.sjhstudio.dailypulse

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual open class BaseViewModel : ViewModel() {

    actual val scope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    fun close() {
        scope.cancel()
    }
}