package pl.jolantawojcik.bazapsow;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


/**
 * Created by Jola on 9/5/2015.
 */
public class AddDog extends Fragment {

    private String name, description, contact, category;
    private EditText edName , edDescription, edContac;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private static final String PING_CREATE = "http://v-ie.uek.krakow.pl/~s148228/psy/create_row.php";
    private static final String TAG_SUCCESS = "success";
    private ProgressDialog progressDialog;
    private Button add, addImage;
    ImageView imageView;
    private AlertDialog alertDialog;
    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;
    Context context;
    DogsList dogsList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_dog, container, false);
        context = getContext();
        edName = (EditText) view.findViewById(R.id.dog_name);
        edDescription = (EditText) view.findViewById(R.id.dog_description);
        edContac = (EditText) view.findViewById(R.id.contact);
        radioGroup = (RadioGroup) view.findViewById(R.id.category);
        imageView = (ImageView) view.findViewById(R.id.imageView);
        add = (Button) view.findViewById(R.id.dodaj_button);
        addImage = (Button) view.findViewById(R.id.image_button);

        int selectedIt = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) view.findViewById(selectedIt);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = (String) radioButton.getText();
                name = edName.getText().toString();
                description = edDescription.getText().toString();
                contact = edContac.getText().toString();
                new AddNewDog().execute();
            }
        });

        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            Matrix matrix = new Matrix();
            matrix.postRotate(90);
            bitmap = BitmapFactory.decodeFile(picturePath);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            imageView.setImageBitmap(rotatedBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class AddNewDog extends AsyncTask <String, String, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setTitle("Trwa dodawanie psa do bazy");
            progressDialog.setIndeterminate(false);
            progressDialog.setMessage("Dodawanie wyniku...");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
           HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(PING_CREATE);

            //Post Data
            List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
            nameValuePair.add(new BasicNameValuePair("category", category));
            nameValuePair.add(new BasicNameValuePair("name", name));
            nameValuePair.add(new BasicNameValuePair("description", description));
            nameValuePair.add(new BasicNameValuePair("contact", contact));

            try{
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

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            if (result == false) {
                Toast toast = Toast.makeText(getActivity(), "Wyst\u0105pi\u0142y problemy z dodawaniem danych", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            } else {
                AddDialog addDialog = new AddDialog(getContext());
                addDialog.show();
            }
            progressDialog.dismiss();
        }
    }
    private class AddDialog extends AlertDialog {
        public AddDialog(Context context) {
            super(context);
            setTitle("Dane zosta\u0142y dodane do bazy");
            setMessage("Zapami\u0119taj swoje Id b\u0119dzie potrzebne do edytowania danych, Id to :");
            setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  Intent intent = new Intent(getActivity(), MainActivity.class);
                  startActivity(intent);
                }
            });
        }
    }
}
