package com.degree53.gohockey;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView versionLabel = TextView.class.cast(findViewById(R.id.version_label));
        versionLabel.setText(getVersion());
    }

    private String getVersion(){
        String versionName = "";
        int versionCode = -1;

        try {
            PackageInfo pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionName = pInfo.versionName;
            versionCode = pInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String version = String.format(getResources().getString(R.string.version_label), versionName, versionCode);
        return version;
    }
}
