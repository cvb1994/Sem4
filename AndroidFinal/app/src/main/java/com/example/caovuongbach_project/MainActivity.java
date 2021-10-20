package com.example.caovuongbach_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public DBHelper db;
    private ListView lvListFeedback;
    private Adapter adapter;
    private List<FeedBack> listFeedback;
    Button btAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);
        btAdd = findViewById(R.id.btAdd);

        btAdd.setOnClickListener(this);

    }

    public void showList(){
        listFeedback = db.getAll();
        lvListFeedback = findViewById(R.id.lvListFeedback);

        adapter = new Adapter(this, listFeedback);
        lvListFeedback.setAdapter(adapter);
//        lvListFeedback.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                FeedBack user = listFeedback.get(i);
//                Intent intent = new Intent(MainActivity.this, EditActivity.class);
//                intent.putExtra("userId",user.getId());
//                intent.putExtra("userName", user.getName());
//                intent.putExtra("userGender", user.getGender());
//                intent.putExtra("userAge", user.getAge());
//                startActivity(intent);
//            }
//        });
//        adapter.reloadData(listUser);
    }

    @Override
    protected void onResume() {
        super.onResume();
        showList();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btAdd:
                Intent intent = new Intent(this, AddNewActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}