package dev.xesam.android.lifecyclemonitor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView text = (TextView) findViewById(R.id.text);
        Timer timer = new Timer() {
            @Override
            public void onTimerTick(long millisUntilFinished) {
                text.setText(millisUntilFinished + "");
            }
        };
        timer.attach(this);
    }
}
