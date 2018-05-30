package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button BtnList;
    private Button btn_prices;
    private Button btn_ManagerLogin;
    private Button btn_chat;
    private Button BtnSendEmail;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_prices = findViewById(R.id.btn_prices);
        BtnList = findViewById(R.id.btn_list);
        btn_ManagerLogin = findViewById(R.id.btn_login_manager);
        btn_chat = findViewById(R.id.btn_login_chat);
        BtnSendEmail = findViewById(R.id.btn_send_email);

        btn_ManagerLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogLoginManager dialog = new DialogLoginManager();
                dialog.show(getFragmentManager(),TAG);
                dialog.setCancelable(true);
            }
        });
        BtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ListOfItems.class);
                    startActivity(intent);

            }
        });
        btn_prices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,NewOffersPrice.class));
            }
        });
        btn_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ChatOnline.class));
            }
        });
        BtnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }

    protected void sendEmail() {
        Log.i("Send email", "");
        String[] TO = {"Txlilil1995@gmail.com"};
        //String[] CC = {"danielmedalsi1234@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("Txlilil1995@gmail.com"));
        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        try {
            startActivity(Intent.createChooser(emailIntent, "שולח........"));
            Log.i(TAG,"השליחה הסתיימה");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this,"השליחה לא הצליחה!",Toast.LENGTH_SHORT).show();
        }
    }
}
