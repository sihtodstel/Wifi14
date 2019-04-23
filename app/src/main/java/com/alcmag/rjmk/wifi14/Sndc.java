package com.alcmag.rjmk.wifi14;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import java.util.List;

public class Sndc extends Activity {
    private WifiManager wifiManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sndc);
        String networkSSID = "test";
        String networkPass = "pass";

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";   // Please note the quotes. String should contain ssid in quotes
        public void connectToWifi(){
            try{
                WifiManager wifiManager = (WifiManager) super.getApplicationContext().getSystemService(android.content.Context.WIFI_SERVICE);
                WifiConfiguration wc = new WifiConfiguration();
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                wc.SSID = "\"NETWORK_NAME\"";
                wc.preSharedKey = "\"PASSWORD\"";
                wc.status = WifiConfiguration.Status.ENABLED;
                wc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

                wifiManager.setWifiEnabled(true);
                int netId = wifiManager.addNetwork(wc);
                if (netId == -1) {
                    netId = getExistingNetworkId(wc.SSID);
                }
                wifiManager.disconnect();
                wifiManager.enableNetwork(netId, true);
                wifiManager.reconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        private int getExistingNetworkId(String SSID) {
            WifiManager wifiManager = (WifiManager) super.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            List<WifiConfiguration> configuredNetworks = wifiManager.getConfiguredNetworks();
            if (configuredNetworks != null) {
                for (WifiConfiguration existingConfig : configuredNetworks) {
                    if (existingConfig.SSID.equals(SSID)) {
                        return existingConfig.networkId;
                    }
                }
            }
            return -1;
        }
        WifiConfiguration wfc = new WifiConfiguration();
        //for open wifi
        wfc.SSID = "\"".concat(ssid).concat("\"");
        wfc.status = WifiConfiguration.Status.DISABLED;
        wfc.priority = 40;
        wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wfc.allowedAuthAlgorithms.clear();
        wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        //WEP
        wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
        wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
        wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);

        if (isHexString(password)) wfc.wepKeys[0] = password;
        else wfc.wepKeys[0] = "\"".concat(password).concat("\"");
        wfc.wepTxKeyIndex = 0;
        //WPA and WPA2
        wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
        wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
        wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);

        wfc.preSharedKey = "\"".concat(password).concat("\"");
        //others
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
        wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
        conf.wepKeys[0] = "\"" + networkPass + "\"";
        conf.wepTxKeyIndex = 0;
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        conf.preSharedKey = "\""+ networkPass +"\"";
        WifiManager wfMgr = (WifiManager) super.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int networkId = wfMgr.addNetwork(wfc);
        if (networkId != -1) {
            // success, can call wfMgr.enableNetwork(networkId, true) to connect
        }
        WifiManager wifiManager = (WifiManager)super.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiManager.addNetwork(conf);
        List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
        for( WifiConfiguration i : list ) {
            if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                wifiManager.disconnect();
                wifiManager.enableNetwork(i.networkId, true);
                wifiManager.reconnect();

                break;
            }
        }
    }
}