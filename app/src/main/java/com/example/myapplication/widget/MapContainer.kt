package com.example.myapplication.widget

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.maps.SupportMapFragment

class MapContainer : SupportMapFragment() {

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstance: Bundle?
    ): View {
        val layout = super.onCreateView(layoutInflater, viewGroup, savedInstance)
        val frameLayout = TouchableWrapper(requireContext())
        (layout as ViewGroup?)?.addView(
            frameLayout,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        return layout
    }

    inner class TouchableWrapper(context: Context) : FrameLayout(context) {

        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            when (event.action) {
                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_UP ->
                    requestDisallowInterceptTouchEvent(true)
            }
            return super.dispatchTouchEvent(event)
        }
    }
}