package dk.sens.android.rygestop


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_data.*

class Data : Fragment()
{

    companion object
    {

        @JvmStatic
        fun newInstance() =
            Data().apply{
                arguments = Bundle().apply{
                    // putString(ARG_PARAM1, param1)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        arguments?.let {
            // param1 = it.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView
                (
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
                ): View?
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_data, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)
        //  webview.settings.setBuiltInZoomControls(true);
        webview.loadUrl("file:///android_asset/html/text.html")
        webview.getSettings().setBuiltInZoomControls(true)

    }
    }
