package com.vmax.railwayinfo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RouteInfo extends AppCompatActivity {
    private static String API_KEY = "owgf4qhi81";
    EditText trainNum;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route_info);

        listView = findViewById(R.id.list);

        trainNum = findViewById(R.id.et_PNR);
        findViewById(R.id.search_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trainNumber = trainNum.getText().toString();
                if (!("").equals(trainNumber)){
                    String ROOT_URL = "https://api.railwayapi.com/v2/route/train/"+trainNumber+"/apikey/"+API_KEY;
                    new FetchFromServerTask().execute(ROOT_URL);
                }

            }
        });
    }
    public class FetchFromServerTask extends AsyncTask<String, Void, String> {

        protected void onPreExecute() {
            super.onPreExecute();

        }

        protected String doInBackground(String... params)  {

            String link = null;
            link = params[0];
            try {
                URL url = new URL(link);
                Log.e("URL", link);
                InputStream is = url.openConnection().getInputStream();
                StringBuffer buffer = new StringBuffer();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                return buffer.toString();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String string) {
            Log.e("Data server json", string);


            try {
                ArrayList<RouteBean> data = new JSONParser(RouteInfo.this, string)
                        .getRouteInfo();
                Log.e("Route info size ", String.valueOf(data.size()));
                findViewById(R.id.text).setVisibility(View.VISIBLE);

                ((TextView)findViewById(R.id.text)).setText("Train Name   :   "+
                        data.get(0).getTrainName()+"\n\nTrain Number    :   "+
                        data.get(0).getTrainNum())/*+"\n\nTotal Passengers    :   "+
                        data.get(0).getTotalPassengers()+"\n\n PNR  :   "+
                        data.get(0).getPnr())*/;

                trainNum.setText("");
                listView.setAdapter(new RouteListAdapter(RouteInfo.this, data));
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
