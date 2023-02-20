package com.example.kidsdrawinhgapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import java.util.jar.Attributes

class DrawingView(context : Context, attrs: AttributeSet): View(context, attrs) {

    private var myDrawPath: CustomPath? = null
    private var myCanvasBitmap: Bitmap? = null
    private var myDrawPaint: Paint? = null
    private var myCanvasPaint: Paint? = null
    private var myBrushSize: Float = 0.toFloat()
    private var color = Color.BLACK
    private var canvas: Canvas? = null

    init {
        setUpDrawing()
    }

    private fun setUpDrawing() {
        myDrawPaint = Paint()
        myDrawPath = CustomPath(color, myBrushSize)
        myDrawPaint!!.color = color
        myDrawPaint!!.style = Paint.Style.STROKE
        myDrawPaint!!.strokeJoin = Paint.Join.ROUND
        myDrawPaint!!.strokeCap = Paint.Cap.ROUND
        myCanvasPaint = Paint(Paint.DITHER_FLAG)
        myBrushSize = 20.toFloat()
    }

    internal inner class CustomPath (var color: Int,
                                     var brush: Float): Path() {

    }

}