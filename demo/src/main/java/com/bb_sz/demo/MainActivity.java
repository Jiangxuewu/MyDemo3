package com.bb_sz.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bb_sz.view.FPStartView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {

    private FPStartView fiveStarView;
    private double arg = 0;
    private double xMultiple = 0.0;
    private double yMultiple = 0.0;
    private Button argBtn;
    private Button xmBtn;
    private Button ymBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fiveStarView = (FPStartView) findViewById(R.id.five_star_view);
        fiveStarView.setOnClickListener(this);
        fiveStarView.setOnLongClickListener(this);

        argBtn = (Button) findViewById(R.id.ARG);
        xmBtn = (Button) findViewById(R.id.XM);
        ymBtn = (Button) findViewById(R.id.YM);
    }


    public void onClickAddArg(View v) {
        arg += 45.0;
    }

    public void onClickAddXM(View v) {
        xMultiple += 0.1;
    }

    public void onClickAddYM(View v) {
        yMultiple += 0.1;
    }


    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.five_star_view:
                fiveStarView.start(arg, xMultiple, yMultiple);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.five_star_view:
                fiveStarView.stop();
                break;
        }
    }
}
