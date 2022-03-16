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

        countries = getResources().getStringArray(
                R.array.countries);
        // create a new ArrayAdapter for the list
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_activated_1, countries));

        // set the twoFragmentsActivity variable to true iff we are in 2 fragment (landscape) view
        View detailsFrame = getActivity().findViewById(R.id.countryInfo);

        twoFragmentsActivity = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        // restore the saved list index selection (Android version no), if available
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            countryIndex = savedInstanceState.getInt("countrySelection", 0);
        }

        // set the list mode as single choice and
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setItemChecked(countryIndex, true);

        // if we are in 2 fragment (landscape) orientation, show the Android version information on the right side
        // This class will act as the "driver" here by displaying the details using the other fragment.
        if (twoFragmentsActivity) {

            // display the information about the selected Android version in the fragment on the right (details)
            showCountryInfo(countryIndex);

            // The list in the landscape orientation may be shorter and the selected item
            // which was visible in portrait mode might be invisible (out of view)
            // i.e., "below" the end of the screen in landscape orientation.
            // To make it visible again, call smoothScrollToPosition
            getListView().smoothScrollToPosition(countryIndex);
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // on a click on a list item, show the selected Android version info
        // store the list view and selection for coming back to the list (using the back button)
        //firstVisibleVersionIndex = l.getFirstVisiblePosition();
        //versionIndex = position;
        showCountryInfo(position);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the list index selection
        outState.putInt("countrySelection", countryIndex);
    }

    void showCountryInfo(int index) {

        this.countryIndex = index;

        if (twoFragmentsActivity) {  // only in the 2 fragment (landscape) orientation

            getListView().setItemChecked(index, true);

            // get the placeholder element (FrameLayout) in a 2 fragment (landscape) orientation layout
            InfoFragment details =
                    (InfoFragment) getParentFragmentManager().findFragmentById(R.id.countryInfo);

            if (details == null || details.getShownVersionIndex() != index) {

                // obtain a new Android info fragment instance
                details = InfoFragment.newInstance(index);

                // replace the placeholder with the new fragment stance within a transaction
                FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.countryInfo, details);

                // TRANSIT_FRAGMENT_FADE means that the fragment should fade in or fade out
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);

                // commit the transaction, i.e. make the changes
                fragmentTransaction.commit();
            }
        } else {
            // In a 1 fragment orientation (portrait), start a new activity using an Intent, as in the previous demo app
            Intent intent = new Intent();
            intent.setClass(getActivity(), CountryInfoActivity.class);
            intent.putExtra("index", index);

            startActivity(intent);
        }
    }
}


