package com.versilistyson.welldone.util

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService()  {

    private val TAG = "FirebaseMessaging"

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.notification != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.notification!!.body)
        } else {
            Log.d(TAG, "Message data body")
        }
    }

    override fun onMessageSent(message: String) {
        super.onMessageSent(message)

        Log.d(TAG, message)
    }

    override fun onDeletedMessages() {
        super.onDeletedMessages()
    }
}