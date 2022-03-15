package edu.uga.cs.countrytovisitv2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.icu.text.IDNA;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

public class FragmentHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // If the screen is now in landscape mode, we can show the
            // dialog in-line so we don't need this activity.
            finish();
            return;
        }

        if (savedInstanceState == null) {
            setContentView(R.layout.fragment_holder);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if(getIntent().getExtras() != null) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                InfoFragment details = new InfoFragment();
                details.setArguments(getIntent().getExtras());
                ft.replace(R.id.listView, details);
                ft.commit();
            }
        }
    }

    //Used for the back button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //Used for the back button
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}
