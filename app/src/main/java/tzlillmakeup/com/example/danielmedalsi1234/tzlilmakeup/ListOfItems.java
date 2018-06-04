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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielmedalsi1234 on 24/03/18.
 */

public class ListOfItems extends Activity {
    public static final String TAG = "ListOfItems";
    ListView listView;
    List<String> listItem;
    Button BtnAddItem,BtnOkItem;
    EditText txtAddNewItem;
    private EditText input1,input2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_of_list);
        listView = findViewById(R.id.listView);
        BtnAddItem = findViewById(R.id.BtnAddNewItem);
        BtnOkItem = findViewById(R.id.BtnOkAddItem);
        txtAddNewItem = findViewById(R.id.txtAddNewItem);
        listItem = new ArrayList<>();
        SharedPreferences preferences = getSharedPreferences("PREFS",0);
        String items = preferences.getString("item","");
        String[] itemsArray = items.split(",");
        List<String> ItemList = new ArrayList<>();
        for (int i=0;i<itemsArray.length;i++){
            listItem.add(itemsArray[i]);
        }
        for (int i=0;i<ItemList.size();i++){
            Log.d("listItem",ItemList.get(i));
        }
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
        BtnOkItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = txtAddNewItem.getText().toString();
                listItem.add(input.toString());
                Toast.makeText(ListOfItems.this,"פריט התווסף!",
                        Toast.LENGTH_SHORT).show();
                txtAddNewItem.setVisibility(View.INVISIBLE);
                BtnOkItem.setVisibility(View.INVISIBLE);
                txtAddNewItem.setText("");

                StringBuilder stringBuilder = new StringBuilder();
                for(String item : listItem){
                    stringBuilder.append(item);
                    stringBuilder.append(",");
                }
                SharedPreferences preferences = getSharedPreferences("PREFS",0);
                SharedPreferences.Editor editor =preferences.edit();
                editor.putString("item",stringBuilder.toString());
                editor.commit();
            }
        });
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listItem);
        listView.setAdapter(adapter);

    }






}
