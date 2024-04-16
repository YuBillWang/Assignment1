package com.example.core1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.media.MediaPlayer

class MainActivity : AppCompatActivity() {
    private var sum = 0
    private lateinit var mediaPlayer: MediaPlayer


    override fun onStart(){
        super.onStart()
        Log.i("LIFECYCLE","onStart")
    }
    override fun onResume(){
        super.onResume()
        Log.i("LIFECYCLE","onResume")
    }
    override fun onPause(){
        super.onPause()
        Log.i("LIFECYCLE","onPause")
    }
    override fun onStop(){
        super.onStop()
        Log.i("LIFECYCLE","onStop")
    }
    override fun onDestroy(){
        super.onDestroy()
        Log.i("LIFECYCLE","onDestroy")
        mediaPlayer.release()
    }
    override fun onRestart(){
        super.onRestart()
        Log.i("LIFECYCLE","onRestart")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE","onCreate")
        mediaPlayer = MediaPlayer.create(this, R.raw.bugle_tune)


        val add = findViewById<Button>(R.id.score)
        val sub = findViewById<Button>(R.id.steal)
        val reset = findViewById<Button>(R.id.reset)
        val txt2 = findViewById<TextView>(R.id.txt2)


        add.setOnClickListener {
            sum += 1
            txt2.text = sum.toString()
            changecolor(txt2)
            Log.i("LIFECYCLE","number added")
            if (sum == 15) {
                playSound()
            }
            if (sum > 15) {
                sum = 15;
                txt2.text = sum.toString()
            }

        }

        sub.setOnClickListener {
            sum -= 1
            if (sum < 0) {
                sum = 0
            }
            txt2.text = sum.toString()
            changecolor(txt2)
            Log.i("LIFECYCLE","number subtracted")
        }

        reset.setOnClickListener {
            sum = 0
            txt2.text = sum.toString()
            changecolor(txt2)
            Log.i("LIFECYCLE","sum reset")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("txt2",sum)
        Log.i("LIFECYCLE", "saveInstanceState $sum")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        sum = savedInstanceState.getInt("txt2")
        val txt2 = findViewById<TextView>(R.id.txt2)
        txt2.text = sum.toString()
        changecolor(txt2)
        Log.i("LIFECYCLE", "saveInstanceState $sum")
    }

    fun changecolor(txt2:TextView) {
        if (sum<5) {
            txt2.setTextColor(Color.BLACK)
            Log.i("LIFECYCLE","color changed")
        }
        else if (sum in 5..9) {
            txt2.setTextColor(Color.BLUE)
            Log.i("LIFECYCLE","color changed")
        }
        else{
            txt2.setTextColor(Color.GREEN)
            Log.i("LIFECYCLE","color changed")
        }
    }
    private fun playSound() {
            mediaPlayer.start()
            Log.i("LIFECYCLE", "Sound played")
        }
}