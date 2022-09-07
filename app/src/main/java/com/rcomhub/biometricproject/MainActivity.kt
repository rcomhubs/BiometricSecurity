package com.rcomhub.biometricproject

import android.hardware.biometrics.BiometricPrompt.BIOMETRIC_ERROR_NO_BIOMETRICS
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.rcomhub.mylibrary.BioMetricClass
import com.rcomhub.mylibrary.BioMetricClass.Companion.AUTH_AVAILABLE
import com.rcomhub.mylibrary.BioMetricClass.Companion.AUTH_CANCEL_BY_USER
import com.rcomhub.mylibrary.BioMetricClass.Companion.AUTH_FAILED
import com.rcomhub.mylibrary.BioMetricClass.Companion.AUTH_NOT_AVAILABLE
import com.rcomhub.mylibrary.BioMetricClass.Companion.AUTH_SUCCESS
import com.rcomhub.mylibrary.BioMetricClickListener

class MainActivity : AppCompatActivity(), BioMetricClickListener {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bioMetricClass = BioMetricClass(
            context = this, appName = "Test", subTitle = "TestTest", setDescription = "TestTestTest"
        )

        when (bioMetricClass.checkingBioMetric()) {
//            AUTH_FAILED -> {
//                Toast.makeText(this, "Auth failed", Toast.LENGTH_SHORT).show()
//            }
//            AUTH_SUCCESS -> {
//                Toast.makeText(this, "Auth succeee", Toast.LENGTH_SHORT).show()
//            }
            BIOMETRIC_ERROR_NO_BIOMETRICS -> {
//                Toast.makeText(this,
//                    "The user does not have any biometrics enrolled",
//                    Toast.LENGTH_SHORT).show()
            }
            AUTH_NOT_AVAILABLE -> {
//                Toast.makeText(this, "Auth not available", Toast.LENGTH_SHORT).show()
            }
            AUTH_CANCEL_BY_USER -> {
//                Toast.makeText(this, "Auth cancel by user", Toast.LENGTH_SHORT).show()
            }
            AUTH_AVAILABLE -> {
//                Toast.makeText(this,
//                    "You can use the fingerprint sensor to login",
//                    Toast.LENGTH_SHORT)
//                    .show()
            }
            else -> {
//                Toast.makeText(this, "Else part", Toast.LENGTH_SHORT)
//                    .show()
            }
        }
    }

    override fun bioClick(RESULT: Int) {
        when (RESULT) {
            BIOMETRIC_ERROR_NO_BIOMETRICS -> {
//                Toast.makeText(this,
//                    "The user does not have any biometrics enrolled",
//                    Toast.LENGTH_SHORT).show()
            }
            AUTH_FAILED -> {
//                Toast.makeText(this, "Auth failed", Toast.LENGTH_SHORT).show()
            }
            AUTH_SUCCESS -> {
//                Toast.makeText(this, "Auth succsee", Toast.LENGTH_SHORT).show()
            }
            AUTH_NOT_AVAILABLE -> {
//                Toast.makeText(this, "Auth not available", Toast.LENGTH_SHORT).show()
            }
            AUTH_CANCEL_BY_USER -> {
//                Toast.makeText(this, "Auth cancel by user", Toast.LENGTH_SHORT).show()
            }
            AUTH_AVAILABLE -> {
//                Toast.makeText(this,
//                    "You can use the fingerprint sensor to login",
//                    Toast.LENGTH_SHORT)
//                    .show()
            }
            else -> {
//                Toast.makeText(this, "Else part", Toast.LENGTH_SHORT)
//                    .show()
            }
        }
    }
}