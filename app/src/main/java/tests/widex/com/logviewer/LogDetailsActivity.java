package tests.widex.com.logviewer;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;

public class LogDetailsActivity extends AppCompatActivity {

    public static final String CATEGORY_EXTRA = "category_extra";

    public static final String TIMESTAMP_EXTRA = "timestamp_extra";

    private static final DateFormat sLocalizedDateFormat = DateFormat.getDateTimeInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_details);

        //TODO: read and display log category and timestamp:
        int category = getIntent().getIntExtra(CATEGORY_EXTRA, 1);
        long timestamp = getIntent().getLongExtra(TIMESTAMP_EXTRA, new Date().getTime());

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        TextView textView = (TextView)findViewById(R.id.textView);

        switch (category) {
            case RandomLogGenerator.HAPPY:
                imageView.setImageResource(R.drawable.emoticon_happy);
                break;
            case RandomLogGenerator.NEUTRAL:
                imageView.setImageResource(R.drawable.emoticon_neutral);
                break;
            case RandomLogGenerator.SAD:
                imageView.setImageResource(R.drawable.emoticon_sad);
                break;
        }

        textView.setText(sLocalizedDateFormat.format(timestamp));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
