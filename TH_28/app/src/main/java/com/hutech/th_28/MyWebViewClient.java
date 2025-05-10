package com.hutech.th_28;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MyWebViewClient extends WebViewClient {

    private EditText addressBar;
    private ProgressBar progressBar; // Thêm thanh tiến trình

    public MyWebViewClient(EditText addressBar) {
        this.addressBar = addressBar;
        this.progressBar = progressBar;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Log.i("WebViewClient", "Navigating to URL: " + url);
        if (addressBar != null) {
            addressBar.setText(url);
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        Log.i("WebViewClient", "Page started loading: " + url);
        if (progressBar != null) {
            progressBar.setVisibility(ProgressBar.VISIBLE); // Hiển thị thanh tiến trình
        }
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        Log.i("WebViewClient", "Page finished loading: " + url);
        if (progressBar != null) {
            progressBar.setVisibility(ProgressBar.GONE); // Ẩn thanh tiến trình
        }
    }

    @Override
    public void onLoadResource(WebView view, String url) {
        super.onLoadResource(view, url);
        Log.i("WebViewClient", "Resource loaded: " + url);
    }
}
