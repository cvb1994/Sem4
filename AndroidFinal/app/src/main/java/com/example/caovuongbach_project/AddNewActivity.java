package com.example.caovuongbach_project;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNewActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edName, edMail, edContent;
    Spinner spinner;
    CheckBox checkBox;
    Button btSave;
    private DBHelper db;
    int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew);
        db = new DBHelper(this);

        edName = findViewById(R.id.edName);
        edMail = findViewById(R.id.edMail);
        edContent = findViewById(R.id.edContent);
        checkBox = findViewById(R.id.checkbox);
        spinner = findViewById(R.id.spinner);
        btSave = findViewById(R.id.btSave);

        btSave.setOnClickListener(this);

        String[] genders = {"Male", "Female", "Other"};
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void saveFeedback(){

        FeedBack feedBack = new FeedBack();
        feedBack.setName(edName.getText().toString());
        feedBack.setMail(edMail.getText().toString());
        feedBack.setGender(spinner.getSelectedItem().toString());
        feedBack.setContent(edContent.getText().toString());
        if(checkBox.isChecked()){
            feedBack.setMailReceive(true);
        } else {
            feedBack.setMailReceive(false);
        }

        String message = db.insertDB(feedBack);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btSave:
                if(edName.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
                    check = 1;
                } else if(edMail.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter your mail", Toast.LENGTH_SHORT).show();
                    check = 1;
                } else if(edContent.getText().toString().isEmpty()){
                    Toast.makeText(this, "Please enter your content", Toast.LENGTH_SHORT).show();
                    check = 1;
                } else {
                    check = 0;
                }
                if(check == 0){
                    saveFeedback();
                    this.finish();
                }
                break;
            default:
                break;
        }
    }
}
