/**
* Copyright 2016 IBM Corp.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.sample.resourcerequestandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import com.worklight.wlclient.api.*;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView resource = null;
    private TextView summary = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        resource = (TextView) findViewById(R.id.resource);

        summary = (TextView) findViewById(R.id.summary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button sendButton = (Button) findViewById(R.id.button_send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    URI adapterPath = new URI("/adapters/testadapter/resource/" + resource.getText());

                    WLResourceRequest request = new WLResourceRequest(adapterPath, WLResourceRequest.GET);
                    // Query Parameters
                    request.addHeader("Content-Type", "text/plain");
                    //request.addHeader("Accept","application/json");

                    // Form Parameters
                    JSONObject body = new JSONObject();


                    // Send
                    //request.send(new WLResponseListener() {
                    request.send(new WLResponseListener() {
                        public void onSuccess(WLResponse response) {
                            String resultText = response.getResponseText();
                            updateTextView(resultText);
                        }

                        public void onFailure(WLFailResponse response) {
                            //String responseText = response.getResponseText();
                            String errorMsg = response.getErrorMsg();
                            Log.d("InvokeFail", errorMsg);
                            updateTextView("Failed to Invoke Adapter Procedure\n" + errorMsg);
                        }
                    });

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateTextView(final String str){
        Runnable run = new Runnable() {
            public void run() {
                summary.setText(str);
            }
        };
        this.runOnUiThread(run);
    }
}
