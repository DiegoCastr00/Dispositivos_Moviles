package com.example.tar_2_pich

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    // Declaring GestureDetector,
    // ScalingFactor and ImageView
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1.0f
    private lateinit var mImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing the ImageVIew and GestureDetector
        mImageView = findViewById(R.id.image_view_1)
        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())
    }

    // When touched, GestureDetector records the motion event
    override fun onTouchEvent(motionEvent: MotionEvent): Boolean {
        mScaleGestureDetector.onTouchEvent(motionEvent)
        return true
    }

    // Zooming in and out in a bounded range
    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mScaleFactor = max(0.1f, min(mScaleFactor, 10.0f))
            mImageView.scaleX = mScaleFactor
            mImageView.scaleY = mScaleFactor
            return true
        }
    }
}