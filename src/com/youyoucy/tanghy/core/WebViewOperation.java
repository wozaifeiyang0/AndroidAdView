package com.youyoucy.tanghy.core;

import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewOperation {

    private WebView webView;
    private View pro;
    public WebViewOperation(WebView paramView,View progress){

        this.pro = progress;
        webView = paramView;
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocus();
        webView.setWebViewClient(new MyWebClient());
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

    }

    /**
     * 加载页面
     * @param url
     */
    public void load(String url){
        webView.loadUrl(url);
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (pro !=null)
            pro.setVisibility(View.GONE);
        }
    }

}


