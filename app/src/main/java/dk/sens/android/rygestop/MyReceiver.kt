package dk.sens.android.rygestop

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var texts  = sharedPref.loadArrayList("key2", context)
        var settings = sharedPref.loadSettings("key4", context)
        texts.add(Text("Ryge-afvænning tekst " + (texts.size +1).toString() , "Tryk for at læse", texts.size,false))
        sharedPref.saveArrayList(texts, "key2", context)

        if(settings.notifications)
        {
notification.createNotification(context,texts.size)
        }



    }
}
