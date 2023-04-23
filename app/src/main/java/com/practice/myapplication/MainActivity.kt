package com.practice.myapplication

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val mContext: Context = this

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
        }
        val grantedVal =
            ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
        if (grantedVal == PackageManager.PERMISSION_GRANTED) {
            Log.d("Permissions", "Perm G")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel.
                val name = getString(R.string.channel_name)
                val descriptionText = getString(R.string.channel_description)
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val CHANNEL_ID = "Channel 1"
                val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
                mChannel.description = descriptionText
                // Register the channel with the system. You can't change the importance
                // or other notification behaviors after this.
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(mChannel)
            }
        } else {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun sendingNotification() {
        val textTitle = "Greetings"
        val textContext = "Hey Bitch"
        val notification = NotificationCompat.Builder(this, "Channel 1")
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
            .setContentTitle(textTitle).setContentText(textContext)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setSmallIcon(R.drawable.luffy)
            .build()
//        notificationManager.notify(1, notification)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            val grantedVal =
                mContext.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                }
            if (grantedVal == PackageManager.PERMISSION_GRANTED) {
                notify(0, notification)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun sendingNotificationDone() {
        val textTitle = "Greetings"
        val textContext = "Done"
        val notification = NotificationCompat.Builder(this, "Channel 1")
            .setSmallIcon(androidx.core.R.drawable.notification_icon_background)
            .setContentTitle(textTitle).setContentText(textContext)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT).setSmallIcon(R.drawable.luffy)
            .build()
//        notificationManager.notify(1, notification)
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            val grantedVal =
                mContext.let {
                    ContextCompat.checkSelfPermission(
                        it,
                        Manifest.permission.POST_NOTIFICATIONS
                    )
                }
            if (grantedVal == PackageManager.PERMISSION_GRANTED) {
                notify(0, notification)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        val time = object : CountDownTimer(20000, 1000) {
            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onTick(millisUntilFinished: Long) {
                sendingNotification()
            }

            @RequiresApi(Build.VERSION_CODES.TIRAMISU)
            override fun onFinish() {
                sendingNotificationDone()
            }
        }
        time.start()
    }

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->

        }


}