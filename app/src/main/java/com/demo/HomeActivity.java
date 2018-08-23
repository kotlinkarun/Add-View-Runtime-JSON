package com.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private LinearLayout mContainerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mContainerView = findViewById(R.id.parentView);
      //  btnAddNewItem = findViewById(R.id.btnAddNewItem);
    }

    public void onAddNewClicked(View view) {
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View rowView = layoutInflater.inflate(R.layout.row, null);
        mContainerView.addView(rowView, mContainerView.getChildCount() - 1);
    }

    public void onDeleteClicked(View view) {
        mContainerView.removeView((View) view.getParent());
    }

    public void btnSaveData(View view) {
        ArrayList<Person> nameList = new ArrayList<>();
        Log.d("TEST", "Container View Size : " + mContainerView.getChildCount());
        for (int i = 0; i < mContainerView.getChildCount() - 1; i++) {
            LinearLayout linearLayout = (LinearLayout) mContainerView.getChildAt(i);
            EditText editText = linearLayout.findViewById(R.id.et_name);
            EditText editTextId = linearLayout.findViewById(R.id.et_id);
            nameList.add(new Person(Integer.parseInt(editTextId.getText().toString()), editText.getText().toString()));
        }



        JsonObject json1=new JsonObject();
        JsonObject json=new JsonObject();
        JsonArray arr=new JsonArray();
        for (Person p : nameList) {
            json.addProperty("id",p.id);
            json.addProperty("name",p.name);
            arr.add(json);
            Log.d("TEST", "Id : " + p.id + " Name : " + p.name + "\n");
        }
        json1.add("emp",arr);
        Toast.makeText(getApplicationContext(),""+json1.toString(),Toast.LENGTH_SHORT).show();
        Log.d("TEST", "Data : "+""+json1.toString());

    }
}
