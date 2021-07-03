package com.emreyoldas.catchthethorkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_start_page.*

class StartPage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_page)
    }
    fun st(view: View){
        val intent = Intent(applicationContext,MainActivity::class.java)
        intent.putExtra("name",nameText.text.toString())

        startActivity(intent)

    }
    fun modetwo(view: View){
        val intent = Intent(applicationContext,Mode2Activity::class.java)
        intent.putExtra("name2",nameText.text.toString())

        startActivity(intent)
    }
}