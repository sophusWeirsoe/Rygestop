package dk.sens.android.rygestop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_data.webview
import kotlinx.android.synthetic.main.activity_ryge.*
import kotlinx.android.synthetic.main.activity_webview.*

class Webview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webview.loadUrl("file:///android_asset/html/text.html")
        webview.getSettings().setBuiltInZoomControls(true)

        imageButtonNext.setOnClickListener {
            val intent = Intent(this, TextAssignment::class.java)
            startActivity(intent)
        }



    }




    fun atButtom(){

    }
}
