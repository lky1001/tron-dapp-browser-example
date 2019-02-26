package com.tistory.lky1001.trondappbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webView;

    private final Handler handler = new Handler();

    private CustomPreference customPreference;
    private KeyStore keyStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        if (TextUtils.isEmpty(customPreference.getPk())) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        customPreference = new CustomPreference(this);
        keyStore = new KeyStoreApi23Impl(customPreference);

        keyStore.init();
        keyStore.createKeys(CustomPreference.ALIAS_ADDRESS_KEY);

        // webview settings
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.setNetworkAvailable(true);
        webView.getSettings().setJavaScriptEnabled(true);

        //// Sets whether the DOM storage API is enabled.
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("http://localhost:3000");

        // Bridge instance
        webView.addJavascriptInterface(this, "tronWeb");
    }

    @JavascriptInterface
    public String requestData() { // must be final
        return keyStore.encryptString(customPreference.getPk(), CustomPreference.ALIAS_ADDRESS_KEY);

//        handler.post(new Runnable() {
//            public void run() {
//                String test  = "test1234";
//                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
//                    webView.evaluateJavascript("javascript:getAndroidData('" + test + "')", null);
//                } else {
//                    webView.loadUrl("javascript:getAndroidData('"+test+"')");
//                }
//            }
//        });
    }
}
