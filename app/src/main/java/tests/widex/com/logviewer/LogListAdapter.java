package tests.widex.com.logviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;

/**
 * Created by mark on 08-11-2016.
 */

public class LogListAdapter extends ArrayAdapter<LogListAdapter.LogEntry> {

    private static final DateFormat sLocalizedDateFormat = DateFormat.getDateTimeInstance();

    List<LogEntry> mData;
    Context mContext;

    public LogListAdapter(Context context, List<LogEntry> logEntries) {
        super(context, -1); // do not layout now - done when adding items
        mContext = context;
        mData = logEntries;
    }

    @Override
    public void add(LogEntry object) {
        super.add(object);
        mData.add(object);
    }

    @Override
    public void clear() {
        mData.clear();
    }

    @Override
    public LogEntry getItem(int position) {
        return mData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.list_view_layout, parent, false);
        TextView textView = (TextView)view.findViewById(R.id.list_text);
        ImageView imageView = (ImageView)view.findViewById(R.id.list_icon);

        int logValue = getItem(position).category;
        long timestamp = getItem(position).timestamp;

        if (logValue == 1) {
            imageView.setImageResource(R.drawable.emoticon_happy);
        } else if (logValue == 2) {
            imageView.setImageResource(R.drawable.emoticon_neutral);
        } else if (logValue == 3) {
            imageView.setImageResource(R.drawable.emoticon_sad);
        }

        textView.setText(sLocalizedDateFormat.format(timestamp));

        return view;
    }

    public static class LogEntry {
        public long timestamp;
        public int category;
    }
}
