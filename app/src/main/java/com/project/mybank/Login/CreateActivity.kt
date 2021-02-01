package com.project.mybank.Login

import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.mybank.BasicActivity
import com.project.mybank.R

class CreateActivity:BasicActivity(),CreateContract.View {
    private lateinit var createPresenter: CreatePresenter
    private lateinit var activity: CreateActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createlayout)
        initPresenter()

    }
    override fun initPresenter() {
        createPresenter = CreatePresenter()
    }

    override fun showError(error: String) {

    }
}