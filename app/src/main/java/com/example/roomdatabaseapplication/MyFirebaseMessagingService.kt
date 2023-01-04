package com.example.roomdatabaseapplication

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

const val channelId="notification_id"
const val channelName="package com.example.roomdatabaseapplication"

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        if (message.notification != null){
            fireBase(message.notification!!.title!!, message.notification!!.body!!)
        }
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun fireBase(tittle:String, message:String){

        val intent=Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIndent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT)



        var builder:NotificationCompat.Builder=NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.weather)
            .setOnlyAlertOnce(false)
            .setAutoCancel(true)
            .setVibrate(longArrayOf(1000,1000,1000,1000))
            .setContentIntent(pendingIndent)



        builder=builder.setContent(getRemoteView(tittle,message))
        val notificationManger=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){

            val notificationChanel=NotificationChannel(channelId, channelName,NotificationManager.IMPORTANCE_HIGH)
            notificationManger.createNotificationChannel(notificationChanel)
        }

        notificationManger.notify(0,builder.build())

    }

    @SuppressLint("RemoteViewLayout")
    private fun getRemoteView(tittle: String, message: String): RemoteViews {
        val remoteView=RemoteViews("package com.example.roomdatabaseapplication",R.layout.activity_fire_base)
        remoteView.setTextViewText(R.id.tvTittle,tittle)
        remoteView.setTextViewText(R.id.tvMessage,message)
        return remoteView
    }
}