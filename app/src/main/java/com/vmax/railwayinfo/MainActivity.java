package com.vmax.railwayinfo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static String API_KEY = "owgf4qhi81";
    EditText etPNR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etPNR = findViewById(R.id.et_PNR);
        findViewById(R.id.search_icon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pnrNum = etPNR.getText().toString();
                if (!("").equals(pnrNum)){
                    String ROOT_URL = "https://api.railwayapi.com/v2/pnr-status/pnr/"+pnrNum+"/apikey/"+API_KEY;
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
                ArrayList<PNRBean> data = new JSONParser(MainActivity.this, string)
                        .getPNRBeanList();
                findViewById(R.id.text).setVisibility(View.VISIBLE);
                ((TextView)findViewById(R.id.text)).setText("From   :   "+
                        data.get(0).getFromName()+"\n\nBoarding    :   "+
                        data.get(0).getBoardingPoint()+"\n\nTo  :     "+
                        data.get(0).getToName()+"\n\nDate of Journey   :  "+
                        data.get(0).getDoj()+"\n\nTrain Number    :   "+
                        data.get(0).getTrainNum()+"\n\nTotal Passengers    :   "+
                        data.get(0).getTotalPassengers()+"\n\n PNR  :   "+
                        data.get(0).getPnr());
                etPNR.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }

}
