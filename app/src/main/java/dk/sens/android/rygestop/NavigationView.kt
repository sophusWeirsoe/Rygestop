package dk.sens.android.rygestop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main2.*

class NavigationView : AppCompatActivity()
{

   // private val mNotificationTime = Calendar.getInstance().timeInMillis + 1000
    var slideValue = 0

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId)
        {
            R.id.navigation_overblik ->
            {

                val Overview = Overblik.newInstance()
              if (slideValue.equals(0)){
                  openFragment(Overview, 1)
              }
                else {

                  openFragment(Overview, 2)
              }
                slideValue = 0
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_settings ->
            {
                val settings = Indstillinger.newInstance()

                if (slideValue.equals(0)){
                    openFragment(settings, 3)
                }
                if (slideValue.equals(1)){
                    openFragment(settings, 1)
                }
                if (slideValue.equals(2)){
                openFragment(settings, 2)
            }
                slideValue = 1
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_text ->
            {
                val text = textList.newInstance()
                if (slideValue.equals(2)){
                    openFragment(text, 1)
                }
                else {

                    openFragment(text, 3)
                }
                slideValue = 2
                return@OnNavigationItemSelectedListener true
            }

        }

        false
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        notification.createNotificationChannel(this)

       val text = intent.getStringExtra("text") ?: ""
        if (text.equals("text"))
        {
            openFragment(textList.newInstance(), 1)
        }
       else
        {
            openFragment(Overblik.newInstance(),1)
        }
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

      //  NotificationUtils().setNotification(mNotificationTime, this@Main2Activity)


    }
    private fun openFragment(fragment: Fragment, slideAnim: Int)
    {
        val transaction = supportFragmentManager.beginTransaction()
        if(slideAnim.equals(3)){
            transaction.setCustomAnimations(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        if(slideAnim.equals(2)){
            transaction.setCustomAnimations(R.anim.slide_from_left,R.anim.slide_to_right)
        }
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

  }