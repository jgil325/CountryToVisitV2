package edu.uga.cs.countrytovisitv2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;

import java.io.InputStream;

public class InfoFragment extends Fragment {
    TextView detailsText;
    InputStream readText;
    ImageView imageView1, imageView2;
    String currentCountry ="Colombia";
    int index;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setContentView(R.layout.info_fragment_view);

        //Set variables to the buttons
        detailsText = getActivity().findViewById(R.id.detail_text);
        imageView1 = getActivity().findViewById(R.id.detail_img1);
        imageView2 = getActivity().findViewById(R.id.detail_img2);

        //Get the current country from mainActivity intent
        index = getActivity().getIntent().getIntExtra("Index", -1);
        //Show the correct country based on the one selected
//        showSelectedCountry(currentCountry);

    }
}
