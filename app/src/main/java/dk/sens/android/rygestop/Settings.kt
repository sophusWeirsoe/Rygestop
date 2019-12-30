package dk.sens.android.rygestop

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.activity_text_assignment.*

class Settings : Fragment() {
    private var mAlarmManager : AlarmManager? = null
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
         val switch = rootView.findViewById(R.id.switch1) as Switch
         val days = rootView.findViewById(R.id.days) as TextView
         val save = rootView.findViewById(R.id.save) as Button
         var settings = sharedPref.loadSettings("key4", activity)
         days.text = settings.dage.toString()

         seek.setProgress(settings.dage)
         if (settings.notifications) {
             switch.isChecked = true
         }
         switch.setOnCheckedChangeListener { buttonView, isChecked ->
             if (isChecked) {
                 // The switch is enabled/checked
                 textView5.text = "Slå notifikationer fra"
                 settings.notifications = true

             } else {
                 // The switch is disabled
                 textView5.text = "Slå notifikationer til"
                 settings.notifications = false

             }
         }

         seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

             override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                 days.text = "$i  dage"
                 settings.dage = i
             }
             override fun onStartTrackingTouch(seekBar: SeekBar) {
                 // Do something

             }

             override fun onStopTrackingTouch(seekBar: SeekBar) {
                 // Do something

             }
         })

         save.setOnClickListener{
             val mIntent = Intent(activity, MyReceiver::class.java)
             val mPendingIntent = PendingIntent.getBroadcast(activity, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)
             mAlarmManager = activity?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
             sharedPref.saveSettings(settings, "key4",activity)

             mAlarmManager!!.cancel(mPendingIntent)
             mAlarmManager!!.setRepeating(
                     AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                     (settings.dage * 60 * 60*1000).toLong(), mPendingIntent
                 )

         }


         return rootView
     }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)








    }

}
