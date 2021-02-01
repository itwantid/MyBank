package com.project.mybank

import android.os.Bundle

class MainActivity:BasicActivity() {
    private lateinit var loginPresenter: LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainlayout)
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentlayout,HomeFragment())
                .commit()
    }
    override fun initPresenter() {
        loginPresenter=LoginPresenter()
    }
}