package com.example.easycounter

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val shared = getSharedPreferences("user_data", MODE_PRIVATE)

        //Get the value stored in memory
        val oldCounter = shared.getInt("counter", 0)
        val counter = Counter(oldCounter)
        val display : TextView = findViewById(R.id.display)
        val plusButton : Button = findViewById(R.id.button_plus)
        val minusButton : Button = findViewById(R.id.button_minus)
        val resetButton : Button = findViewById(R.id.button_reset)
        val setButton : Button = findViewById(R.id.button_set)
        val input : EditText = findViewById(R.id.num_box)

        //Initialize the display with the value memorized or with the default value 0
        display.text = counter.print()

        //Button plus increases the value by one, button minus subtracts one from the value
        plusButton.setOnClickListener {
            counter.add(1)
            shared.edit().putInt("counter", counter.n).apply()
            display.text = counter.print()
        }

        minusButton.setOnClickListener {
            counter.add(-1)
            shared.edit().putInt("counter", counter.n).apply()
            display.text = counter.print()
        }

        resetButton.setOnClickListener {
            counter.set(0)
            shared.edit().putInt("counter", 0).apply()
            display.text = counter.print()
        }

        setButton.setOnClickListener {
            try {
                val n = input.text.toString().toInt()
                counter.set(n)
            } catch (e : Exception){
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            }
            display.text = counter.print()
            input.text.clear()
        }
    }
}