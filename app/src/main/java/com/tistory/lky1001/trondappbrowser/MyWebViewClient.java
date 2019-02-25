package com.tistory.lky1001.trondappbrowser;

import android.graphics.Bitmap;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MyWebViewClient extends WebViewClient {

    // 로딩이 시작될 때
    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    // 리소스를 로드하는 중 여러번 호출
    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
    }

    // 방문 내역을 히스토리에 업데이트 할 때
    @Override
    public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
        super.doUpdateVisitedHistory(view, url, isReload);
    }

    // 로딩이 완료됬을 때 한번 호출
    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
    }

    // 오류가 났을 경우, 오류는 복수할 수 없음
    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        super.onReceivedError(view, errorCode, description, failingUrl);

        switch (errorCode) {
            case ERROR_AUTHENTICATION:
                break;               // 서버에서 사용자 인증 실패
            case ERROR_BAD_URL:
                break;                           // 잘못된 URL
            case ERROR_CONNECT:
                break;                          // 서버로 연결 실패
            case ERROR_FAILED_SSL_HANDSHAKE:
                break;    // SSL handshake 수행 실패
            case ERROR_FILE:
                break;                                  // 일반 파일 오류
            case ERROR_FILE_NOT_FOUND:
                break;               // 파일을 찾을 수 없습니다
            case ERROR_HOST_LOOKUP:
                break;           // 서버 또는 프록시 호스트 이름 조회 실패
            case ERROR_IO:
                break;                              // 서버에서 읽거나 서버로 쓰기 실패
            case ERROR_PROXY_AUTHENTICATION:
                break;   // 프록시에서 사용자 인증 실패
            case ERROR_REDIRECT_LOOP:
                break;               // 너무 많은 리디렉션
            case ERROR_TIMEOUT:
                break;                          // 연결 시간 초과
            case ERROR_TOO_MANY_REQUESTS:
                break;     // 페이지 로드중 너무 많은 요청 발생
            case ERROR_UNKNOWN:
                break;                        // 일반 오류
            case ERROR_UNSUPPORTED_AUTH_SCHEME:
                break; // 지원되지 않는 인증 체계
            case ERROR_UNSUPPORTED_SCHEME:
                break;          // URI가 지원되지 않는 방식
        }

    }

    // http 인증 요청이 있는 경우, 기본 동작은 요청 취소
    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    // 확대나 크기 등의 변화가 있는 경우
    @Override
    public void onScaleChanged(WebView view, float oldScale, float newScale) {
        super.onScaleChanged(view, oldScale, newScale);
    }

    // 잘못된 키 입력이 있는 경우
    @Override
    public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
        return super.shouldOverrideKeyEvent(view, event);
    }

    // 새로운 URL이 webview에 로드되려 할 경우 컨트롤을 대신할 기회를 줌
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}