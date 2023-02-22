package com.example.kidsdrawinhgapp

import android.app.Activity
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import androidx.databinding.DataBindingUtil
import com.example.kidsdrawinhgapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var drawingViewLayout : DrawingView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawingViewLayout = findViewById(R.id.drawing_view)
        drawingViewLayout?.setSizeForBrush(20.toFloat())

        binding.icBrush.setOnClickListener {
            showBrushSizeChooserDialogue()
        }

    }

    fun showBrushSizeChooserDialogue() {
        //var drView = drawingViewLayout
        val brushDialogue = Dialog(this)
        brushDialogue.setContentView(R.layout.dialog_brush_size)
        brushDialogue.setTitle("Tamanho do Brush: ")

        val smallBtn = brushDialogue.findViewById<ImageButton>(R.id.ib_small_brush)
        smallBtn.setOnClickListener {
            drawingViewLayout?.setSizeForBrush(10.toFloat())
            brushDialogue.dismiss()
        }
        brushDialogue.show()
    }



}