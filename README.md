# BiometricSecurity

#Add it in your root build.gradle at the end of repositories:
    
        allprojects {
        repositories {
          ...
          maven { url 'https://jitpack.io' }
        }
      }
      
#Add the dependency
 
      dependencies {
	        implementation 'com.github.rcomhubs:BiometricSecurity:1.0.1'
	    }
      
#Add method to check biometric exist or not if exist set title and description

        val bioMetricClass = BioMetricClass(
            context = this,
            appName = getString(R.string.app_name),
            subTitle = getString(R.string.subTitle),
            setDescription = getString(R.string.setDescription)
        )
        
        when (bioMetricClass.checkingBioMetric()) {
            BioMetricClass.AUTH_NOT_AVAILABLE -> {
                gotoHome()
            }
        }
        
#Use interface for click listener on biometric prompt

      BioMetricClickListener and overide method
      
          override fun bioClick(RESULT: Int) {
            when (RESULT) {
                BiometricPrompt.BIOMETRIC_ERROR_NO_BIOMETRICS -> {
                    Toast.makeText(this,
                        "The user does not have any biometrics enrolled",
                        Toast.LENGTH_SHORT).show()
                    gotoHome()
                }
                BioMetricClass.AUTH_FAILED -> {
                    Toast.makeText(this, "Auth failed", Toast.LENGTH_SHORT).show()
                    finish()
                }
                BioMetricClass.AUTH_SUCCESS -> {
                    Toast.makeText(this, "Auth success", Toast.LENGTH_SHORT).show()
                    gotoHome()
                }
            }
        }
        
  
      private fun gotoHome(log: String) {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
      }
        
        
