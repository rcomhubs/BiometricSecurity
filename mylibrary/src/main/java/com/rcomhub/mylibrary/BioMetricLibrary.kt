package com.rcomhub.mylibrary

import android.content.Context
import android.hardware.biometrics.BiometricManager
import android.widget.Toast


object BioMetricLibrary {

    // creating a variable for our BiometricManager
    // and lets check if our user can use biometric sensor or not
    fun checkBioMetric(context: Context): Pair<Boolean, String> {
        var isAvailable = false
        var reason = ""
        val biometricManager: androidx.biometric.BiometricManager =
            androidx.biometric.BiometricManager.from(context)

        isAvailable =
            when (biometricManager.canAuthenticate(androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK)) {
                BiometricManager.BIOMETRIC_SUCCESS -> {
                    reason = "You can use the fingerprint sensor to login"
                    true
                }
                BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                    reason = "This device doesnot have a fingerprint sensor"
                    false
                }
                BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                    reason = "The biometric sensor is currently unavailable"
                    false
                }
                BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                    reason =
                        "Your device doesn't have fingerprint saved, please check your security settings"
//                Toast.makeText(
//                    context,
//                    "No fingerprint configured. Please register at least one fingerprint in your device's Settings",
//                    Toast.LENGTH_LONG
//                ).show()
                    false
                }
                else -> {
                    false
                }
            }
        return Pair(isAvailable, reason)
    }
}