package pl.jolantawojcik.bazapsow;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jola on 9/5/2015.
 */
public class RemoveDog extends Fragment {

    private EditText ed_id;
    private String dog_id;
    private ProgressDialog pDialog;
    private Button remove_dog, category_new;
    private static final String PING_REMOVE = "http://v-ie.uek.krakow.pl/~s148228/psy/delete_dog.php";
    private static final String TAG_ID = "id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.remove_dog, container, false);

        ed_id = (EditText) view.findViewById(R.id.ed_id);
        remove_dog = (Button) view.findViewById(R.id.remove);
        category_new = (Button) view.findViewById(R.id.change_button);

        dog_id = ed_id.getText().toString();

        remove_dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DeleteDog().execute();
            }
        });

        category_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private class DeleteDog extends AsyncTask<String, Void, Boolean> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Usuwanie danych...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    protected Boolean doInBackground(String... args) {
        try{
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(PING_REMOVE);
            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("id", dog_id));
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
            HttpResponse httpResponse = httpClient.execute(httpPost);

            Log.d("Http Post Response:", httpResponse.toString());

            return true;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    protected void onPostExecute(Boolean result) {
        if (result == false) {
            Toast toast = Toast.makeText(getActivity(), "Wyst\u0105pi\u0142y problemy z usuwaniem danych", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }else{
            Toast toast = Toast.makeText(getActivity(), "Dane zosta\u0142y usuni\u0119te z bazy", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }

        pDialog.dismiss();

    }
}
}


