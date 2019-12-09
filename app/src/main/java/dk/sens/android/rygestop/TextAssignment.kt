package dk.sens.android.rygestop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_webview.*

class TextAssignment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_assignment)

        imageButtonNext.setOnClickListener{
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("text", "text")
            startActivity(intent)
        }



    }
}
