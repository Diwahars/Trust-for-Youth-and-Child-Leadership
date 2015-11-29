package com.nkana.app.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nkana.app.R;

/**
 * Created by Chokkar G
 */

public class ChooseMainActivity extends AppCompatActivity {
    private static final String LOG_TAG = ChooseMainActivity.class.getSimpleName();
    private Context context;
    private Button btn_mentor, volunteer, btn_student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity_screen);
        context = this;
        btn_mentor = (Button) findViewById(R.id.btn_mendor);
        volunteer = (Button) findViewById(R.id.volunteer);
        btn_student = (Button) findViewById(R.id.btn_student);

        btn_mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MendorRegistrationActivity.class);
                startActivity(intent);
            }
        });
        volunteer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VolunteerRegistrationActivity.class);
                startActivity(intent);
            }
        });
        btn_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChildrenRegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
}