package com.example.marjolein.studentportal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        // init the webview.
        mWebView = (WebView) findViewById(R.id.webView);

        // add the webview.
        mWebView = new WebView(this);
        setContentView(mWebView);
        Portal portal = getIntent().getExtras().getParcelable(MainActivity.PORTAL); // get the data from Portal.
        mWebView.setWebViewClient(new WebViewClient()); // make sure that it stays in the app and doesn't go to a browser.
        mWebView.loadUrl(portal.getmUrl()); // load the URL.
    }
}
