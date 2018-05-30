package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MessagesAdapter extends ArrayAdapter<String> {

    private List<String> messages;
    private Activity activity;

    public MessagesAdapter(@NonNull Activity activity, List<String> messages) {
        super(activity, R.layout.row_message, messages);
        this.messages = messages;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.row_message, parent, false);
        TextView lblMessage = view.findViewById(R.id.lblMessage);
        lblMessage.setText(messages.get(position));
        return view;
    }
}