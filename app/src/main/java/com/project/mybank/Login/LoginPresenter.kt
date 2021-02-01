package com.project.mybank

import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginPresenter : LoginContract.Presenter{
    private var LoginView : LoginContract.View?=null
    private var database = FirebaseDatabase.getInstance()
    private var firebaseAuth:FirebaseAuth? = null
    private lateinit var activity:LoginActivity
    private var reuslt:Boolean = false
    override fun setView(view: LoginContract.View,loginactivity: Activity) {
        LoginView = view
        activity=loginactivity as LoginActivity
    }


    override fun accessLogin(id: Editable?, pass: Editable?) {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth!!.signInWithEmailAndPassword(id.toString(),pass.toString()).addOnCompleteListener(activity){
            if(it.isSuccessful){
                Toast.makeText(activity,"로그인 성공",Toast.LENGTH_SHORT).show()

                var intent = Intent(activity,MainActivity::class.java)
                activity.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
                activity.finish()
            }else{
                Toast.makeText(activity,"로그인 실패",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun dropView() {
        LoginView = null
    }



}