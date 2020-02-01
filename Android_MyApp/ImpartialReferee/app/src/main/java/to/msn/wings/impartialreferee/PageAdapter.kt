package to.msn.wings.impartialreferee

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * リストを表示する為のアダプタークラス
 */
class PageAdapter(fm:FragmentManager) : FragmentPagerAdapter(fm){
    //ページ数
    private val PAGE_COUNT = 1
    //ページのタイトル
    private var titles:Array<String> = arrayOf("ページ1")

    //================================================================================//

    /**
     * インデックス番号に応じてページを取得
     */
    override fun getItem(position: Int): Fragment {
        return ListFragment1()
    }



    /**
     * ページ数を返す
     */
    override fun getCount(): Int {
       return PAGE_COUNT
    }
}