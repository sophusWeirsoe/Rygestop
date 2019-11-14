package dk.sens.android.rygestop
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_data.*

class textListFragment : Fragment(){

    companion object
    {

        @JvmStatic
        fun newInstance() =
            textListFragment().apply{
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
        val rootView = inflater.inflate(R.layout.activity_text_list, container, false)
        val rv = rootView.findViewById(R.id.recyclerView1) as RecyclerView // Add this
        rv.layoutManager = LinearLayoutManager(activity)
        val texts = ArrayList<Text>()
        texts.add(Text("Text1", "Click to read"))
        var adapter = Adapter(texts,{text : Text -> ItemClicked(text)} )
        rv.adapter = adapter
        return rootView
    }



    private fun ItemClicked(text : Text) {
        val intent = Intent(context, Webview::class.java)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

    }
}
