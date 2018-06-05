package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

public class ListOfItems extends Activity {
    public static final String TAG = "ListOfItems";
    private ListView listView;
    private List<String> listItem;
    private Button BtnAddItem,BtnOkItem,BtnDelete;
    private EditText txtAddNewItem;
    private ArrayAdapter adapter;
    private EditText input1;
    private SharedPreferences.Editor editor;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_of_list);
        listView = findViewById(R.id.listView);
        BtnAddItem = findViewById(R.id.BtnAddNewItem);
        BtnOkItem = findViewById(R.id.BtnOkAddItem);
        txtAddNewItem = findViewById(R.id.txtAddNewItem);
        BtnDelete = findViewById(R.id.BtnDelete);
        listItem = new ArrayList<>();
        adapter  = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);

        LoadList();
        AddItem();
        OkItem();
        DeleteAllList();
    }

    public void LoadList(){
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        String items = preferences.getString("item","");
        final String[] itemsArray = items.split(",");
        List<String> ItemList = new ArrayList<>();
        for (int i=0;i<itemsArray.length;i++){
            listItem.add(itemsArray[i]);
        }
        for (int i=0;i<ItemList.size();i++){
            Log.d("listItem",ItemList.get(i));
        }
    }
    public void OkItem(){
        BtnOkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = txtAddNewItem.getText().toString();
                listItem.add(input.toString());
                Toast.makeText(ListOfItems.this,"פריט התווסף!!",
                        Toast.LENGTH_SHORT).show();
                txtAddNewItem.setVisibility(View.INVISIBLE);
                BtnOkItem.setVisibility(View.INVISIBLE);
                BtnDelete.setVisibility(View.INVISIBLE);
                txtAddNewItem.setText("");
                refreshAdapterThread();


                StringBuilder stringBuilder = new StringBuilder();
                for(String item : listItem){
                    stringBuilder.append(item);
                    stringBuilder.append(",");
                }
                preferences = getSharedPreferences("PREFS",0);
                editor = preferences.edit();
                editor.putString("item",stringBuilder.toString());
                editor.commit();
            }
        });
    }
    public void AddItem(){
        BtnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(ListOfItems.this);
                alert.setTitle("מנהל הוספת פריט");
                alert.setMessage("הכנס סיסמת מנהל");
                input1 = new EditText(ListOfItems.this);
                alert.setView(input1);

                alert.setPositiveButton("אישור", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Editable value1 = input1.getText();
                        if (input1.getText().toString().equals("1")) {
                            txtAddNewItem.setVisibility(View.VISIBLE);
                            BtnOkItem.setVisibility(View.VISIBLE);
                            BtnDelete.setVisibility(View.VISIBLE);

                        }
                        else
                            Toast.makeText(ListOfItems.this,"סיסמה שגויה",
                                    Toast.LENGTH_SHORT).show();
                    }

                });
                alert.setNegativeButton("ביטול", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // Canceled.
                    }
                });

                alert.show();

            }
        });
    }
    public void DeleteAllList(){
        BtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listItem.clear();
                preferences = getSharedPreferences("PREFS",0);
                editor = preferences.edit();
                editor.clear();
                editor.commit();

                refreshAdapterThread();
            }
        });
    }
    void refreshAdapterThread()
    {
        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                refreshList();
            }
        }).start();
    }

    void refreshList()
    {
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                adapter.notifyDataSetChanged();
            }
        });
    }



}
