package com.example.diceroller

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity()
{
    var mediaPlayer: MediaPlayer? = null
    var count = 0
    var score = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mediaPlayer = MediaPlayer.create(this,R.raw.tune2)

        val rollButton: Button = findViewById(R.id.button)
        Log.d("OneArmBandit", "onCreate method has been created");
        rollButton.setBackgroundColor(Color.BLUE)
        rollButton.setOnClickListener {
                count++
                val textview4 = findViewById(R.id.textView4) as TextView
                textview4.text = "Win/Spin ratio: $score/$count"
                val textview = findViewById(R.id.textView) as TextView
                textview.text = "You have spun $count times."

            startSlot()
        }
    }



    private fun startSlot() {
        //number of slots
        val slot = Slot(4)
        //slot 1
        val slotRoll = slot.roll();
        val slotImage: ImageView = findViewById(R.id.imageView)
        val drawableResource = when (slotRoll) {
            1 -> R.drawable.ssj1
            2 -> R.drawable.ssj2
            3 -> R.drawable.ssj3
            else -> R.drawable.ssj4
        }
        slotImage.setImageResource(drawableResource);

        //slot 2
        val slotRoll2 = slot.roll();
        val slotImage2: ImageView = findViewById(R.id.imageView2)
        val drawableResource2 = when (slotRoll2) {
            1 -> R.drawable.ssj1
            2 -> R.drawable.ssj2
            3 -> R.drawable.ssj3
            else -> R.drawable.ssj4
        }
        slotImage2.setImageResource(drawableResource2);

        //slot3
        val slotRoll3 = slot.roll();
        val slotImage3: ImageView = findViewById(R.id.imageView3)
        val drawableResource3 = when (slotRoll3) {
            1 -> R.drawable.ssj1
            2 -> R.drawable.ssj2
            3 -> R.drawable.ssj3
            else -> R.drawable.ssj4
        }
        slotImage3.setImageResource(drawableResource3);

        val delayButton: Button = findViewById(R.id.button);
        val gifImage : ImageView = findViewById(R.id.gokuGif)
        gifImage.isVisible = true

        //compares all 3 cards and gives a gif based on the result
        Log.d("Number's Generator", "Number ${slotRoll}, Number ${slotRoll2}, Number ${slotRoll3}")
        if(slotRoll == slotRoll2 && slotRoll2 == slotRoll3)
        {
            mediaPlayer?.start()
            gifImage.setImageResource(R.drawable.winner)
            Toast.makeText(this, "You win", Toast.LENGTH_SHORT).show()
            val textview3 = findViewById(R.id.textView3) as TextView
            score++
            textview3.text = "You have won $score times"
            delayButton.isEnabled = false;
            Handler().postDelayed({
                //delayButton.setBackgroundColor(Color.RED);
                delayButton.isEnabled =true;
            }, 3000)
        }
        else
        {
           // mediaPlayer?.pause()
            gifImage.setImageResource(R.drawable.loser)
            delayButton.isEnabled = false;
            val toast = Toast.makeText(this, "try again", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                //delayButton.setBackgroundColor(Color.RED);
                delayButton.isEnabled =true;

            }, 2500)
            //gifImage.isVisible = false
        }
    }


}
class Slot(val numSides: Int) {

    fun roll(): Int
    {
        return (1..numSides).random()
    }
}