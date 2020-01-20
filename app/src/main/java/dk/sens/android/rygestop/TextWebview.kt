package dk.sens.android.rygestop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data.*
import kotlinx.android.synthetic.main.activity_data.webview
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_webview.*
import java.io.InputStream
//comment
class TextWebview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val ID = intent.getIntExtra("ID", 0)
        val text = intent.getStringExtra("text-" + ID.toString())

        webview.loadUrl("file:///android_asset/text" +(ID+1)+ ".html")
        webview.getSettings().setBuiltInZoomControls(true)









        imageButtonNext.setOnClickListener {
            val intent = Intent(this, TextAssignment::class.java)
            intent.putExtra("ID",ID)
            intent.putExtra("text-" + ID.toString(),text)
            startActivity(intent)
        }

        share.setOnClickListener {

           share()
        }
    }

    fun share(){
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/html"

        val inputStream: InputStream = assets.open("text2.html")
        val inputString = inputStream.bufferedReader().use{it.readText()}

        i.putExtra(Intent.EXTRA_TEXT, inputString)
        startActivity(Intent.createChooser(i, "Share text"))

    }
}
