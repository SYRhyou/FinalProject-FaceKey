package com.example.facekey;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class EmployeeAttendanceActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;
    private String ENO ;
    private TextView textEno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_attendance);

        spinner = (Spinner) findViewById(R.id.monthSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        textEno = (TextView)findViewById(R.id.textENO);
        Intent intent = getIntent();
        ENO = intent.getStringExtra("ENO");
        textEno.setText(ENO);
    }
}
