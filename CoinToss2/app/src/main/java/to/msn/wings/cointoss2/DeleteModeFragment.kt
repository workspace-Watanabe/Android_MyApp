package to.msn.wings.cointoss2

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import to.msn.wings.cointoss2.observer.ListObserver
import to.msn.wings.cointoss2.observer.Observer

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DeleteModeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class DeleteModeFragment : Fragment() {

    companion object{
        var deleteModeFragment : DeleteModeFragment? = null
    }
    private val fragment = ModeFlagment()
    private var dataList : ListView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        deleteModeFragment = this
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_delete_mode, container, false)
        //リストを取り出す
        dataList = view.findViewById(R.id.dataList)
        listChange(dataList!!)
        dataList?.onItemLongClickListener =  LongLogicListener()
        return view
    }

    private inner class LongLogicListener : AdapterView.OnItemLongClickListener{
        override fun onItemLongClick(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ): Boolean {
            val dataName = parent?.getItemAtPosition(position) as String
            val observer = ListObserver.getInstance()
            observer.deleteData(dataName)
            listChange(dataList!!)
            return true
        }
    }

    /**
     * 移譲して処理を任せる
     */
    fun listChange(list:ListView){
        fragment.listChange(list)
    }


}
