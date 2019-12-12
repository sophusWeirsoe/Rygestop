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
import java.time.LocalDateTime

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





        var texts  = sharedPref.loadArrayList("key2", activity)
        if (texts == null)
        {
                texts = ArrayList<Text>()
                texts.add(Text("Ryge-afvænning tekst " + (texts.size +1).toString(), "Tryk for at læse", texts.size, false))
                sharedPref.saveArrayList(texts, "key2", activity)
        }


        var adapter = Adapter(texts,{text : Text -> ItemClicked(text)} )
        rv.adapter = adapter
        return rootView
    }



    private fun ItemClicked(text : Text) {
        val intent = Intent(context, Webview::class.java)
        intent.putExtra("ID", text.ID)

        val intent2 = Intent(context, TextAssignment::class.java)
        intent2.putExtra("ID", text.ID)

        if(!text.done) {
            startActivity(intent)
        }
        else {
            startActivity(intent2)

        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?)
    {
        super.onActivityCreated(savedInstanceState)

    }
}
