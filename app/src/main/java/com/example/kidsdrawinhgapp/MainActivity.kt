package com.example.kidsdrawinhgapp

import android.app.Activity
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.size
import androidx.databinding.DataBindingUtil
import com.example.kidsdrawinhgapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var drawingViewLayout : DrawingView? = null
    var myImageButtonCurrentPaint : ImageButton? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        drawingViewLayout = findViewById(R.id.drawing_view)
        drawingViewLayout?.setSizeForBrush(20.toFloat())

        binding.icBrush.setOnClickListener {
            showBrushSizeChooserDialogue()
        }

        myImageButtonCurrentPaint = binding.llPaleletColor[1] as ImageButton
        myImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )

    }

    fun showBrushSizeChooserDialogue() {
        val brushDialogue = Dialog(this)
        brushDialogue.setContentView(R.layout.dialog_brush_size)
        brushDialogue.setTitle("Tamanho do Brush: ")

        val smallBtn = brushDialogue.findViewById<ImageButton>(R.id.ib_small_brush)
        val mediumBtn = brushDialogue.findViewById<ImageButton>(R.id.ib_medium_brush)
        val largeBtn = brushDialogue.findViewById<ImageButton>(R.id.ib_large_brush)

        smallBtn.setOnClickListener {
            drawingViewLayout?.setSizeForBrush(10.toFloat())
            brushDialogue.dismiss()
        }

        mediumBtn.setOnClickListener {
            drawingViewLayout?.setSizeForBrush(20.toFloat())
            brushDialogue.dismiss()
        }

        largeBtn.setOnClickListener {
            drawingViewLayout?.setSizeForBrush(30.toFloat())
            brushDialogue.dismiss()
        }

        brushDialogue.show()
    }

    fun paintClicked(view: View) {

        if (view != myImageButtonCurrentPaint) {
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            drawingViewLayout!!.setColor(colorTag)

            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )

            myImageButtonCurrentPaint?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )

            myImageButtonCurrentPaint = view
        }

    }


}