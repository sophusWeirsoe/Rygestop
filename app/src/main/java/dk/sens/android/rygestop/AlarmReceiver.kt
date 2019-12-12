package dk.sens.android.rygestop

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val service = Intent(context, NotificationService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))

        var texts  = sharedPref.loadArrayList("key2", context)
        texts.add(Text("Ryge-afvænning tekst " + (texts.size +1).toString() , "Tryk for at læse", texts.size,false))
        sharedPref.saveArrayList(texts, "key2", context)


        context.startService(service)
    }
}