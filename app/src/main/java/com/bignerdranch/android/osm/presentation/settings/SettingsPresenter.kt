package com.bignerdranch.android.osm.presentation.settings

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.domain.interactor.RoomService
import javax.inject.Inject

class SettingsPresenter : MvpBasePresenter<SettingsContract.View>(), SettingsContract.Presenter {

    @Inject
    lateinit var roomService: RoomService

    init {
        App.getComponent().inject(this)
    }

    override fun deleteAll() {
        roomService.deleteAll(object : RoomService.EditNoteCallback {
            override fun onSuccess() {
                Log.i("code", "success delete all")
            }

            override fun onError(e: Throwable) {
                Log.i("code", "error delete all ${e.message}")
            }
        })
    }
}