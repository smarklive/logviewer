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
    private LogListAdapter mListAdapter;
    private StateFragment mState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: handle screen rotation so that the list is not cleared!
        mState = (StateFragment)getSupportFragmentManager().findFragmentByTag("state");
        if (mState == null) {
            mState = new StateFragment();
            mState.logListAdapter = new LogListAdapter(this.getBaseContext(), new ArrayList<LogListAdapter.LogEntry>());
            getSupportFragmentManager().beginTransaction().add(mState,"state").commit();
        }
        initViews();


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
        //TODO: make log entry appear on the list.
        Log.i(TAG, "logValue = "+logValue);
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                LogListAdapter.LogEntry logEntry = new LogListAdapter.LogEntry();
                logEntry.category = logValue;
                logEntry.timestamp = new Date().getTime();
                mListAdapter.add(logEntry);
            }
        });
    }

    private void initViews() {
        mListView = (ListView)findViewById(R.id.listview);
        mListAdapter = mState.logListAdapter;
        mListView.setAdapter(mListAdapter);

        //TODO: when user clicks on a log item then launch LogDetailsActivity to view log details.
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent logDetailsIntent = new Intent(LoggingActivity.this, LogDetailsActivity.class);
                logDetailsIntent.putExtra(LogDetailsActivity.CATEGORY_EXTRA, mListAdapter.getItem(position).category);
                logDetailsIntent.putExtra(LogDetailsActivity.TIMESTAMP_EXTRA, mListAdapter.getItem(position).timestamp);
                startActivity(logDetailsIntent);
            }
        });
    }

    public static class StateFragment extends Fragment {
        public LogListAdapter logListAdapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);
        }
    }
}
