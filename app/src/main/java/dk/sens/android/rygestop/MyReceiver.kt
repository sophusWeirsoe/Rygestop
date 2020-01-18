package dk.sens.android.rygestop

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        var texts = sharedPref.loadArrayList("key2", context)
        var settings = sharedPref.loadSettings("key4", context)


        if (texts.size < 5) {

            val title = "Titel_" + Integer.toString(texts.size + 1)
            val r = context.getResources().getIdentifier(title, "string", context.getPackageName())
            texts.add(Text(context.getString(r), "Tryk for at læse", texts.size, false))
            sharedPref.saveArrayList(texts, "key2", context)

            if (settings.notifications) {
                notification.createNotification(context, texts.size)
            }


        }
    }
}
