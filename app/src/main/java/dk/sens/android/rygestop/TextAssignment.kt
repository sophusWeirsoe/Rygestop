package dk.sens.android.rygestop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.google.android.material.textfield.TextInputEditText
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_text_assignment.*
import kotlinx.android.synthetic.main.activity_webview.imageButtonNext


class TextAssignment : AppCompatActivity() {
   // lateinit var post3: String

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_assignment)
        val assignment = findViewById<TextInputEditText>(R.id.assignment)
        val ID = intent.getIntExtra("ID", 0)
        val opgaveString = "Opgave_" + Integer.toString(ID+1)
        val rr = getResources().getIdentifier(opgaveString, "string", getPackageName())

        opgave.setText(getString(rr))
        var texts  = sharedPref.loadArrayList("key2", this)


if (texts.get(ID).done)
{
    firebaseRead(ID)
}
        else
{
    val text = intent.getStringExtra("text-" + ID.toString())
    assignment.setText(text)
}

        imageButtonNext.setOnClickListener{
            firebaseWrite(assignment.text.toString(), ID)
            if (!texts.get(ID).done)
            {

            texts.set(ID, Text(texts.get(ID).title, texts.get(ID).des, texts.get(ID).ID, true))
            sharedPref.saveArrayList(texts, "key2", this)
            }
            succes.setAnimation("success.json")
            succes.playAnimation()
            val handler = Handler()
            handler.postDelayed(Runnable {
                val intent = Intent(this, NavigationView::class.java)
                intent.putExtra("text", "text")
                startActivity(intent)
            }, 1500)
        }


        imageButtonBack.setOnClickListener{
            val intent = Intent(this, TextWebview::class.java)
            intent.putExtra("ID", ID)
            intent.putExtra("text-" + ID.toString(), assignment.text.toString())
            startActivity(intent)

        }



    }

    fun firebaseWrite(svar: String, ID: Int)
    {
        val current = LocalDateTime.now()
        sharedPref.save(current, "key", this)

        val database = FirebaseDatabase.getInstance().getReference("Assignment")
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        val svar2 = Svar(svar, formatted)
        Toast.makeText(this,"Data send til firebase", Toast.LENGTH_SHORT).show()


        database.child(sharedPref.loadUUID("key3", this)).child("Text-" + ID).setValue(svar2)
        // Crashlytics.getInstance().crash() // Force a crash


    }

    fun firebaseRead(ID: Int)
    {
        val database = FirebaseDatabase.getInstance().getReference("Assignment").child(sharedPref.loadUUID("key3", this)).child("Text-" + ID).child("svar")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
               val post = dataSnapshot.getValue(String::class.java)
                assignment.setText(post)
              //  Log.i("TAG", post)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("TAG", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }
        database.addListenerForSingleValueEvent(postListener)






    }
}
