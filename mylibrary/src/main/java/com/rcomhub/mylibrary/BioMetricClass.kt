package com.rcomhub.mylibrary

import android.content.Context
import android.content.DialogInterface
import android.hardware.biometrics.BiometricPrompt
import android.os.Build
import android.os.CancellationSignal
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi

class BioMetricClass(context: Context, appName: String, subTitle: String, setDescription: String) {

    companion object {
        public const val AUTH_FAILED = 1
        public const val AUTH_SUCCESS = 2
        public const val AUTH_NOT_AVAILABLE = 3
        public const val AUTH_AVAILABLE = 4
        public const val AUTH_CANCEL_BY_USER = 5
        public const val BIOMETRIC_ERROR_NO_BIOMETRICS = 11
    }

    var RESULT: Int = 0
    private lateinit var bioMetricClickListener: BioMetricClickListener

    private var cancellationSignal: CancellationSignal? = null
    private val authenticationCallback: BiometricPrompt.AuthenticationCallback
        get() =
            @RequiresApi(Build.VERSION_CODES.P)
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int, errString: CharSequence?) {
                    super.onAuthenticationError(errorCode, errString)
//                    notifyUser("Authentication error: $errString")
//                    LockPage.setLock(this@LockActivity, MainActivity::class.java)
                    RESULT = if (errorCode == BIOMETRIC_ERROR_NO_BIOMETRICS) {
                        BIOMETRIC_ERROR_NO_BIOMETRICS
                    } else {
                        AUTH_FAILED
                    }
                    bioMetricClickListener.bioClick(RESULT)
                }

                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult?) {
                    super.onAuthenticationSucceeded(result)
//                    notifyUser("Authentication Success!")
//                    gotoNewActivity(MainActivity::class.java, this@LockActivity)
                    RESULT = AUTH_SUCCESS
                    bioMetricClickListener.bioClick(RESULT)

                }
            }

    var context: Context
    var appName: String = ""
    var subTitle: String = ""
    var setDescription: String = ""


    init {
        this.context = context
        this.appName = appName
        this.subTitle = subTitle
        this.setDescription = setDescription
        this.bioMetricClickListener = context as BioMetricClickListener
    }

    @RequiresApi(Build.VERSION_CODES.P)
    public fun checkingBioMetric(): Int {
        val (isAvailable, reason) = BioMetricLibrary.checkBioMetric(context)
        if (isAvailable) {
//            RESULT = AUTH_AVAILABLE
            val biometricPrompt: BiometricPrompt = BiometricPrompt.Builder(context)
                .setTitle(appName)
                .setSubtitle(subTitle)
                .setDescription(setDescription)
                .setNegativeButton(
                    "Cancel",
                    context.mainExecutor,
                    DialogInterface.OnClickListener { dialog, which ->
                        RESULT = AUTH_FAILED
                        Log.e("RESULT", "$RESULT")
                        bioMetricClickListener.bioClick(RESULT)

                    }).build()
            biometricPrompt.authenticate(
                getCancellationSignal(),
                context.mainExecutor,
                authenticationCallback
            )

        } else {
            RESULT = AUTH_NOT_AVAILABLE
//            Toast.makeText(context, "$reason ", Toast.LENGTH_SHORT).show()
        }
        Log.e("isAvailable", "$isAvailable")
        return RESULT
    }

    private fun getCancellationSignal(): CancellationSignal {
        cancellationSignal = CancellationSignal()
        cancellationSignal?.setOnCancelListener {
//            notifyUser("Authentication was cancelled by the user")
//            showKeyPass()
            RESULT = AUTH_CANCEL_BY_USER
            bioMetricClickListener.bioClick(RESULT)

        }
        return cancellationSignal as CancellationSignal
    }

}

