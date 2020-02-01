package to.msn.wings.impartialreferee

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListFragment1.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class ListFragment1 : Fragment() {
    companion object{
        val dataList  = mutableListOf("データがありません")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_fragment1, container, false)

        val listView = view.findViewById<ListView>(R.id.dataList1)
        val adapter = ArrayAdapter(view.context,android.R.layout.simple_list_item_1,dataList)
        listView.adapter = adapter
        return view
    }

}
