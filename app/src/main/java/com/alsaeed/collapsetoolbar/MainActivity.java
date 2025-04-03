package com.alsaeed.collapsetoolbar;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
      //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Back arrow
      //  getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set custom back arrow
       // toolbar.setNavigationIcon(ContextCompat.getDrawable(this, R.drawable.back_arrow));

        // Set toolbar background color (optional if already set in XML)
     //   toolbar.setBackgroundColor(ContextCompat.getColor(this, R.color.your_custom_toolbar_color));

        // Collapsing Toolbar Layout
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);

        collapsingToolbarLayout.setTitle("");



        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShown = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (Math.abs(verticalOffset) >= scrollRange) {
                    // Toolbar is fully collapsed
                    Log.d("Alsaeed", "onOffsetChanged: COLLAPSED");
                    collapsingToolbarLayout.setTitle("Toolbar Title");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.RED);

                    isShown = true;
                } else if (verticalOffset == 0) {
                    // Toolbar is fully expanded
                    Log.d("Alsaeed", "onOffsetChanged: EXPANDED");
                    collapsingToolbarLayout.setTitle("");  // Hide title when expanded
                    isShown = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Handle back arrow press
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
