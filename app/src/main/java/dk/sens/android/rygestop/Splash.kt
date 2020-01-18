package dk.sens.android.rygestop

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
setupAnimation()

        val handler = Handler()
        handler.postDelayed(Runnable {
            val intent = Intent(this@Splash, Main2Activity::class.java)
            startActivity(intent)
            finish()
        }, 2000)
    }



    fun setupAnimation(){
        smoke.setAnimation("smoke.json")
        smoke.playAnimation()
        smoke.loop(true)
}
}
