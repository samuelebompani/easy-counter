package com.easy.counter

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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
        val resetButton : Button = findViewById(R.id.button_reset)
        val setButton : Button = findViewById(R.id.button_set)
        val addButton : Button = findViewById(R.id.button_add)
        val setInput : EditText = findViewById(R.id.num_box)
        val addInput : EditText = findViewById(R.id.add_num_box)

        //Initialize the display with the value memorized or with the default value 0
        display.text = counter.print()

        //Button plus increases the value by one, button minus subtracts one from the value
        plusButton.setOnClickListener {
            try {
                counter.add(1)
                shared.edit().putInt("counter", counter.n).apply()
            } catch (e : Exception){}
            display.text = counter.print()
        }

        minusButton.setOnClickListener {
            try {
                counter.add(-1)
                shared.edit().putInt("counter", counter.n).apply()
            } catch (e : Exception){}
            display.text = counter.print()
        }

        resetButton.setOnClickListener {
            counter.set(0)
            shared.edit().putInt("counter", 0).apply()
            display.text = counter.print()
        }

        setButton.setOnClickListener {
            try {
                val n = setInput.text.toString().toInt()
                counter.set(n)
                shared.edit().putInt("counter", n).apply()
            } catch (e: Exception){
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            }
            display.text = counter.print()
            setInput.text.clear()
            hideKeyboard(this)
        }

        addButton.setOnClickListener {
            try {
                val n = addInput.text.toString().toInt()
                counter.add(n)
                shared.edit().putInt("counter", counter.n).apply()
            } catch (e: Exception){
                Toast.makeText(this, "Invalid number", Toast.LENGTH_SHORT).show()
            }
            display.text = counter.print()
            addInput.text.clear()
            hideKeyboard(this)
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view: View? = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}