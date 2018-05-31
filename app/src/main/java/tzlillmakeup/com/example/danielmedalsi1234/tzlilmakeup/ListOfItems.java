package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

public class ListOfItems extends Activity {
    public static final String TAG = "ListOfItems";
    ListView listView;
    ArrayList<String> listData = new ArrayList<>();
    Button BtnAddItem,BtnOkItem;
    EditText txtAddNewItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_of_list);
        listView = findViewById(R.id.listView);
        listData.add("אייליינר");
        listData.add("תוחם");
        listData.add("אודם");
        listData.add("סומק");
        listData.add("עיפרון ריסים");
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listData);
        listView.setAdapter(adapter);

    }



}
