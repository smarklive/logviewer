package tests.widex.com.logviewer;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class LoggingActivity extends AppCompatActivity implements RandomLogGenerator.LogListener {

    private final String TAG = getClass().getSimpleName();

    private Handler mHandler;
    private RandomLogGenerator mLogGenerator;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        mLogGenerator = new RandomLogGenerator();
        mLogGenerator.run();
        mLogGenerator.setLogListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLogGenerator.removeLogListener();
        mLogGenerator.stop();
    }

    @Override
    public void addLog(final int logValue) {
        //TODO: make log entry appear on a list.

    }



}
