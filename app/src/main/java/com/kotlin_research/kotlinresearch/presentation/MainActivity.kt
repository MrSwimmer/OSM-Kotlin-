package com.kotlin_research.kotlinresearch.presentation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.widget.FrameLayout
import butterknife.BindView
import butterknife.ButterKnife
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.kotlin_research.kotlinresearch.R
import com.kotlin_research.kotlinresearch.presentation.settings.NotesController
import com.kotlin_research.kotlinresearch.presentation.settings.SettingsController
import com.kotlin_research.kotlinresearch.presentation.statistic.StatisticController

class MainActivity : AppCompatActivity() {

    lateinit var router: Router

    @BindView(R.id.main_container)
    lateinit var mainFrameLayout: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
        router = Conductor.attachRouter(this, mainFrameLayout, savedInstanceState)
        if (!router.hasRootController())
            router.setRoot(RouterTransaction.with(NotesController()))

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.main_bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_action_notes -> {
                router.replaceTopController(RouterTransaction.with(NotesController()))
            }
            R.id.navigation_action_statistic -> {
                router.replaceTopController(RouterTransaction.with(StatisticController()))
            }
            R.id.navigation_action_settings -> {
                router.replaceTopController(RouterTransaction.with(SettingsController()))
            }
        }
        true
    }

    override fun onBackPressed() {
        if (!router.handleBack())
            super.onBackPressed()
    }
}
