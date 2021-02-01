package com.project.mybank

import android.os.Bundle
import android.util.Log
import android.webkit.*
import android.widget.Toast
import com.project.mybank.Auth.AuthContract
import com.project.mybank.Auth.AuthPresenter

class CenterAuth:BasicActivity(),AuthContract.View {
    private lateinit var authPresenter:AuthPresenter
    private lateinit var webview: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authlayout)
        val activiy = this
        initPresenter()
        authPresenter.setView(this,this)
        webview = findViewById<WebView>(R.id.webview)
        val settings = webview.settings
        settings.javaScriptEnabled=true
        settings.domStorageEnabled = true
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.defaultTextEncodingName = "UTF-8"

        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        webview.setNetworkAvailable(true)
        webview.webChromeClient = WebChromeClient()
        settings.allowUniversalAccessFromFileURLs = true

        webview.webViewClient = object: WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {

                if(url?.startsWith("http://")){
                    val str = url.split("?")
                    val str2 = str[1].split("&")
                    val str3 = str2[0].split("=")
                    val code = str3[1]
                    authPresenter.createToken(code)
                    //view.loadUrl("https://www.google.com")
                }else{
                    Log.d("testurl",url)
                    view.loadUrl(url)
                }
                return true
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                webview.loadUrl("https://testapi.openbanking.or.kr/oauth/2.0/authorize?" +
                        "response_type=code&client_id=195a697e-27ec-4bc2-9586-f3966950e2ae&" +
                        "redirect_uri=http://localhost:5000&" +
                        "scope=login inquiry transfer&" +
                        "state=12345678901234567890123456789012&auth_type=0")
            }
        }
        webview.loadUrl("https://testapi.openbanking.or.kr/oauth/2.0/authorize?" +
                "response_type=code&client_id=195a697e-27ec-4bc2-9586-f3966950e2ae&" +
                "redirect_uri=http://localhost:5000&" +
                "scope=login inquiry transfer&" +
                "state=12345678901234567890123456789012&auth_type=0")

    }

    override fun initPresenter() {
        authPresenter = AuthPresenter()
    }

    override fun showError(error: String) {
        TODO("Not yet implemented")
    }
}
