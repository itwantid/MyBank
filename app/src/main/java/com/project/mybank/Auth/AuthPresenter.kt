package com.project.mybank.Auth

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.android.volley.Response
import com.project.mybank.CenterAuth
import org.json.JSONException
import org.json.JSONObject

class AuthPresenter:AuthContract.Presenter {
    private var AuthView: AuthContract.View? = null
    private lateinit var activity: CenterAuth
    override fun setView(view: AuthContract.View, centerauth: Activity) {
        AuthView = view
        activity = centerauth as CenterAuth
    }

    override fun dropView() {
        AuthView = null
    }

    override fun createToken(code: String) {
        accessToken(activity,"https://testapi.openbanking.or.kr/oauth/2.0/token",code)
    }

    override fun accessToken(context: Context, url: String, code: String) {
        val requestQueue = Volley.newRequestQueue(context)
        val request:StringRequest = object:StringRequest(Method.POST,url,
        Response.Listener{ response->showJSONList(response)

        },
        Response.ErrorListener { error->
            Toast.makeText(activity,error.toString(),Toast.LENGTH_LONG).show()
        }){
            override fun getParams(): MutableMap<String, String> {
                val params:MutableMap<String,String> = HashMap()
                params["code"] = code
                params["client_id"] = "195a697e-27ec-4bc2-9586-f3966950e2ae"
                params["client_secret"] = "4d2ed4e2-9893-44b1-b538-155bc946c9be"
                params["redirect_uri"] = "http://localhost:5000"
                params["grant_type"] = "authorization_code"
                return params
            }
        }
        requestQueue.add(request)
    }

    override fun showJSONList(response: String) {
        try{
            val jsonObject = JSONObject(response)
            jsonObject.let{
                Toast.makeText(activity,it.getString("access_token").toString(),Toast.LENGTH_LONG).show()
            }
        }catch (e:JSONException){
            e.printStackTrace()
        }
    }
}
