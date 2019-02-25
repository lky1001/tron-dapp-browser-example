package com.tistory.lky1001.trondappbrowser;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);

        // 웹뷰 셋팅팅
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        webView.setNetworkAvailable(true);
        webView.getSettings().setJavaScriptEnabled(true);

        //// Sets whether the DOM storage API is enabled.
        webView.getSettings().setDomStorageEnabled(true);

        webView.loadUrl("http://172.26.108.152:3000");

        // Bridge 인스턴스 등록
        webView.addJavascriptInterface(this, "DiceWeb");
    }

    @JavascriptInterface
    public String requestData() { // must be final
        return "test1234";
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
