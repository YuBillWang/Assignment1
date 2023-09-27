package com.example.core1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var a = 0
    private var sum = 0
    private val random = Random(1)


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
    }
    override fun onRestart(){
        super.onRestart()
        Log.i("LIFECYCLE","onRestart")
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE","onCreate")

        val roll = findViewById<Button>(R.id.roll)
        val add = findViewById<Button>(R.id.add)
        val sub = findViewById<Button>(R.id.sub)
        val reset = findViewById<Button>(R.id.reset)
        val txt1 = findViewById<TextView>(R.id.txt1)
        val txt2 = findViewById<TextView>(R.id.txt2)
        var isRolled = false

        roll.setOnClickListener {
            if(!isRolled) {
                a = roll()
                txt1.text = a.toString()
                isRolled = true
                Log.i("LIFECYCLE","Dice rolled once")
            }

        }

        add.setOnClickListener {
            if(isRolled) {
                sum += a
                txt2.text = sum.toString()
                changecolor(txt2)
                a = 0
                isRolled = false
                Log.i("LIFECYCLE","number added")
            }
        }

        sub.setOnClickListener {
            if(isRolled) {
                sum -= a
                if (sum < 0) {
                    sum = 0
                }
                txt2.text = sum.toString()
                changecolor(txt2)
                a = 0
                isRolled = false
                Log.i("LIFECYCLE","number subtracted")
            }
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
        Log.i("LIFECYCLE", "saveInstanceState $sum")
    }

   /* fun roll():Int{
        return  (1..6).random()
    }*/
    fun roll():Int{
        return random.nextInt(6)+1
    }

    fun changecolor(txt2:TextView) {
        if (sum<20) {
            txt2.setTextColor(Color.BLACK)
            Log.i("LIFECYCLE","color changed")
        }
        else if (sum==20) {
            txt2.setTextColor(Color.GREEN)
            Log.i("LIFECYCLE","color changed")
        }
        else if (sum>20) {
            txt2.setTextColor(Color.RED)
            Log.i("LIFECYCLE","color changed")
        }
    }
}