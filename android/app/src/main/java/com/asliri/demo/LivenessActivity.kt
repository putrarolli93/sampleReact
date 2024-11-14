package com.asliri.demo

import android.app.Activity
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.asliri.aslismileliveness.sdk.AsliSmileLivenessContainer
import com.asliri.aslismileliveness.sdk.AsliSmileLivenessListener
import com.asliri.aslismileliveness.sdk.AsliSmileLivenessSDK
import com.asliri.demo.databinding.SmileLivenessActivityBinding

class LivenessActivity : AppCompatActivity(), AsliSmileLivenessListener {

    private val asliSmileLiveness by lazy {
        AsliSmileLivenessSDK.getInstance(this)
    }

    private val binding by lazy {
        SmileLivenessActivityBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        asliSmileLiveness.initialize("4cdfb7dd-b690-45db-8f0c-e5a8c0813d1a")
        asliSmileLiveness.smileLiveness(
            container = AsliSmileLivenessContainer(
                fragmentManager = supportFragmentManager,
                containerId = binding.fragmentContainer.id
            ),
            listener = this
        )
    }

    override fun onSmileLivenessFailure(code: Int, message: String) {
        Toast.makeText(this,"onSmileLivenessFailure: code=$code message=$message", Toast.LENGTH_LONG).show()
    }

    override fun onSmileLivenessSuccess(
        neutralBitmap: Bitmap,
        smileBitmap: Bitmap,
        result: Boolean
    ) {
        Toast.makeText(this,"onSmileLivenessSuccess-Result: $result", Toast.LENGTH_LONG).show()
    }

}