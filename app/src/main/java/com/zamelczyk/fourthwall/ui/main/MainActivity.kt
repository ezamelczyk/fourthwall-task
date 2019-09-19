package com.zamelczyk.fourthwall.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zamelczyk.fourthwall.R
import com.zamelczyk.fourthwall.ui.picslist.PicsListFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PicsListFragment.newInstance())
                .commitNow()
        }
    }

}
