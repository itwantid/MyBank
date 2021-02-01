package com.project.mybank.Login

import android.app.Activity

class CreatePresenter:CreateContract.Presenter {
    private var createView:CreateContract.View? = null
    private lateinit var activity:CreateActivity
    override fun setView(view: CreateContract.View, createactivity: Activity) {
        createView = view
        activity = createactivity as CreateActivity
    }
    override fun dropView() {
        createView = null
    }
}