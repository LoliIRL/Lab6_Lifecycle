package com.example.activitylifecycle

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LifecycleLog", "onCreate")
        setContent { Text("Смотри вкладку Logcat!") }
    }
    override fun onStart() { super.onStart(); Log.d("LifecycleLog", "onStart") }
    override fun onResume() { super.onResume(); Log.d("LifecycleLog", "onResume") }
    override fun onPause() { super.onPause(); Log.d("LifecycleLog", "onPause") }
    override fun onStop() { super.onStop(); Log.d("LifecycleLog", "onStop") }
    override fun onRestart() { super.onRestart(); Log.d("LifecycleLog", "onRestart") }
    override fun onDestroy() { super.onDestroy(); Log.d("LifecycleLog", "onDestroy") }
}