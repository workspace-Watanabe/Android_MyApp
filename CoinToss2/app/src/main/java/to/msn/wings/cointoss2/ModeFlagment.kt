package to.msn.wings.cointoss2

import android.R
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import to.msn.wings.cointoss2.observer.ListObserver

class ModeFlagment : Fragment(){
    private var dbList : MutableList<String> = mutableListOf()

    /**
     * リストを変更
     */
    fun listChange(list: ListView){
        val observer = ListObserver.getInstance()
        dbList = observer.updata()
        val adapter = ArrayAdapter(MainActivity.getInstance()!!, R.layout.simple_list_item_1,dbList)
        list.adapter = adapter
    }
}