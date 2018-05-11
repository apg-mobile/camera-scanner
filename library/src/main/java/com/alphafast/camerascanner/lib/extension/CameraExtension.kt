package com.alphafast.camerascanner.lib.extension

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.hardware.Camera
import android.util.Log
import android.view.WindowManager
import android.widget.Toast

/**
 * Check if this device has a camera
 * @return true if device has camera
 */
fun checkCameraHardware(context: Context?): Boolean {
    return context?.packageManager?.hasSystemFeature(PackageManager.FEATURE_CAMERA) == true
}

/**
 * A safe way to get an instance of the Camera object.
 * @return null if camera is unavailable or in use.
 */
fun getCameraInstance(): Camera? {
    return try {
        Camera.open()
    } catch (e: Exception) {
        null
    }
}

/**
 * @return correct degree for rotate camera
 */
fun getCorrectRotateDegree(context: Context?): Int {
    val orientation = context?.resources?.configuration?.orientation ?: -1
    val wm = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager?
    val rotation = wm?.defaultDisplay?.rotation

    return if (rotation == 0 && orientation == Configuration.ORIENTATION_PORTRAIT) {
        90
    } else if (rotation == 1 && orientation == Configuration.ORIENTATION_LANDSCAPE) {
        0
    } else if (rotation == 2 && orientation == Configuration.ORIENTATION_PORTRAIT) {
        270
    } else if (rotation == 3 && orientation == Configuration.ORIENTATION_LANDSCAPE) {
        180
    } else {
        //Something went wrong
        0
    }
}