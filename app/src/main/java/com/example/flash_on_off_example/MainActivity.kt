package com.example.flash_on_off_example

import android.content.pm.PackageManager
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var stateFlash = false
        findViewById<TextView>(R.id.btn_on_off_flash).setOnClickListener {
            if (stateFlash){
                findViewById<TextView>(R.id.btn_on_off_flash).text = "OFF"
            }else{
                findViewById<TextView>(R.id.btn_on_off_flash).text = "ON"
            }
            stateFlash = !stateFlash
            setFlash(stateFlash)
        }
    }

    private fun setFlash(value: Boolean) {
        if (this.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            val camManager = getSystemService(CAMERA_SERVICE) as CameraManager
            val cameraID: String?
            try {
                cameraID = camManager.cameraIdList[0]
                camManager.setTorchMode(cameraID, value)
            } catch (e: CameraAccessException) {
                e.printStackTrace()
            }
        } else {
            Toast.makeText(this, "NO FLASH", Toast.LENGTH_SHORT).show()
        }
    }
}