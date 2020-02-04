package to.msn.wings.impartialreferee

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.StringBuilder

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context,
    DATABASE_NAME,null,
    DATABASE_VERSION
) {
    //static
    companion object{
        //データベースファイル名の定数フィールド
        private const val DATABASE_NAME = "judgment.db"
        //バージョン情報
        private const val DATABASE_VERSION = 1
    }
// ==================================================================================== //
    /**
     * テーブルを作成する
     */
    override fun onCreate(db: SQLiteDatabase?) {
        val sb = StringBuilder()
        sb.append("CREATE TABLE elements(")
        sb.append("_id INTEGER PRIMARY KEY AUTOINCREMENT,")
        sb.append("name TEXT,")
        sb.append("category INTEGER")
        sb.append(")")
        val sql = sb.toString()
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }



}