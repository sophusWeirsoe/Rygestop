package dk.sens.android.rygestop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : Fragment() {

    companion object {

        @JvmStatic
        fun newInstance() =
            Settings().apply {
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
         val rootView = inflater.inflate(R.layout.activity_settings, container, false)
         val seek = rootView.findViewById(R.id.seekBar) as SeekBar
         seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

             override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                 days.text = "$i  dage"
             }
             override fun onStartTrackingTouch(seekBar: SeekBar) {
                 // Do something

             }

             override fun onStopTrackingTouch(seekBar: SeekBar) {
                 // Do something

             }
         })


         return rootView
     }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        switch1.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled/checked
                textView5.text = "Slå notifikationer fra"

            } else {
                // The switch is disabled
                textView5.text = "Slå notifikationer til"

            }
        }



    }

}
