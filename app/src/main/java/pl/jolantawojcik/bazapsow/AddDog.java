package pl.jolantawojcik.bazapsow;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by Jola on 9/5/2015.
 */
public class AddDog extends Fragment {

    private String name, description, contact, category;
    private EditText edName, edDescription, edContac;
    private RadioGroup radioGroup;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_dog, container, false);

        edName = (EditText) view.findViewById(R.id.dog_name);
        edDescription = (EditText) view.findViewById(R.id.dog_description);
        edContac = (EditText) view.findViewById(R.id.contact);
        radioGroup = (RadioGroup) view.findViewById(R.id.category);

        name = edName.getText().toString();
        description = edDescription.getText().toString();
        contact = edContac.getText().toString();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.category_baza:
                        category = "baza";
                        break;
                    case R.id.category_zaginione:
                        category = "zaginione";
                        break;
                    case R.id.category_adopcja:
                        category = "adopcja";
                        break;
                }
            }
        });

        return view;
    }

    public void AddImage(View view) {
    }

    public void AddDogToDB(View view) {

    }
}
