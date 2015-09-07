package pl.jolantawojcik.bazapsow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Jola on 9/6/2015.
 */
public class ListAdapter extends BaseAdapter{

    private TextView textView;
    private ImageView imageView;
    HashMap<String, String> hashMap = new HashMap<String, String>();
    ArrayList<Dogs> arrayList;
    Context context;
    LayoutInflater layoutInflater;
    private LinearLayout linearLayout;

    public ListAdapter(Context context, ArrayList<Dogs> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.list_adapter, parent, false);

        linearLayout = (LinearLayout) view.findViewById(R.id.layout_adapter);
        textView = (TextView) view.findViewById(R.id.name_dog);
        imageView = (ImageView) view.findViewById(R.id.image_dog);

        arrayList.get(position);
        textView.setText(arrayList.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SingleItem.class);
                intent.putExtra("name", arrayList.get(position).getName());
                intent.putExtra("contact", arrayList.get(position).getContact());
                intent.putExtra("description", arrayList.get(position).getDescription());
                context.startActivity(intent);
            }
        });

        return view;
    }
}
