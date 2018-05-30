package tzlillmakeup.com.example.danielmedalsi1234.tzlilmakeup;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.List;

public class CheckMessagesThread extends Thread {

    private boolean go = true;
    private OnNewMessageListener listener;
    private List<String> messages;

    public CheckMessagesThread(OnNewMessageListener listener, List<String> messages) {
        this.listener = listener;
        this.messages = messages;
    }

    @Override
    public void run() {

        while(go){
            URL url = null;
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try{
                url = new URL(ChatOnline.BASE_URL + "?get_messages="+messages.size());
                urlConnection = (HttpURLConnection)url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setRequestProperty("Content-Type", "text/plain");
                urlConnection.setUseCaches(false);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                byte[] buffer = new byte[128];
                StringBuilder stringBuilder = new StringBuilder();
                int actuallyRead;
                while ((actuallyRead = inputStream.read(buffer)) != -1){
                    stringBuilder.append(new String(buffer, 0, actuallyRead));
                }
                String messageFromServer = stringBuilder.toString();
                if(messageFromServer.length() > 0) {
                    String[] newMessages = messageFromServer.split("&");
                    for (String newMessage : newMessages) {
                        messages.add(URLDecoder.decode(newMessage, "UTF-8"));
                    }
                    if (listener != null)
                        listener.onNewMessage();
                }
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
                if(urlConnection != null)
                    urlConnection.disconnect();
            }

            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {

            }
        }
    }

    public void stopChecking(){
        go = false;
        interrupt();

    }

    public interface OnNewMessageListener{
        void onNewMessage();
    }
}