package dk.sens.android.rygestop

import java.time.LocalDateTime
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_overview.*
import java.time.Duration

class Overview : Fragment() {
    var condition = false
    val mainHandler = Handler(Looper.getMainLooper())
    companion object {

        @JvmStatic
        fun newInstance() =
            Overview().apply {
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
            return inflater.inflate(R.layout.activity_overview, container, false)
        }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



        mainHandler.post(object : Runnable
        {
            override fun run()
            {
if(!condition){
                smoked()
                mainHandler.postDelayed(this, 1000)
            }}
        })
    }
    fun smoked(){
        val current = LocalDateTime.now()
        var load = sharedPref.load("key", activity)
        if (load == null)
        {
            load = LocalDateTime.now()
            sharedPref.save(current, "key", activity)
        }


        val between = Duration.between(load, current)
        // val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
        // val formatted = between.format(formatter)

        textView2.text = between.toString()


    }

    override fun onStop() {
        super.onStop()
        condition = true
    }
}

