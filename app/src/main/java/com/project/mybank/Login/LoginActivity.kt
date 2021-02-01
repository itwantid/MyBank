package com.project.mybank

import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.webkit.*
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.project.mybank.Login.CreateActivity

class LoginActivity:BasicActivity(),LoginContract.View {
    var id = null
    private lateinit var loginPresenter:LoginPresenter
    private lateinit var text_id:EditText
    private lateinit var text_pass:EditText
    private lateinit var activity:LoginActivity
    override fun onCreate(savedInstanceState: Bundle?) { 
        super.onCreate(savedInstanceState)
        activity = this
        setContentView(R.layout.activity_main)
        var layout = findViewById<ConstraintLayout>(R.id.layout)
        text_id = findViewById<EditText>(R.id.textView1)
        text_pass = findViewById<EditText>(R.id.textView2)
        var login = findViewById<TextView>(R.id.login)
        var create = findViewById<TextView>(R.id.create)
        initPresenter()
        loginPresenter.setView(this,this)

        layout.setOnClickListener({
            CloseKeyboard()
        })
        create.setOnClickListener({
            OpenCreate()
        })
        login.setOnClickListener({
            try{
                val id:Editable? = text_id.text
                val pass:Editable? = text_pass?.text
                if(id.isNullOrBlank()||pass.isNullOrBlank()){
                    throw Exception()
                }
                CloseKeyboard()
                loginPresenter.accessLogin(id,pass)

            }catch(e:Exception){
                Toast.makeText(this@LoginActivity, "아이디와 패스워드를 입력해주세요", Toast.LENGTH_SHORT).show()
                CloseKeyboard()
            }
        })


    }
    fun OpenCreate(){
        var intent = Intent(activity,CenterAuth::class.java)
        activity.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
        activity.finish()
    }
    fun CloseKeyboard(){
        var view = this.currentFocus
        text_id.clearFocus()
        text_pass.clearFocus()
        if(view != null)
        {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun initPresenter(){
        loginPresenter = LoginPresenter()
    }


    override fun showError(error: String) {
        TODO("Not yet implemented")
    }
}