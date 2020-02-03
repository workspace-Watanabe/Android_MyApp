package to.msn.wings.cointoss2.observer


import android.widget.Toast
import to.msn.wings.cointoss2.MainActivity
import to.msn.wings.cointoss2.database.DatabaseHelper

/**
 * リストの変化を加える場合、このクラスが処理をする
 */
class ListObserver : Observer{
    private constructor()

    companion object{
        //インスタンスは一つ
        private val singleTon = ListObserver()
        //インスタンスを取得
        fun getInstance() = singleTon
    }

    private var databaseHelper : DatabaseHelper? = null
    private var dbList : MutableList<String> = mutableListOf()
//========================================================================================//

    /**
     * 更新処理を行う。
     */
    override fun updata() : MutableList<String>{
        //データベースヘルパーを取得
        if(databaseHelper == null)
            databaseHelper = MainActivity.getInstance()?.getHelper()
        dbList = databaseHelper?.selectSQL()!!
        if(dbList.size <= 0) dbList.add("データがありません。")
        return dbList
    }

    /**
     *  リストを追加する
     */
    fun addData(dataName:String) : MutableList<String>{
        //データベースヘルパーを取得
        if(databaseHelper == null)
            databaseHelper = MainActivity.getInstance()?.getHelper()
        val result : Long?= databaseHelper?.insertSQL(dataName)
        if(result ?: 0 <= 0)
            Toast.makeText(MainActivity.getInstance(),"失敗しました",Toast.LENGTH_SHORT).show()
        dbList = updata()
        return dbList
    }

    /**
     * リストを削除
     */
    fun deleteData(dataName: String) {
        //データベースヘルパーを取得
        if(databaseHelper == null)
            databaseHelper = MainActivity.getInstance()?.getHelper()
        val result = databaseHelper?.deleteSQL(dataName)
        Toast.makeText(MainActivity.getInstance(),"${result}を削除しました。",Toast.LENGTH_SHORT).show()
    }

}