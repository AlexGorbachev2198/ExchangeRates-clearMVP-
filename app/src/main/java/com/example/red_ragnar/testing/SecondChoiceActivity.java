package com.example.red_ragnar.testing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bpc.modulesdk.rest.dto.pojo.RateInformation;
import com.example.red_ragnar.testing.R;

import java.util.List;
import java.util.Map;

public class SecondChoiceActivity extends AppCompatActivity {
    TextView _text;
    Presenter _prsnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_choice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.hide();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //_prsnt = new Presenter();
        _text =(TextView) findViewById(R.id.textView2);
        _text.setText("");
       if(_prsnt.GetSuccess()) {
           List<RateInformation> rates = _prsnt.GetAll_Data();
           for (int i = 0; i < rates.size(); i++) {
               _text.append(rates.get(i).getName() + " " + rates.get(i).getBase() + " " + rates.get(i).getBuy() + " " + rates.get(i).getSell() + "\n");
           }
       }
       else _text.setText("Error: Connection failure");
    }

}
