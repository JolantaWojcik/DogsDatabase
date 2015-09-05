package pl.jolantawojcik.bazapsow;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Jola on 9/5/2015.
 */
public class RemoveDog extends Fragment {

    private EditText ed_id, ed_name;
    private int dog_id;
    private String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.remove_dog, container, false);

        ed_id = (EditText) view.findViewById(R.id.ed_id);
        ed_name = (EditText) view.findViewById(R.id.ed_name);

        dog_id = Integer.parseInt(ed_id.getText().toString());
        name = ed_name.getText().toString();

        return view;
    }

    public void removeDog(View view) {
    }

    public void changeCategory(View view) {
    }
}


