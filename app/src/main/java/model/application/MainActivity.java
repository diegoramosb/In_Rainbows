package model.application;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.diego.inrainbows.R;

import org.joda.time.JodaTimePermission;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        JodaTimeAndroid.init(this);
    }
}
