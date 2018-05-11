package com.alphafast.camerascanner.lib

import android.hardware.Camera
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alphafast.camerascanner.lib.extension.checkCameraHardware
import com.alphafast.camerascanner.lib.extension.getCameraInstance
import com.alphafast.camerascanner.lib.view.CameraSurfacePreview
import kotlinx.android.synthetic.main.fragment_camera.*

open class CameraFragment : Fragment() {

    protected var camera: Camera? = null
    protected val isCameraAvailable by lazy { checkCameraHardware(context) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (!isCameraAvailable) {
            context?.also {
                AlertDialog.Builder(it)
                        .setTitle("ข้อผิดพลาด")
                        .setMessage("ไม่มีกล้อง หรือ แอพอื่นกำลังใช้งานกล้องอยู่")
                        .setPositiveButton("ปิด", { d, _ -> d.dismiss() })
                        .show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (isCameraAvailable) {
            camera = getCameraInstance()
            cameraContainer?.addView(CameraSurfacePreview(context, camera))
        }
    }

    override fun onPause() {
        super.onPause()
        if (isCameraAvailable) {
            camera?.release()
            cameraContainer?.removeAllViews()
            camera = null
        }
    }
}