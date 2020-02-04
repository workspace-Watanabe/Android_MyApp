package to.msn.wings.cointoss2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import org.w3c.dom.Text
import to.msn.wings.cointoss2.database.DatabaseHelper
import to.msn.wings.cointoss2.dialog.SelectDiaLog

class MainActivity : AppCompatActivity() {
    companion object{
        private var mainActivity : MainActivity? = null                     //このクラスのインスタンス
        private var addModeFragment : AddModeFragment? = null               //挿入モードのインスタンス
        private var deleteModeFragment : DeleteModeFragment? = null         //削除モードのインスタンス

        fun getInstance() = mainActivity
    }
    private var helper : DatabaseHelper? = null //データベースヘルパーのインスタンス
    fun getHelper() = helper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //------------------------------------------------------------------------------------//
        mainActivity = this
        helper = DatabaseHelper(getInstance()!!)
        //--------------------------------------------------------------------------------//
        ////////////////////////////////////////////////////////////////////////////////////
        val modeSwitch :Switch = findViewById(R.id.modeSwitch)
        addModeFragment = AddModeFragment()
        deleteModeFragment = DeleteModeFragment()
        changeFragment(addModeFragment!!)
        modeSwitch.setOnCheckedChangeListener(ChangeModeListener())
        ////////////////////////////////////////////////////////////////////////////////////

    }

// ======================================================================================================//


    /**
     * 削除モードか挿入モードに変わったときにフラグメントを変更する
     *  削除モード : false
     *  挿入モード : true
     */
    private inner class ChangeModeListener : CompoundButton.OnCheckedChangeListener{
        override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
            val swicthText = findViewById<TextView>(R.id.tvModeSwictch)
            //削除モードか挿入モードのテキストを変更
            swicthText.setText(if (isChecked) R.string.tv_deleteModeON else R.string.tv_deleteModeOFF)
            //フラグメントをチェンジ
            changeFragment(if(isChecked) addModeFragment!! else deleteModeFragment!!)
        }
    }

    /**
     * フラグメントを切り替える処理
     */
    private fun changeFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.foundation, fragment)
        fragmentTransaction.commit()
    }

    /**
     * データを挿入が押された時
     */
    fun onClickAddButton(view: View){
        val edData = findViewById<EditText>(R.id.edDataName)
        val dataName = edData.text.toString()
        if(dataName != ""){
            edData.setText("")
            addModeFragment?.addData(dataName)
        }else{
            Toast.makeText(applicationContext,"データ名を入力してください。",Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 【判定開始】ボタンを押したとき
     */
    fun onClickJudgeButton(view: View){
        //データベースから値を取り出す。
        val dataList = helper?.selectSQL()
        val dialogFragment = SelectDiaLog(dataList!!)
        dialogFragment.show(supportFragmentManager,"判定開始")
    }

    /**
     * 結果をセット
     */
    fun setResultText(message: String){
        val messageText : TextView = findViewById(R.id.tvResultset)
        messageText.setText(message)
    }


}
