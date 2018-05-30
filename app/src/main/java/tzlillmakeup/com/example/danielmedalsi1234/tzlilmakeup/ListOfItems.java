package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

public class ListOfItems extends Activity {
    public static final String TAG = "ListOfItems";

    //AppDataBaseContact db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_of_list);
        listView = findViewById(R.id.listView);

        /*db = Room.databaseBuilder(listDB.this, AppDataBaseContact.class, "contact")
                .allowMainThreadQueries()
                .build();*/

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG,"populateListView: Displaying data in the list view");

        //get data and append yo list

        ArrayList<String> listData = new ArrayList<>();
        listData.add("תוחם");
        listData.add("אייליינר");
        listData.add("אודם סגול");
        listData.add("סומק");


        //adapter and set adapter
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);
    }
}
