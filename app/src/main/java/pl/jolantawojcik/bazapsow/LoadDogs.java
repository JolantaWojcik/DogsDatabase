package pl.jolantawojcik.bazapsow;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jola on 9/7/2015.
 */
public class LoadDogs extends AsyncTask<String, Void, Boolean> {

    private ProgressDialog progressDialog;
    Context context;
    private static final String PING_ALL_DOGS = "http://v-ie.uek.krakow.pl/~s148228/psy/get_all_rows.php";
    ArrayList<Dogs> dogsList = new ArrayList<Dogs>();

    public LoadDogs(Context context, ArrayList<Dogs> dogsList){
        this.dogsList = dogsList;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Baza ps\u00f3w");
       // progressDialog.setIndeterminate(false);
        progressDialog.setMessage("\u0141adowanie wynik\u00f3w...");
        progressDialog.show();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            HttpGet httpGet = new HttpGet(PING_ALL_DOGS);
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String data = EntityUtils.toString(httpEntity);
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = jsonObject.getJSONArray("dogs");

            for(int i =0; i < jsonArray.length(); i++){
                JSONObject object = jsonArray.getJSONObject(i);
                Dogs dog = new Dogs();
                dog.setName(object.getString("name"));
                dog.setDescription(object.getString("description"));
                dog.setContact(object.getString("contact"));
                dogsList.add(dog);
            }
            Log.d("name22", dogsList.toString());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean==false){
            Toast.makeText(context, "Wyst\u0105pi\u0142y problemy z pobieraniem danych", Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }

}