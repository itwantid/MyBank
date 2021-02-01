package com.project.mybank.Auth

import android.content.Context
import android.text.Editable
import com.project.mybank.BasicContract
import com.project.mybank.BasicVew

interface AuthContract {
    interface View : BasicVew {
    }
    interface Presenter : BasicContract<View> {
        fun createToken(code:String)
        fun accessToken(context:Context, url:String, code:String)
        fun showJSONList(response:String)
    }
}