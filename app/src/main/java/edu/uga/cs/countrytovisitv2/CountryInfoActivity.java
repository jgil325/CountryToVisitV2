package edu.uga.cs.countrytovisitv2;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//Activity only used if portrait.
//When the list is selected, it replaces the list fragment with the info fragment
//and makes sure to pass along the selected country information
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
        int id = item.getItemId();

        if( id == android.R.id.home ) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected( item );
    }

}

