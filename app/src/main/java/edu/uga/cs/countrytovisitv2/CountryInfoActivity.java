package edu.uga.cs.countrytovisitv2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class CountryInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {

        super.onCreate( savedInstanceState );

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled( true );

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ) {
            finish();
            return;
        }

        InfoFragment countryInfoFragment = new InfoFragment();

        countryInfoFragment.setArguments( getIntent().getExtras() );

        getSupportFragmentManager().beginTransaction().replace( android.R.id.content, countryInfoFragment ).commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Implement Back button listener method.
        // This method may be used for other actions from the ActionBar menu, if provided in the layout.
        int id = item.getItemId();

        // android.R.id.home is the built-in designation of the back button widget.
        if( id == android.R.id.home ) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

}

