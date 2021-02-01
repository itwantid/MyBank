package com.project.mybank

import android.text.Editable

interface LoginContract {
    interface View : BasicVew{
    }
    interface Presenter : BasicContract<View> {
        fun accessLogin(id: Editable?, pass: Editable?)
    }
}