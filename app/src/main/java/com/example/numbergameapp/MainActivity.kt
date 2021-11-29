package com.example.numbergameapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val myMessage = ArrayList<String>()
    var count = 4
    val randomNum = Random.nextInt(10)
    private lateinit var myLayout: ConstraintLayout
    private lateinit var button_click: Button
    private lateinit var txt_Enter: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
myLayout = findViewById(R.id.clMain)
        txt_Enter = findViewById(R.id.numGuessed)
        button_click = findViewById(R.id.bttn_click)

        button_click.setOnClickListener {
            if (txt_Enter.text.toString().toInt() == randomNum && count > 1 ){
            myMessage.add("Your guess ${txt_Enter.text.toString()} is correct ")
                myMessage.add("Your win :)")
                myMessage.add("GAME OVER")
            val myRV = findViewById<RecyclerView>(R.id.rvGuesses)
            myRV.adapter = RecyclerViewAdapter(myMessage)
            myRV.layoutManager = LinearLayoutManager(this)
            txt_Enter.text = null
                bttn_click.isEnabled = false
                txt_Enter.isEnabled = false
               }
            else if (txt_Enter.text.toString().toInt() != randomNum && count > 1){
                count -=1
                myMessage.add("Your guess ${txt_Enter.text.toString()} is wrong :( try again $randomNum")
                myMessage.add("Remember you have $count attempts left ")
                val myRV = findViewById<RecyclerView>(R.id.rvGuesses)
                myRV.adapter = RecyclerViewAdapter(myMessage)
                myRV.layoutManager = LinearLayoutManager(this)
                txt_Enter.text = null
            }
            else{
                myMessage.add("Attempts are OVER, you lost :( " )
                myMessage.add("GAME OVER")
                val myRV = findViewById<RecyclerView>(R.id.rvGuesses)
                myRV.adapter = RecyclerViewAdapter(myMessage)
                myRV.layoutManager = LinearLayoutManager(this)
                bttn_click.isEnabled = false
                txt_Enter.isEnabled = false
            }
        }
    }
}