package com.albertoapps.listtodo.Src.Auth

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.albertoapps.listtodo.Src.Main.MainActivity
import com.albertoapps.listtodo.databinding.ActivitySplashscreenBinding

class Splashscreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        handler = Handler(Looper.myLooper()!!)
        handler.postDelayed({
            val navegateTo = Intent(this, MainActivity::class.java)
            startActivity(navegateTo)
            finish()
        }, 3500)

    }
}