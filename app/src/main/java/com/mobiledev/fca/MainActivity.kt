package com.mobiledev.fca

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity(),View.OnClickListener {

    private lateinit var classify: CardView
    private lateinit var artStyle: CardView
    private lateinit var superResolution: CardView
    private lateinit var removeBackground: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initObjects()
    }
    //TEST
    private fun initObjects() {
        classify = findViewById<CardView>(R.id.classify)
        artStyle = findViewById<CardView>(R.id.artStyle)
        removeBackground = findViewById<CardView>(R.id.removeBackground)
        superResolution = findViewById<CardView>(R.id.superResolution)
        classify.setOnClickListener(this)
        artStyle.setOnClickListener(this)
        removeBackground.setOnClickListener(this)
        superResolution.setOnClickListener(this)
    }

    //TEST
    override fun onClick(v: View) {
        when (v.id) {
            R.id.classify -> {
                //setContentView(R.layout.choose_image)
                Toast.makeText(this, "Classify", Toast.LENGTH_SHORT).show()}
            R.id.artStyle -> setContentView(R.layout.choose_image)
            R.id.removeBackground -> setContentView(R.layout.choose_image)
            R.id.superResolution -> setContentView(R.layout.choose_image)
            else -> Log.d("Test","fail")
        }
    }

}