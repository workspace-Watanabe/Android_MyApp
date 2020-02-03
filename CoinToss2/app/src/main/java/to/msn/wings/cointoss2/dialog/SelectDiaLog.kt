package to.msn.wings.cointoss2.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.activity_main.view.*
import to.msn.wings.cointoss2.MainActivity
import kotlin.random.Random

class SelectDiaLog(val dataList : MutableList<String>) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //--------------フラグメントに使うデータリストをセット----------------//
        val dataList = dataList
        val items = arrayOfNulls<String>(dataList.size)
        for((i,element) in dataList.withIndex()){
            items[i] = element
        }
        val selected = BooleanArray(items.size){it == -1}
        // -----------------------------------------------------------//

        //ダイアログを生成
        val builer = AlertDialog.Builder(activity)
        return builer
            .setTitle("選択してください。")
            .setIcon(android.R.drawable.sym_def_app_icon)
            .setMultiChoiceItems(items,selected)
            { dialog: DialogInterface, which : Int, isChecked :Boolean->
                selected[which] = isChecked
            }.setPositiveButton("判定する！！")
            { dialog: DialogInterface, which: Int ->
                val msg = arrayOfNulls<String>(selected.size)
                var count = 0
                for (i in 0 until selected.size) {
                    if (selected[i])
                        msg[count++] = items[i]
                }
                //ランダムで一つ選択!!
                if (count > 0) {
                    val randomResult = Random.nextInt(count)
                    MainActivity.getInstance()?.setResultText("${msg[randomResult]}にしましょうよ！")

                }
            }
            .create()
    }
}