package com.alphafast.camerascanner.lib.view

import android.content.Context
import android.hardware.Camera
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.alphafast.camerascanner.lib.extension.getCorrectRotateDegree

class CameraSurfacePreview(context: Context?, var camera: Camera?) : SurfaceView(context) {

    private val holderCallback = object : SurfaceHolder.Callback {
        override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
            // If your preview can change or rotate, take care of those events here.
            // Make sure to stop the preview before resizing or reformatting it.
            if (holder?.surface != null) {
                // stop preview before making changes
                try {
                    camera?.stopPreview()
                } catch (e: Exception) {
                    // ignore: tried to stop a non-existent preview
                }
                holder.startPreview()
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder?) {
            // empty. Take care of releasing the Camera preview in your activity.
        }

        override fun surfaceCreated(holder: SurfaceHolder?) {
            holder?.startPreview()
        }

        fun SurfaceHolder.startPreview() {
            try {
                camera?.setDisplayOrientation(getCorrectRotateDegree(context))
                camera?.setPreviewDisplay(this)
                camera?.startPreview()
            } catch (e: Exception) {
                Log.e("CameraSurfacePreview", "Error setting camera preview: ${e.message}")
            }
        }

    }

    init {
        holder?.addCallback(holderCallback)
    }
}