package com.example.classwork2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerViewStudio;
    private StudioHomeAdapter studioHomeAdapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Studio List");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Home_ToolBar)));

        loadBottomNavigationView();
        //load data
        recyclerViewStudio = findViewById(R.id.RecyclerViewStudio);
        LinearLayoutManager linearLayoutManagerStudio = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerViewStudio.setLayoutManager(linearLayoutManagerStudio);
        studioHomeAdapter = new StudioHomeAdapter(getStudioData(), new IClickItemStudioListener() {
            @Override
            public void onClickItemStudio(Studio studio) {
                Toast.makeText(MainActivity.this, studio.getTitle(), Toast.LENGTH_SHORT).show();
                onClickItemGoDetail(studio);
            }
        });
        recyclerViewStudio.setAdapter(studioHomeAdapter);
    }

    private void loadBottomNavigationView() {
        AHBottomNavigation bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //Define Items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.action_home, R.drawable.home_white_48dp, R.color.Home_ToolBar);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.action_chat, R.drawable.chat_white_48dp, R.color.Chat_ToolBar);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.action_feed, R.drawable.feed_white_48dp, R.color.NewFeed_ToolBar);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.action_booking, R.drawable.shopping_cart_white_48dp, R.color.Booking_ToolBar);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.action_user, R.drawable.account_circle_white_48dp, R.color.User_ToolBar);
        // Add items
        bottomNavigationView.addItem(item1);
        bottomNavigationView.addItem(item2);
        bottomNavigationView.addItem(item3);
        bottomNavigationView.addItem(item4);
        bottomNavigationView.addItem(item5);
        //Style
        bottomNavigationView.setColored(true);

        //Notification Icon
        AHNotification notification = new AHNotification.Builder().setText("10").setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.Chat_ToolBar)).setTextColor(ContextCompat.getColor(MainActivity.this, R.color.Home_ToolBar)).build();
        bottomNavigationView.setNotification(notification, 1);
    }

    private List<Studio> getStudioData() {
        List<Studio> myList = new ArrayList<>();

        int[] image = {R.drawable.stu1, R.drawable.stu2, R.drawable.stu3, R.drawable.stu4, R.drawable.stu5, R.drawable.stu6};
        String[] studioName = {"DreamWorks Animation", "Pixar Animation Studios", "Paramount Pictures", "20th Century Studios", "Lionsgate Films", "New Line Cinema"};
        String[] studioDescription = {"Innovative animated films loved by audiences of all ages.",
                "Heartwarming storytelling and groundbreaking animation in beloved animated classics.",
                "Iconic films with a wide range of genres and timeless appeal.",
                "Home to epic blockbusters and beloved franchises.",
                "Diverse range of films from action-packed adventures to thought-provoking dramas.",
                "Known for horror classics and memorable film franchises."};
        String[] price = {"Form: US$500", "Form: US$500", "Form: US$500", "Form: US$500", "Form: US$500", "Form: US$500"};
        String[] rating = {"⭐: 5.0", "⭐: 5.0", "⭐: 5.0", "⭐: 5.0", "⭐: 5.0", "⭐: 5.0"};
        for (int i = 0; i < studioName.length; i++) {
            myList.add(new Studio(image[i], studioName[i], studioDescription[i], price[i], rating[i]));
        }
        return myList;
    }

    private void onClickItemGoDetail(Studio studio) {
        Intent intent = new Intent(this, StudioPageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("Studio", studio);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}