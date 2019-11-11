package dk.sens.android.rygestop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.crashlytics.android.Crashlytics
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_ryge.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ryge : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() =
            ryge().apply {
                arguments = Bundle().apply {
                    // putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // param1 = it.getString(ARG_PARAM1)
        }
    }

     override fun onCreateView(
         inflater: LayoutInflater, container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
         // Inflate the layout for this fragment
         return inflater.inflate(R.layout.activity_ryge, container, false)
     }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        stressBtn.setOnClickListener{
            smoked("Stress")
           // Crashlytics.getInstance().crash() // Force a crash
        }

        alkoBtn.setOnClickListener {
            smoked("Alkohol")
        }

        sociBtn.setOnClickListener {
            smoked("Socialt")
        }

        udBtn.setOnClickListener {
            smoked("Udmattet")
        }

    }

    fun smoked(grund: String){
        val current = LocalDateTime.now()
        sharedPref.save(current, "key", activity)

        val database = FirebaseDatabase.getInstance().getReference("Smoke")
        val id = database.push().key
        val formatter = DateTimeFormatter. ofPattern("yyyy-MM-dd")
        val formatted = current.format(formatter)
        val smokey = smoked(grund, formatted)
        Toast.makeText(getActivity(),"Data send til firebase", Toast.LENGTH_SHORT).show();
        database.child(id.toString()).setValue(smokey)



    }
}
