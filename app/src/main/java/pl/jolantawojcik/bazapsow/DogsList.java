package pl.jolantawojcik.bazapsow;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Entity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
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
import java.util.HashMap;

/**
 * Created by Jola on 9/5/2015.
 */
public class DogsList extends Fragment {

    private ProgressDialog progressDialog;
    HashMap<String, String> map;
    private ListView listView;
    ListAdapter listAdapter;
    private String name;
    private static final String TAG_ID = "pid";
    public static final String TAG_NAME = "name";
    private Context contex;
    ArrayList<Dogs> dogsList  = new ArrayList<Dogs>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contex = getContext();
        View view = inflater.inflate(R.layout.dogs_list, container, false);
        listView = (ListView) view.findViewById(R.id.list_dogs);
        new LoadDogs(contex, dogsList).execute();
        listAdapter = new ListAdapter(contex, dogsList);
        listView.setAdapter(listAdapter);

        return view;
    }
}
