package com.project.mybank

import android.app.Activity

interface BasicContract<T> {
        fun setView(view : T,activity: Activity)
        fun dropView()
}