package com.emreyoldas.catchthethorkotlin

import android.content.DialogInterface
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_start_page.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var score = 0
    var runnable = Runnable { }
    var handler = Handler(Looper.getMainLooper())
    var imagearray = ArrayList<ImageView>()
    var mediaPlayer = MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //imagearray
        imagearray.add(imageView)
        imagearray.add(imageView2)
        imagearray.add(imageView3)
        imagearray.add(imageView4)
        imagearray.add(imageView5)
        imagearray.add(imageView6)
        imagearray.add(imageView7)
        imagearray.add(imageView8)
        imagearray.add(imageView9)
        hideImages()
        //this is background music
        mediaPlayer = MediaPlayer.create(applicationContext,R.raw.modfifteen)
        mediaPlayer.start()
        //the player name
        val intent = intent
        val name = intent.getStringExtra("name")
        textView.text= name
        //The CountDown
        object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeText.text = "Zaman: " + millisUntilFinished / 1000
            }

            override fun onFinish() {
                timeText.text = "Zaman: 0"
                handler.removeCallbacks(runnable)
                for (image in imagearray) {
                    image.visibility = View.INVISIBLE
                }
                //AlertDialog
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("Oyun Bitti")
                alertDialog.setMessage("Oyunu Tekrar Başlatmak İstermisiniz")
                alertDialog.setPositiveButton("Evet",) { dialog, which ->
                    //Restart
                    val intent = intent
                    finish()
                    startActivity(intent)
                }
                alertDialog.setNegativeButton("Hayır",) { dialog, which ->

                    Toast.makeText(this@MainActivity, "Oyun Bitti", Toast.LENGTH_SHORT).show()
                    val intent = intent
                    finish()

                }
                alertDialog.show()
            }


        }.start()


    }
    //This is Options menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId){
                R.id.cikis -> {
                   var intent = Intent(applicationContext,StartPage::class.java)
                    startActivity(intent)
                    true
                }
            else -> super.onOptionsItemSelected(item)
        }
        return super.onOptionsItemSelected(item)


        }


    fun hideImages() {
        runnable = object : Runnable {
            override fun run() {
                for (image in imagearray) {
                    image.visibility = View.INVISIBLE

                }
                val random = Random
                val randomIndex = random.nextInt(9)
                imagearray[randomIndex].visibility = View.VISIBLE
                handler.postDelayed(runnable, 500)
            }

        }
        handler.post(runnable)
    }
        fun increaseScore(view: View) {
            score++
            scoreText.text = "Score $score"
        }
   // check if background music is working when app is in background
    override fun onResume() {
        super.onResume()
        mediaPlayer.start()
    }
    override fun onStop() {
        super.onStop()
        mediaPlayer.pause()

    }

}