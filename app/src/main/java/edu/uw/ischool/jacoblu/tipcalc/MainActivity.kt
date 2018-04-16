package edu.uw.ischool.jacoblu.tipcalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val amount = findViewById(R.id.billValue) as EditText
        val button = findViewById(R.id.button) as Button
        val seekbar = findViewById(R.id.seekBar) as SeekBar
        val tipText = findViewById(R.id.tipAmount) as TextView


        seekbar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                tipText.setText("Tip Amount: " + seekbar.getProgress().toString() + "%")
            }
        }

        )
        button.setEnabled(false)
        amount.setText("$     ");
        amount.setSelection(amount.getText().length);
        amount.addTextChangedListener(object: TextWatcher{
            var checkString: Boolean = false

            override fun afterTextChanged(p0: Editable?) {
                if (!checkString)
                {
                    var string = p0.toString()
                    string = string.replace(".", "")
                    string = string.replace("$", "")
                    string = string.replace("-", "")
                    string = string.replace(" ", "")
                    if (string.length == 0)
                        string = "$"
                    else if (string.length == 1)
                        string = "$" + string
                    else if (string.length == 2)
                        string = "$" + string
                    else if (string.length > 2)
                        string = "$" + string.substring(0, string.length - 2) + "." + string.substring(string.length - 2, string.length)
                    checkString = true
                    amount.setText(string)
                    amount.setSelection(amount.getText().length)
                    checkString = false
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().equals("")) {
                    button.setEnabled(false)
                } else {
                    button.setEnabled(true)
                }

            }

        })

        button.setOnClickListener {
            var string = amount.getText().toString()
            string = string.replace("$", "")
            string = string.replace("-", "")
            string = string.replace(" ", "")
            var amountNumber : Double = string.toDouble()
            var tip: String = String.format("%.2f",(amountNumber * 0.01 * seekbar.getProgress()))
            Toast.makeText(this@MainActivity, "Tip Amount: $${tip}", Toast.LENGTH_LONG).show()
        }


    }
}
