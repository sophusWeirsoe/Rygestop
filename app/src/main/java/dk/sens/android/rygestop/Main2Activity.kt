package dk.sens.android.rygestop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main2.*
import java.util.*

class Main2Activity : AppCompatActivity() {

    private val mNotificationTime = Calendar.getInstance().timeInMillis + 60000*60 //Set after one hour from the current time.
    private var mNotified = false

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_songs -> {
             //   toolbar.title = "Songs"
                val Overview = Overview.newInstance()
                openFragment(Overview)

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_albums -> {
                val ryge = ryge.newInstance()
                openFragment(ryge)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_artists -> {
                val data = Data.newInstance()
                openFragment(data)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

       val text = intent.getStringExtra("text") ?: ""
        if (text.equals("text")){
            openFragment(Data.newInstance())
        }
       else {
            openFragment(Overview.newInstance())
        }
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (!mNotified) {
            NotificationUtils().setNotification(mNotificationTime, this@Main2Activity)
        }

    }
    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

  }