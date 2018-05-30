package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class ChatOnline extends Activity implements CheckMessagesThread.OnNewMessageListener {

    //public static final String BASE_URL = "http://146.148.28.47/Chat_Server/ChatServlet";
    public static final String BASE_URL = "http://10.0.2.2:8080/ChatServlet";
    EditText txtMessage;
    ListView lstMessages;
    Button btnSend;
    CheckMessagesThread checkMessagesThread;
    Handler handler;
    private List<String> messages;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online_chat);
        txtMessage = findViewById(R.id.txtMessage);
        lstMessages = findViewById(R.id.lstMessages);
        btnSend = findViewById(R.id.btnSend);
        messages = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messages);
        lstMessages.setAdapter(adapter);
    }

    private void enableUI(boolean enabled){
        txtMessage.setEnabled(enabled);
        btnSend.setEnabled(enabled);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkMessagesThread = new CheckMessagesThread(this, messages);
        checkMessagesThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        checkMessagesThread.stopChecking();
        checkMessagesThread = null;
    }

    public void btnSend(View view) {
        enableUI(false);
        String message = txtMessage.getText().toString();
        if(message.isEmpty()){
            enableUI(true);
            Toast.makeText(this, "must write something...", Toast.LENGTH_SHORT).show();
            return;
        }
        new AsyncTask<String, Void, Boolean>(){
            @Override
            protected Boolean doInBackground(String... strings) {
                String message = strings[0];
                try {
                    message = URLEncoder.encode(message, "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    return false;
                }
                URL url = null;
                HttpURLConnection connection = null;
                InputStream inputStream = null;
                try{
                    url = new URL(BASE_URL + "?set_message=" +message);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setUseCaches(false);
                    connection.setRequestProperty("Content-Type", "text/plain");
                    connection.connect();
                    inputStream = connection.getInputStream();
                    byte[] buffer = new byte[64];
                    int actuallyRead = inputStream.read(buffer);
                    String response = new String(buffer, 0, actuallyRead);
                    //close connection
                    if(response.equals("ok"))
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
                enableUI(true);
                if(success){
                    txtMessage.setText("");
                    txtMessage.requestFocus();

                }else{
                    Toast.makeText(ChatOnline.this, "error!", Toast.LENGTH_SHORT).show();

                }
            }
        }.execute(message);
    }

    @Override
    public void onNewMessage() {
        new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        };
    }
}
