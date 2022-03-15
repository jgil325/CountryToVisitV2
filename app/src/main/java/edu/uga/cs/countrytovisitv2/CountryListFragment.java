package edu.uga.cs.countrytovisitv2;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import android.app.ListFragment;
import android.widget.Toast;

public class CountryListFragment extends ListFragment {
    ListView listView;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setContentView(R.layout.list_fragment_view);
        listView = getActivity().findViewById( R.id.listView );

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        String[] countries = getResources().getStringArray(
                R.array.countries);
        ArrayAdapter<String> countriesAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_activated_1, countries);
        listView.setAdapter(countriesAdapter);
        listView.setOnItemClickListener(messageClickedHandler);
    }

    void showCountryDetails(int index) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), FragmentHolderActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }
    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            String x = "" + position;
            Toast toast = Toast.makeText( getActivity().getApplicationContext(),
                    x,
                    Toast.LENGTH_SHORT);
            toast.show();

            showCountryDetails(position);
        }
    };


}
