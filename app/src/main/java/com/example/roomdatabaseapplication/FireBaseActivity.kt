package com.example.roomdatabaseapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FireBaseActivity : AppCompatActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null
    private lateinit var signIn: MaterialButton
    private val remoteConfig = Firebase.remoteConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fire_base)
        signIn = findViewById(R.id.btn_signIn)
        val hate = findViewById<MaterialButton>(R.id.btn_Hate)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        hate.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("show_name",hate.toString())
            mFirebaseAnalytics?.logEvent("show_selected", bundle)
        }





        signIn.setOnClickListener {
            remoteConf()
        }


        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 3600
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)



    }


    fun remoteConf(){
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                    val text = remoteConfig.getString("Login_Btn")
                    signIn.text = text
                    Toast.makeText(
                        this, "Fetch and activate succeeded",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this, "Fetch failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }

}