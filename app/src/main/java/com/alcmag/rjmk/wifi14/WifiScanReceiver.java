package com.alcmag.rjmk.wifi14;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

class WifiScanReceiver extends BroadcastReceiver {
    WifiManager mainWifiObj;
    String[] wifis;
    ListView list;


    public void onReceive(Context c, Intent intent) {
        java.util.List<ScanResult> wifiScanList = mainWifiObj.getScanResults();
        wifis = new String[wifiScanList.size()];
        for (int i = 0; i < wifiScanList.size(); i++) {
            wifis[i] = ((wifiScanList.get(i)).toString());

        }
        String[] filtered = new String[wifiScanList.size()];
        int counter = 0;
        for (String eachWifi : wifis) {
            String[] temp = eachWifi.split(",");

            filtered[counter] = temp[0].substring(5).trim() + "\n" + temp[2].substring(12).trim() + "\n" + temp[3].substring(6).trim();//0->SSID, 2->Key Management 3-> Strength

            counter++;

        }
        list.setAdapter(new ArrayAdapter<String>(c.getApplicationContext(), R.layout.activity_list, R.id.label, filtered));


    }

}