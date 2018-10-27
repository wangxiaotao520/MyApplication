package com.myapplication.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.myapplication.R;
import com.myapplication.widget.Charts;

import lecho.lib.hellocharts.view.LineChartView;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        final Charts charts = new Charts((LineChartView) findViewById(R.id.chart));
        final Charts.LINE lineBlue = charts.new LINE("#00ff00");
        final Charts.LINE lineRed = charts.new LINE("#ff0000");

        Button bt1 = (Button) findViewById(R.id.button1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0; i<300; i++) {
                    lineBlue.add(i, i);
                    lineRed.add(i, 300-i);
                    charts.flush();
                }

            }
        });

        Button bt2 = (Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineBlue.clean();
                lineRed.clean();
                charts.flush();
            }
        });

        findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (findViewById(R.id.iv_select).getVisibility()!=View.GONE){
                    findViewById(R.id.iv_select).setVisibility(View.GONE);
                }else {
                    findViewById(R.id.iv_select).setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
