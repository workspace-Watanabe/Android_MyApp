package to.msn.wings.cointoss2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import to.msn.wings.cointoss2.observer.ListObserver
import to.msn.wings.cointoss2.observer.Observer

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddModeFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 */
class AddModeFragment : Fragment() {
    companion object{
        var addModeFragment: AddModeFragment? = null
    }

    private var dbList : MutableList<String> = mutableListOf()
    private val fragment = ModeFlagment()
    private var dataList : ListView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //インスタンスを得る
        addModeFragment = this
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_mode, container, false)
        //リストを取り出す
        dataList = view.findViewById(R.id.dataList)
        listChange(dataList!!)
        return view
    }

    /**
     * 移譲して処理を任せる
     */
    fun listChange(list:ListView){
       fragment.listChange(list)
    }

    /**
     * リストにデータを追加する
     */
    fun addData(dataName:String){
         val observer = ListObserver.getInstance()
         observer.addData(dataName)
        listChange(dataList!!)
    }


}
