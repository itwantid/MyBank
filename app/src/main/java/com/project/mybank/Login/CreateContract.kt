package com.project.mybank.Login

import android.text.Editable
import com.project.mybank.BasicContract
import com.project.mybank.BasicVew

interface CreateContract {
    interface View : BasicVew {
    }
    interface Presenter : BasicContract<View> {

    }
}