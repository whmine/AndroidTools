package com.actionbarsherlock.whmine.book.app;


import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;
import com.actionbarsherlock.app.BaseApplication;
import com.actionbarsherlock.utils.NetworkUtils;
import com.actionbarsherlock.utils.PreferencesUtils;
import com.actionbarsherlock.whmine.book.R;
import com.actionbarsherlock.whmine.book.cache.ConfigCache;
import com.actionbarsherlock.whmine.book.db.AppSQLiteHelper;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Environment;

public class AppApplication extends BaseApplication {

    public static final String DOMAIN = "domain";
    public static final String DOMAIN_URL = "url";
    public static String mDomain = "http://www.kaiyuanxiangmu.com/";
    public static String mBakeDomain = "http://1.kaiyuanxiangmu.sinaapp.com/";

    private static final String DB_NAME = "book.db";

    public static String mSdcardDataDir;
    public static String mApkDownloadUrl = null;

    @Override
    public void fillTabs() {
    }

    @Override
    public void initDb() {
        mSQLiteHelper = new AppSQLiteHelper(getApplicationContext(), DB_NAME, 1);
    }

    @Override
    public void initEnv() {
        mAppName = "book";
        mDownloadPath = "/book/download";
        if (Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() +  "/book/config/");
            if(!file.exists()) {
                if (file.mkdirs()) {
                    mSdcardDataDir = file.getAbsolutePath();
                }
            } else {
                mSdcardDataDir = file.getAbsolutePath();
            }
        }

        mNetWorkState = NetworkUtils.getNetworkState(this);
        checkDomain(mDomain, false);
//        AppConnect.getInstance(getApplicationContext());
    }

    @Override
    public void exitApp(final Context context) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(this.getString(R.string.app_exit_title))
            .setMessage(this.getString(R.string.app_exit_message))
            .setPositiveButton(this.getString(R.string.app_exit_btu),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
//                        AppConnect.getInstance(context).finalize();
                        android.os.Process.killProcess(android.os.Process.myPid());
                    }
                }
            ).setNegativeButton(this.getString(R.string.app_esc_btu),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }
            );
        alertBuilder.create().show();
    }

    public void checkDomain(final String domain, final boolean stop){
        AppApplication.mDomain = PreferencesUtils.getStringPreference(getApplicationContext(), DOMAIN, DOMAIN_URL, mDomain);
        String cacheConfigString = ConfigCache.getUrlCache(domain + "host.json");
        if (cacheConfigString != null) {
            updateDomain(cacheConfigString);
        } else {/*
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(domain + "host.json", new AsyncHttpResponseHandler(){

                @Override
                public void onStart() {
                }

                @Override
                public void onSuccess(String result) {
                    ConfigCache.setUrlCache(result, domain + "host.json");
                    updateDomain(result);
                }

                @Override
                public void onFailure(Throwable arg0) {
                    if (!stop) {
                        checkDomain(mBakeDomain, true);
                    }
                }

                @Override
                public void onFinish() {
                }
            });
        */}
    }

    public void updateDomain(String result) {
        try {
            JSONObject appreciateConfig = new JSONObject(result);
            String domain = appreciateConfig.optString("domain");
            if (domain != null && !"".equals(domain)) {
                AppApplication.mDomain = domain;
                PreferencesUtils.setStringPreferences(getApplicationContext(), DOMAIN, DOMAIN_URL, domain);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
