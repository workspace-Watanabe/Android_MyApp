package to.msn.wings.impartialreferee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {

    /**
     * Activityが立ち上がった時
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // -----------------------------------------------------------------------//

        //ViewPagerにアダプタを設定
        val pager : ViewPager = findViewById(R.id.pager)
        pager.adapter = PageAdapter(supportFragmentManager)
    }

    fun onClickSelectButton(view: View){
        val dialogFragment = SelectDialogFragment()
        dialogFragment.show(supportFragmentManager,"aa")
    }
}
