package pl.jolantawojcik.bazapsow;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class SingleItem extends Activity {

    private String name, description, contact;
    private TextView tv_name, tv_description, tv_contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item);

        tv_contact = (TextView) findViewById(R.id.tv_contact);
        tv_name =(TextView) findViewById(R.id.name_dog);
        tv_description = (TextView) findViewById(R.id.tv_cechy);

        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        description = intent.getStringExtra("description");
        contact = intent.getStringExtra("contact");

        setTitle(name);

        tv_name.setText(name);
        tv_description.setText(description);
        tv_contact.setText(contact);
    }

}
