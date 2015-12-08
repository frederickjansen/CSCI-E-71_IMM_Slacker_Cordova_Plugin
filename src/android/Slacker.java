package edu.cscie71.imm.slacker.plugin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import edu.cscie71.imm.app.slacker.client.ISlackerClient;
import edu.cscie71.imm.app.slacker.client.SlackerClient;

public class Slacker extends CordovaPlugin {

    private ISlackerClient slackerClient = new SlackerClient();
    private String token = "xoxp-10020492535-10036686290-14227963249-1cb545e1ae";
    private String immTestChannel = "C0F6U0R5E";
    private String authURL = "https://slack.com/oauth/authorize";
    private static final String PREFS = "Slacker";
    private String slackClientID = null;
    private String slackClientSecret = null;

    private WebView inAppWebView;

    @Override
    public void initialize(CordovaInterface cordovaInterface, CordovaWebView cordovaWebView) {
        int slackClient = cordova.getActivity().getResources().getIdentifier("SlackClientID", "string", cordova.getActivity().getPackageName());
        slackClientID = cordova.getActivity().getString(slackClient);
        int slackSecret = cordova.getActivity().getResources().getIdentifier("SlackClientSecret", "string", cordova.getActivity().getPackageName());
        slackClientSecret = cordova.getActivity().getString(slackSecret);
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("postMessage")) {
            String message = args.getString(0);
            this.postMessage(message, callbackContext);
        }
        return false;
    }

    private void postMessage(String message, CallbackContext callbackContext) {
        String response = slackerClient.postMessage(token, immTestChannel, message);
        if (response.contains("\"ok\":true"))
            callbackContext.success(message);
        else
            callbackContext.error("Error posting to the Slack channel.");
    }

    private void openAuthScreen() {
        final CordovaWebView thatWebView = this.webView;
        LinearLayout mainLayout = new LinearLayout(cordova.getActivity());

        Runnable runnable = new Runnable() {
            @SuppressLint("NewApi")
            public void run() {
                inAppWebView = new WebView(cordova.getActivity());
                inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                inAppWebView.setWebChromeClient(new WebChromeClient());
                WebViewClient client = new AuthBrowser();
                inAppWebView.setWebViewClient(client);
                WebSettings settings = inAppWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);

                inAppWebView.loadUrl(authURL);
                inAppWebView.getSettings().setLoadWithOverviewMode(true);
                inAppWebView.getSettings().setUseWideViewPort(true);
                inAppWebView.requestFocus();
                inAppWebView.requestFocusFromTouch();
            }
        };
    }

    private void closeDialog() {
        final WebView childView = this.inAppWebView;
        // The JS protects against multiple calls, so this should happen only when
        // closeDialog() is called by other native code.
        if (childView == null) {
            return;
        }
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                childView.setWebViewClient(new WebViewClient());
                // NB: From SDK 19: "If you call methods on WebView from any thread
                // other than your app's UI thread, it can cause unexpected results."
                // http://developer.android.com/guide/webapps/migrating.html#Threads
                childView.loadUrl("about:blank");
            }
        });
    }

    private void storeOAuthToken(String token) {
        Context context = cordova.getActivity();
    	SharedPreferences.Editor editor = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit();
 		editor.putString("token", token);
 		editor.apply();
    }

    private String getOAuthToken() {
        Context context = cordova.getActivity();
    	SharedPreferences prefs = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        // Return null as default value
    	return prefs.getString("token", null);
    }

    public class AuthBrowser extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

        }
    }
}
