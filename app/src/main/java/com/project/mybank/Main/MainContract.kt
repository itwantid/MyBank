package com.project.mybank

interface MainContract {
    interface View:BasicVew{
    }
    interface Presenter:BasicContract<View>{

    }
}