package in.androidhunt.instasample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import in.androidhunt.instapost.InstaPostView;

/**
 * Created by Pratheep Chowdhary on 03,February,2019
 */

public class MainActivity extends AppCompatActivity {
    InstaPostView instaPostView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Window w=getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instaPostView=findViewById(R.id.insta_post);
        instaPostView.setPostId("Bm8kaROlodJ");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
