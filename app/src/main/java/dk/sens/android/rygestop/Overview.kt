package dk.sens.android.rygestop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_overview.*


class Overview : Fragment() {

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
        var texts  = sharedPref.loadArrayList("key2", context)
        textView1.text = "Tekster: " + texts.size
        textView2.text = "Opgaver besvarede: " + read(true,texts)
        textView3.text = "Nye tekster: " + read(false,texts)
    }

    fun read(boolean: Boolean,texts: ArrayList<Text>): Int
    {
        var n = 0
        var m = 0
        for(i in 0..texts.size -1)
        {

         if(texts.get(i).done == boolean)
{
    n = n+1
}

    }
        return n
    }


    override fun onStop() {
        super.onStop()
    }
}

