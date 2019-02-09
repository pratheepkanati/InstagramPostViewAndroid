package in.androidhunt.instapost;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Pratheep Chowdhary on 03,February,2019
 */
public class InstaPostView extends LinearLayout {
    public static final String post = "https://api.instagram.com/oembed/?url=http://instagr.am/p/";
    WebView instaView;
    RequestTask task;

    public InstaPostView(Context context) {
        super(context);
    }

    public InstaPostView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InstaPostView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public InstaPostView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public void setPostId(String id) {
        instaView = new WebView(getContext());
        instaView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        WebSettings set = instaView.getSettings();
        set.setUseWideViewPort(true);
        set.setLoadWithOverviewMode(true);
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        set.setPluginState(WebSettings.PluginState.ON);
        set.setPluginState(WebSettings.PluginState.ON_DEMAND);
        set.setAllowContentAccess(true);
        set.setAllowFileAccess(true);
        set.setJavaScriptEnabled(true);
        this.setLayerType(View.LAYER_TYPE_NONE, null);
        this.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.setLongClickable(true);
        instaView.setInitialScale(1);
        instaView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    final Activity activity = (Activity) getContext();
                    if (activity != null)
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (RuntimeException ignored) {
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        if (id != null) {
            task = new RequestTask();
            task.execute(post + id);
        }


        addView(instaView);
    }
    public void setPostContent(String content) {
        instaView = new WebView(getContext());
        instaView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        WebSettings set = instaView.getSettings();
        set.setUseWideViewPort(true);
        set.setLoadWithOverviewMode(true);
        set.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        set.setCacheMode(WebSettings.LOAD_NO_CACHE);
        set.setPluginState(WebSettings.PluginState.ON);
        set.setPluginState(WebSettings.PluginState.ON_DEMAND);
        set.setAllowContentAccess(true);
        set.setAllowFileAccess(true);
        set.setJavaScriptEnabled(true);
        this.setLayerType(View.LAYER_TYPE_NONE, null);
        this.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        this.setLongClickable(true);
        instaView.setInitialScale(1);
        instaView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                try {
                    final Activity activity = (Activity) getContext();
                    if (activity != null)
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                } catch (RuntimeException ignored) {
                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        this.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });
        if (content != null) {
            instaView.loadDataWithBaseURL("https://www.Instagram.com", getInstaHTML(content), "text/html", "utf-8", null);
        }
        addView(instaView);
    }
    private String getInstaHTML(String instaId) {
        try {
            InputStream in = getResources().openRawResource(R.raw.instagram);
            if (in != null) {
                InputStreamReader stream = new InputStreamReader(in, "utf-8");
                BufferedReader buffer = new BufferedReader(stream);
                String read;
                StringBuilder sb = new StringBuilder("");

                while ((read = buffer.readLine()) != null) {
                    sb.append(read + "\n");
                }

                in.close();

                return sb.toString().replace("[_INSTA_ID_]", instaId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void onDestroy() {
        super.onDetachedFromWindow();
        if (instaView != null) {
            instaView.clearCache(true);
            instaView.clearHistory();
        }
        if (task != null && !task.isCancelled())
            task.cancel(true);
    }


    public class RequestTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri) {
            String url = uri[0];
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            return sh.makeServiceCall(url);

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String post = jsonObject.getString("html");
                    if (instaView != null) {
                        instaView.loadDataWithBaseURL("https://www.Instagram.com", getInstaHTML(post), "text/html", "utf-8", null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}