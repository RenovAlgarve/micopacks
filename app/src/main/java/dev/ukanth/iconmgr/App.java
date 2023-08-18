package dev.ukanth.iconmgr;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import dev.ukanth.iconmgr.dao.HistoryDao;
import dev.ukanth.iconmgr.dao.HistoryDatabase;
import dev.ukanth.iconmgr.dao.IPObjDao;
import dev.ukanth.iconmgr.dao.IPObjDatabase;

/**
 * Created by ukanth on 13/8/17.
 */

public class App extends Application {

    private static App instance;

    private BroadcastReceiver receiver;


    public static App getInstance() {
        return instance;
    }

    public static Context getContext() {
        return instance;
    }


    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addDataScheme("package");
        receiver = new InstallReceiver();
        registerReceiver(receiver, intentFilter);
    }

    public IPObjDao getIPObjDao() {
        IPObjDatabase db = IPObjDatabase.getInstance(getApplicationContext());
        IPObjDao ipObjDao = db.ipObjDao();
        return ipObjDao;
    }

    public HistoryDao getHistoryDao() {
        HistoryDatabase db2 = HistoryDatabase.getInstance(getApplicationContext());
        HistoryDao historyDao = db2.historyDao();
        return historyDao;
    }

}