package to.msn.wings.impartialreferee

import android.database.sqlite.SQLiteStatement
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    //データベースヘルパーオブジェクト
    private var helper = DatabaseHelper(this@MainActivity)
    //SQLSTATEMENT
    private var stmt : SQLiteStatement? = null
    //データリストを格納する
    val dataList : MutableList<String> = mutableListOf()
    //ListView
    var listView : ListView? = null


    /**
     * Activityが立ち上がった時
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -----------------------------------------------------------------------//

        selectData()
        listView = findViewById(R.id.dataList1)
        listView?.onItemClickListener = onItemClickListener()
        val adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,dataList)
        listView?.adapter = adapter
        val switch = findViewById<Switch>(R.id.switch1)
        //スイッチがオンかオフになったときのリスナークラス
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            val swText = findViewById<TextView>(R.id.swText)
            if (isChecked)
                swText.setText(R.string.sw_deleteModeON)
            else
                swText.setText(R.string.sw_deleteModeOFF)
        }
    }


    /**
     * リストがタップされた時の処理が記述されたメンバクラス
     */
    private inner class onItemClickListener :AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            val isSwitch = findViewById<Switch>(R.id.switch1)
            //スイッチがONならば...
            if (isSwitch!!.isChecked) {
                val item = parent?.getItemAtPosition(position) as String
                val message = "${item}を削除しました"
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                val helper = DatabaseHelper(view!!.context)
                val db = helper.writableDatabase
                val sql = "DELETE FROM elements WHERE name = ?"
                val stmt = db.compileStatement(sql)
                stmt.bindString(1, item)
                stmt.executeUpdateDelete()
                //初期化
                removeDataList()
                selectData()
            }
        }
    }

    /**
     * 【判定を開始する】
     */
    fun onClickSelectButton(view: View){
        removeDataList()
        selectData()
        val dialogFragment = SelectDialogFragment(dataList)
        dialogFragment.show(supportFragmentManager,"aa")
    }


    /**
     * 【データを登録する】
     */
    fun onClickAddDataButton(view: View){
        //データ名
        val edData = findViewById<EditText>(R.id.edData)
        val db = helper.writableDatabase


        //SQL文を用意
        val sqlInsert = "INSERT INTO elements (name) VALUES (?)"
        stmt = db.compileStatement(sqlInsert)
        stmt?.bindString(1,edData.text.toString())
        edData.setText("")
        //インサートの実効
        stmt?.executeInsert()
        //初期化
        removeDataList()
        //select
        selectData()
    }

    /**
     * データベースからデータを取り出す
     */
    private fun selectData(){
        val db = helper.writableDatabase
        val sqlSelect = "SELECT * FROM elements"
        val cursor = db.rawQuery(sqlSelect,null)
        while(cursor.moveToNext()){
            val column = cursor.getColumnIndex("name")
            dataList.add(cursor.getString(column))
        }
        val adapter = ArrayAdapter(applicationContext,android.R.layout.simple_list_item_1,dataList)
        listView?.adapter = adapter
    }

    /**
     * データリストを初期化する
     */
    private fun removeDataList(){
        dataList.clear()
    }



    override fun onDestroy() {
        helper.close()
        super.onDestroy()
    }






}
