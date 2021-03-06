package com.bignerdranch.android.osm.presentation.sign_in

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.domain.interactor.RoomService
import javax.inject.Inject

class SignInPresenter : MvpBasePresenter<SignInContract.View>(), SignInContract.Presenter {

    @Inject
    lateinit var roomService: RoomService

    init {
        App.getComponent().inject(this)
    }

}