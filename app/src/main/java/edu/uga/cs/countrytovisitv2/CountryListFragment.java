package edu.uga.cs.countrytovisitv2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.fragment.app.Fragment;
import android.app.ListFragment;

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
    }

}
