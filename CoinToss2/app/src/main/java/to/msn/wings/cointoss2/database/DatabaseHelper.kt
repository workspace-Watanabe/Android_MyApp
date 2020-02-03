package to.msn.wings.cointoss2.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {

    companion object{
        //データベースの定数フィールド
        private const val DATABASE_NAME = "myapp.db"
        //データベースのバージョン情報定数フィールド
        private const val DATABASE_VERSION = 1
    }
//====================================================================================//

    /**
     * テーブルを作成する
     */
    override fun onCreate(db: SQLiteDatabase?) {
       val sb = StringBuilder()
        sb.append("CREATE TABLE cointoss(")
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,")
        sb.append("name TEXT,")
        sb.append("category INTEGER")
        sb.append(");")
        val sql = sb.toString()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    /**
     * テーブルから値を取り出す
     */
    fun selectSQL() : MutableList<String>{
        val dbList = mutableListOf<String>()
        val sql =  "SELECT * FROM cointoss"
        val cursor = writableDatabase.rawQuery(sql,null)

        //cursorから結果をdbListに格納。
        while(cursor.moveToNext()){
            val index = cursor.getColumnIndex("name")
            dbList.add(cursor.getString(index))
        }
        return dbList
    }

    /**
     * テーブルに値を加える
     */
    fun insertSQL(dataName:String) : Long{
        val sql = "INSERT INTO cointoss (name) VALUES (?)"
        val stmt = writableDatabase.compileStatement(sql)
        stmt.bindString(1,dataName)
        val result =  stmt.executeInsert()
        return result
    }

    /**
     * テーブルの値を削除
     */
    fun deleteSQL(dataName: String) : Int{
        val sql = "DELETE FROM cointoss WHERE name=?"
        val stmt = writableDatabase.compileStatement(sql)
        stmt.bindString(1,dataName)
        val result =  stmt.executeUpdateDelete()
        return result
    }
}