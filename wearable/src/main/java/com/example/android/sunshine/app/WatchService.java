package com.example.android.sunshine.app;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.DataMap;
import com.google.android.gms.wearable.DataMapItem;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by Admin on 21/09/2016.
 */
// Wearable listener service to provide the data on change
public class WatchService extends WearableListenerService {
    public WatchService() {
    }

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {

        DataMap dataMap;
        for (DataEvent event: dataEvents) {
                String path = event.getDataItem().getUri().getPath();
                if (path.equals("/test")) {
                    dataMap = DataMapItem.fromDataItem(event.getDataItem()).getDataMap();
                    Log.e("Data Item received", dataMap.toString());
                    // Creating intent to send the dataMap via local broadcast manager
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra("test", dataMap.toBundle());

                    // Sending the broadcast
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
                }
        }
    }
}