package com.bignerdranch.android.osm.presentation.add_note

import android.util.Log
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.bignerdranch.android.osm.App
import com.bignerdranch.android.osm.data.model.Points
import com.bignerdranch.android.osm.data.room.Note
import com.bignerdranch.android.osm.domain.interactor.ApiServiceGraph
import com.bignerdranch.android.osm.domain.interactor.RoomService
import javax.inject.Inject

class AddNotePresenter : MvpBasePresenter<AddNoteContract.View>(), AddNoteContract.Presenter {
    @Inject
    lateinit var db: RoomService

    init {
        App.getComponent().inject(this)
    }

    @Inject
    lateinit var apiService: ApiServiceGraph

    override fun getResult(pulseSitting: String, pulseStanding: String) {
        val x = 2.27
        val y = 0.5
        var points = 14.5 - y * (pulseSitting.toFloat() - 40) / 3.5 - (pulseStanding.toFloat() - pulseSitting.toFloat()) / x * 0.5
        points *= 100
        var i = Math.round(points).toInt()
        points = i.toDouble() / 100
        val zone = getZone(points)
        view.setRes(points, zone)
        apiService.setPoints(Points(points.toFloat()), object : ApiServiceGraph.PointsCallback {
            override fun onSuccess(it: Float) {
                Log.i("code", "set float points $it")
            }

            override fun onError(it: Throwable) {
                Log.i("code", "set float points error ${it.message}")
            }

        })
        apiService.getPoints(object : ApiServiceGraph.PointsCallback {
            override fun onSuccess(it: Float) {
                Log.i("code", "get float points $it")
            }

            override fun onError(it: Throwable) {
                Log.i("code", "get float points error ${it.message}")
            }

        })
    }

    private fun getZone(points: Double): Int {
        var n = 0
        if (points >= 7.5)
            n = 1
        if (points >= 5 && points < 7.5)
            n = 2
        if (points >= 2.5 && points < 5)
            n = 3
        if (points < 2.5)
            n = 4
        return n
    }

    override fun addNote(note: Note) {
        db.addNote(note, object : RoomService.EditNoteCallback {
            override fun onSuccess() {
                view.gotoResult(note)
            }

            override fun onError(e: Throwable) {
                Log.i("code", e.message)
            }

        })
    }
}