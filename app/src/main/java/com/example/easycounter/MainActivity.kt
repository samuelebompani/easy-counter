package com.example.easycounter

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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

        //Initialize the display with the value memorized or with the default value 0
        display.text = counter.print()

        //Button plus increases the value by one, button minus subtracts one from the value
        plusButton.setOnClickListener {
            counter.add()
            shared.edit().putInt("counter", counter.n).apply()
            display.text = counter.print()
        }

        minusButton.setOnClickListener {
            counter.sub()
            shared.edit().putInt("counter", counter.n).apply()
            display.text = counter.print()
        }
    }
}