package dk.sens.android.rygestop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_data.*

class Webview : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        webview.loadUrl("file:///android_asset/html/text.html")
        webview.getSettings().setBuiltInZoomControls(true)
    }
}
