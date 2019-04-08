package com.example.facekey;

import android.support.v7.app.AppCompatActivity;

public class M2List extends AppCompatActivity {

    String enter_timeText;
    String exit_timeText;
    String lateText;

    public String getEnter_timeText() { return enter_timeText; }
    public void setEnter_timeText(String enter_timeText) { this.enter_timeText = enter_timeText; }

    public String getExit_timeText() { return exit_timeText; }
    public void setExit_timeText(String exit_timeText) { this.exit_timeText = exit_timeText; }

    public String getLateText() { return lateText; }
    public void setLateText(String lateText) { this.lateText = lateText; }

    public M2List(String dayText, String enter_timeText, String exit_timeText, String lateText) {
        this.enter_timeText = enter_timeText;
        this.exit_timeText = exit_timeText;
        this.lateText = lateText;
    }


}
