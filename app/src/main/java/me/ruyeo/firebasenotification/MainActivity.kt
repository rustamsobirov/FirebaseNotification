package me.ruyeo.firebasenotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFCMToken()
        navigate(intent)
    }

    private fun loadFCMToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("TAG", "Fetching FCM registration token failed")
                return@OnCompleteListener
            }
            val token = task.result
            Log.d("TAG", token.toString())
        })
    }

    private fun navigate(intent: Intent?){
        if (intent != null){
            val text = intent.getStringExtra("type")
            when(text){
                "parcel" -> {
                    //navigate to fragment
                    Toast.makeText(this, "Navigate to parcel Fragment", Toast.LENGTH_SHORT).show()
                }
                "simple" -> {
                    //navigate to fragment
                    Toast.makeText(this, "Navigate to Simple Fragment", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "ok", Toast.LENGTH_SHORT).show()
        navigate(intent)
    }
}