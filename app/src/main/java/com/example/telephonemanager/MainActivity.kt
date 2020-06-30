package com.example.telephonemanager

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {
    lateinit var textView1: TextView
    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView1 = findViewById(R.id.textView1)

        //Get the instance of TelephonyManager
        val tm = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        //Calling the methods of TelephonyManager the returns the information
        val IMEINumber = tm.deviceId
        val subscriberID = tm.deviceId
        val SIMSerialNumber = if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
                tm.simSerialNumber
        val networkCountryISO = tm.networkCountryIso
        val SIMCountryISO = tm.simCountryIso
        val softwareVersion = tm.deviceSoftwareVersion
        val voiceMailNumber = tm.voiceMailNumber

        //Get the phone type
        var strphoneType = ""

        val phoneType = tm.phoneType

        when (phoneType) {
            TelephonyManager.PHONE_TYPE_CDMA -> strphoneType = "CDMA"
            TelephonyManager.PHONE_TYPE_GSM -> strphoneType = "GSM"
            TelephonyManager.PHONE_TYPE_NONE -> strphoneType = "NONE"
        }
        val isRoaming = tm.isNetworkRoaming

        var info = "Phone Details:\n"
        info += "\n IMEI Number:$IMEINumber"
        info += "\n SubscriberID:$subscriberID"
        info += "\n Sim Serial Number:$SIMSerialNumber"
        info += "\n Network Country ISO:$networkCountryISO"
        info += "\n SIM Country ISO:$SIMCountryISO"
        info += "\n Software Version:$softwareVersion"
        info += "\n Voice Mail Number:$voiceMailNumber"
        info += "\n Phone Network Type:$strphoneType"
        info += "\n In Roaming? :$isRoaming"

        textView1.text = info

    }
}
