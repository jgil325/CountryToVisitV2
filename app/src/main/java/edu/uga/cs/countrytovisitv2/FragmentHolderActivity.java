package edu.uga.cs.countrytovisitv2;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

//This Activity simply holds the fragments -- fragments cannot exist alone
public class FragmentHolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_display);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled( true );
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
