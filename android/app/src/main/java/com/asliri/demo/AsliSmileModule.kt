package com.asliri.demo

import android.R
import android.app.Activity
import android.content.Intent
import com.facebook.react.bridge.ActivityEventListener
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class AsliSmileModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext), ActivityEventListener {

    private var promise: Promise? = null
    private val LIVENESS_REQUEST_CODE = 1234 // Definisikan request code

    init {
        reactContext.addActivityEventListener(this)
    }

    override fun getName() = "AsliSmileModule"

    @ReactMethod
    fun startSmileLiveness(promise: Promise) {
        this.promise = promise
        val activity = currentActivity
        if (activity != null) {
            val intent = Intent(activity, LivenessActivity::class.java)
            // Gunakan startActivityForResult
            activity.startActivityForResult(intent, LIVENESS_REQUEST_CODE)
        } else {
            promise.reject("Activity not found", "React Activity is null")
        }
    }

    override fun onActivityResult(activity: Activity?, requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == LIVENESS_REQUEST_CODE) {
            when (resultCode) {
                Activity.RESULT_OK -> {
                    // Proses hasil sukses
                    val result = data?.getStringExtra("result_key") // sesuaikan dengan key yang Anda gunakan
                    promise?.resolve(result)
                }
                Activity.RESULT_CANCELED -> {
                    // Handle pembatalan
                    promise?.reject("CANCELLED", "Liveness check was cancelled")
                }
                else -> {
                    // Handle error lainnya
                    promise?.reject("ERROR", "Liveness check failed")
                }
            }
            promise = null // Reset promise setelah digunakan
        }
    }

    override fun onNewIntent(p0: Intent?) {
        // Implement jika diperlukan
    }
}