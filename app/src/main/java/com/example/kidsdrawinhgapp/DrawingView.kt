package com.example.kidsdrawinhgapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
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
    private val mPaths = ArrayList<CustomPath>()

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

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        myCanvasBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(myCanvasBitmap!!)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(myCanvasBitmap!!,0f,0f, myCanvasPaint)

        for (path in mPaths){
            myDrawPaint!!.strokeWidth = path.brushThickness
            myDrawPaint!!.color = path.color
            canvas.drawPath(path, myDrawPaint!!)
        }

        if (!myDrawPath!!.isEmpty){
            myDrawPaint!!.strokeWidth = myDrawPath!!.brushThickness
            myDrawPaint!!.color = myDrawPath!!.color
            canvas.drawPath(myDrawPath!!, myDrawPaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y

        when(event?.action){

            MotionEvent.ACTION_DOWN -> {
                myDrawPath!!.color = color
                myDrawPath!!.brushThickness = myBrushSize

                myDrawPath!!.reset()
                myDrawPath!!.moveTo(touchX!!, touchY!!)
            }
            MotionEvent.ACTION_MOVE -> {
                myDrawPath!!.lineTo(touchX!!, touchY!!)
            }
            MotionEvent.ACTION_UP -> {
                mPaths.add(myDrawPath!!)
                myDrawPath = CustomPath(color, myBrushSize)
            }
            else -> { return false }
        }
        invalidate()

        return true
    }

    fun setSizeForBrush (newSize: Float) {
        myBrushSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
        newSize, resources.displayMetrics)
        myDrawPaint!!.strokeWidth = myBrushSize
    }

    fun setColor(newColor: String) {
        color = Color.parseColor(newColor)
        myDrawPaint!!.color = color
    }

    internal inner class CustomPath (var color: Int,
                                     var brushThickness: Float): Path() {

    }

}