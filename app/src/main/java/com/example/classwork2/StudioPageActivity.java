package com.example.classwork2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


public class StudioPageActivity extends AppCompatActivity {
    Studio studio;
    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studio_page);

        loadStudioInfo();
        initToolBar();

        textView = findViewById(R.id.description_Studio);
        textView.setText(studio.getDescription());



    }


    private void loadStudioInfo() {
        if (getIntent().getExtras() != null) {
            studio = (Studio) getIntent().getExtras().get("Studio");
        }
    }

    private void initToolBar() {
        Toolbar toolbar;
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(studio.getTitle());
            imageView = findViewById(R.id.ToolBar_Image);
            imageView.setImageResource(studio.getImage());
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}