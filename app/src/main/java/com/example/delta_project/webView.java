package com.example.delta_project;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import static com.example.delta_project.homeFragment.Finalname;
import static com.example.delta_project.homeFragment.Finalurl;
import static com.example.delta_project.finalDisplay.Value;

public class webView extends AppCompatActivity {
private String name;
private String url;
private String which;
private WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Intent i=getIntent();
        name=i.getStringExtra(Finalname);
        url=i.getStringExtra(Finalurl);
        which=i.getStringExtra(Value);
        WebView webview=findViewById(R.id.webview);
        webview.setWebViewClient(new WebViewClient());
        if(which.equals("chrome")){
        webview.loadUrl("https://www.google.com/search?q=" + name);
        }
        else if(which.equals("location")){
//            webview.loadUrl("http://maps.google.co.in/maps?q=" + name);
            Uri uri=Uri.parse("geo:0,0?q="+Uri.encode(name));
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
            mapIntent.setPackage("com.google.android.apps.maps");
            if(mapIntent.resolveActivity(getPackageManager())!=null){

            startActivity(mapIntent);
            }
        }
        else if(which.equals("directions")){
            Uri uri=Uri.parse("google.navigation:q="+Uri.encode(name));
            Intent intent =new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivity(intent);
            }

        }
        else{
            webview.loadUrl("https://en.m.wikipedia.org/wiki/"+ name);
        }
    }

    @Override
    public void onBackPressed() {
        if(webview.canGoBack()){
            webview.goBack();
        }else
        super.onBackPressed();
    }
}
