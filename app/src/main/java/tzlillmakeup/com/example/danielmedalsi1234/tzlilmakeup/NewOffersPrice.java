package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;




public class NewOffersPrice extends Activity {


    public static final String TAG ="ChatOnline";
    public static final String BASE_URL = "http://10.0.2.2:8080/ServletDB";

    EditText txtName;
    TextView lblName;
    EditText txtPhone;
    TextView lblPhone;
    private int phoneMYSQL;
    private String fullNameMYSQL;
    String nameEntery;
    String temp1;
    Button BtnDone;
    EditText txtMessage;
    ListView lstMessages;
    private List<String> messages;
    private ArrayAdapter<String> adapter;

    public int getPhoneMYSQL() {
        return phoneMYSQL;
    }

    public void setPhoneMYSQL(int phoneMYSQL) {
        this.phoneMYSQL = phoneMYSQL;
    }

    public String getFullNameMYSQL() {
        return fullNameMYSQL;
    }

    public void setFullNameMYSQL(String fullNameMYSQL) {
        this.fullNameMYSQL = fullNameMYSQL;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.price_offers_new);
        txtMessage = findViewById(R.id.txt_name);
        lblName = findViewById(R.id.lbl_name);
        txtPhone = findViewById(R.id.txt_phone);
        lblPhone = findViewById(R.id.lbl_phone);
        BtnDone = findViewById(R.id.btn_done);
        messages = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);



        BtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameEntery = txtMessage.getText().toString();
                String phoneEntery = txtPhone.getText().toString();

                setPhoneMYSQL(Integer.parseInt(phoneEntery));
                setFullNameMYSQL(nameEntery);

                fullNameMYSQL = nameEntery;
                if (txtPhone.length() != 0 && !txtMessage.getText().toString().equals("")){
                    AppDataBaseContact db = Room.databaseBuilder(NewOffersPrice.this.getApplicationContext(),AppDataBaseContact.class,"contact")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                    db.contactDao().insertAll(new Contact(nameEntery,phoneEntery));
                    Toast.makeText(NewOffersPrice.this, "פנייתך הועברה לטיפול תודה !", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(NewOffersPrice.this,MainActivity.class));
                }else
                    Toast.makeText(NewOffersPrice.this, "פנייתך לא התקבלה", Toast.LENGTH_SHORT).show();

                String message = txtMessage.getText().toString();
                if(message.isEmpty()){

                    Toast.makeText(NewOffersPrice.this, "השדה ריק!", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AsyncTask<String, Void, Boolean>(){
                    @Override
                    protected Boolean doInBackground(String... strings) {
                        URL url = null;
                        HttpURLConnection connection = null;
                        InputStream inputStream = null;
                        try{
                            url = new URL(BASE_URL +"?txtFullName="+getFullNameMYSQL()+"&"+"txtPhone="+getPhoneMYSQL());
                            connection = (HttpURLConnection)url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.setUseCaches(false);
                            connection.setRequestProperty("Content-Type", "text/plain");
                            connection.connect();
                            inputStream = connection.getInputStream();
                            //close connection
                                return true;
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if(inputStream != null)
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            if(connection != null)
                                connection.disconnect();
                        }
                        return false;
                    }
                    @Override
                    protected void onPostExecute(Boolean success) {
                    }
                }.execute(message);
            }
        });
    }
}


