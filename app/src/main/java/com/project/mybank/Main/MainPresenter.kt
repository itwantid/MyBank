package com.project.mybank

import android.app.Activity

class MainPresenter :MainContract.Presenter{
    private var mainView:MainContract.View?= null
    private lateinit var activity:MainActivity
    override fun setView(view: MainContract.View, mainactivity: Activity) {
        mainView = view
        activity = mainactivity as MainActivity
    }

    override fun dropView() {
        mainView = null
    }
}