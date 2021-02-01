package com.project.mybank

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract  class BasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        initPresenter()
    }
    abstract fun initPresenter()
}