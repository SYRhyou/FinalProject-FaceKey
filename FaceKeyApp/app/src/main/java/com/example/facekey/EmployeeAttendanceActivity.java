package com.example.facekey;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EmployeeAttendanceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String ENO;
    private ArrayAdapter adapter;
    private Spinner spinner;
    private String text;
    private String phpAdress;

    private Button inquiryButton;
    private TextView textView2;

    private ListView monthListView;

    private M1ListAdapter m1ListAdapter;
    private List<M1List> m1List;

    private M2ListAdapter m2ListAdapter;
    private List<M2List> m2List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_attendance);

        textView2 = (TextView)findViewById(R.id.textView2);
        Intent intent = getIntent();

        ENO = intent.getStringExtra("ENO");
        textView2.setText(ENO);

        //스피너
        spinner = (Spinner) findViewById(R.id.monthSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // 조회버튼
        inquiryButton = (Button)findViewById(R.id.inquiryButton);
        inquiryButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (text.equals("1월")) {
                    monthListView = (ListView)findViewById(R.id.monthListView);
                    m1List = new ArrayList<M1List>();
                    m1ListAdapter = new M1ListAdapter(getApplicationContext(), m1List);
                    monthListView.setAdapter(m1ListAdapter);
                    phpAdress = "http://203.244.145.238/M1.php";
                    new BackgroundTask().execute();
                }

                else if(text.equals("2월")) {
                    monthListView = (ListView)findViewById(R.id.monthListView);
                    m2List = new ArrayList<M2List>();
                    m2List.add(new M2List("김혜진","2월","2월","-"));

                    m2ListAdapter = new M2ListAdapter(getApplicationContext(), m2List);
                    monthListView.setAdapter(m2ListAdapter);

                }
            }
        });
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>
    {
        String target;

        @Override
        protected void onPreExecute(){
            target = phpAdress;
        }

        @Override
        public void onProgressUpdate(Void... values){
            super.onProgressUpdate();
        }
        @Override
        public void onPostExecute(String result){
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String ENTER_TIME, EXIT_TIME, ATTENDANCE;

                while (count < jsonArray.length()) {
                    JSONObject object = jsonArray.getJSONObject(count);

                    if(object.getString("ENO").equals(ENO)) {
                        ENTER_TIME = object.getString("ENTER_TIME");
                        EXIT_TIME = object.getString("EXIT_TIME");
                        ATTENDANCE = object.getString("ATTENDANCE");

                        M1List M1List = new M1List(ENTER_TIME, EXIT_TIME, ATTENDANCE);
                        m1List.add(M1List);
                        m1ListAdapter.notifyDataSetChanged();
                        count++;
                    }

                    else{
                        count++;
                    }
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        protected String doInBackground(Void... voids) {

            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }


    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}


