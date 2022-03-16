package edu.uga.cs.countrytovisitv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;


public class CountryListFragment extends ListFragment {

    //holds the array of countries that can be selected
    private String[] countries;

    // indicate if this is a landscape mode with both fragments present or not
    boolean twoFragmentsActivity = false;

    // list selection index
    int countryIndex = 0;

    public CountryListFragment() {
        // required default constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //get the countries from res
        countries = getResources().getStringArray(
                R.array.countries);
        //set them to an adapter
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, countries));

        View detailsFrame = getActivity().findViewById(R.id.countryInfo);

        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            countryIndex = savedInstanceState.getInt("countrySelection", 0);
        }

        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(countryIndex, true);

        //if landscape show both
        if (twoFragmentsActivity) {
            showCountryInfo(countryIndex);

            getListView().smoothScrollToPosition(countryIndex);
        }

    }

    //when an country is clicked, set the information to that of the country
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        showCountryInfo(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt("countrySelection", countryIndex);
    }

    //Show the info on the selected country, if landscape, update the info fragment,
    //if portrait, replace the list fragment with the info fragment of the selected country
    void showCountryInfo(int index) {

        this.countryIndex = index;

        if (twoFragmentsActivity) {

            getListView().setItemChecked(index, true);

            InfoFragment details =
                    (InfoFragment) getParentFragmentManager().findFragmentById(R.id.countryInfo);

            if (details == null || details.getShownVersionIndex() != index) {
                details = InfoFragment.newInstance(index);

                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.countryInfo, details);

                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                fragmentTransaction.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), CountryInfoActivity.class);
            intent.putExtra("index", index);

            startActivity(intent);
        }
    }
}


