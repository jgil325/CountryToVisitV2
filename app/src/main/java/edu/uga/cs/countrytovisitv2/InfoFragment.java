package edu.uga.cs.countrytovisitv2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.app.Fragment;
import android.widget.Toast;

import java.io.InputStream;

public class InfoFragment extends Fragment {
    TextView detailsText;
    InputStream readText;
    ImageView imageView1, imageView2;
    String currentCountry;
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
        index = getActivity().getIntent().getIntExtra("index", -1);

        //Show the correct country based on the one selected
        indexToCountry(index);
        Toast toast = Toast.makeText( getActivity().getApplicationContext(), index + "test",
                Toast.LENGTH_SHORT);
        toast.show();
        showSelectedCountry(currentCountry);
    }

    public void indexToCountry(int index) {
        int i = index;
        if (i == 0) {
            currentCountry = "Colombia";
        }
        if (i == 1) {
            currentCountry = "Venezuela";
        }
        if (i == 2) {
            currentCountry = "Mexico";
        }
        if (i == 3) {
            currentCountry = "Spain";
        }
        if (i == 4) {
            currentCountry = "France";
        }
    }

    //Reads correct text files and sets pictures based on the country
    public void showSelectedCountry(String country) {
        try {
            if (country.equals("Colombia")) {
                setColombia();
            } else if (country.equals("Venezuela")) {
                setVenezuela();
            } else if (country.equals("Mexico")) {
                setMexico();
            } else if (country.equals("Spain")) {
                setSpain();
            } else if (country.equals("France")) {
                setFrance();
            }
            byte[] b = new byte[readText.available()];
            readText.read(b);
            detailsText.setText(new String(b));
        } catch (Exception e) {
            detailsText.setText( "Error: can't show info text." );
        }
    }

    //---------------------------Set images and text file to be read depending on country
    public void setColombia() {
        readText = getResources().openRawResource(R.raw.colombia_details);
        imageView1.setImageResource(R.drawable.col_uber);
        imageView2.setImageResource(R.drawable.col_med);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setVenezuela() {
        readText = getResources().openRawResource(R.raw.venezuela_details);
        imageView1.setImageResource(R.drawable.ven_1);
        imageView2.setImageResource(R.drawable.ven_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setMexico() {
        readText = getResources().openRawResource(R.raw.mexico_details);
        imageView1.setImageResource(R.drawable.mex_1);
        imageView2.setImageResource(R.drawable.mex_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setSpain() {
        readText = getResources().openRawResource(R.raw.spain_details);
        imageView1.setImageResource(R.drawable.spain_1);
        imageView2.setImageResource(R.drawable.spain_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setFrance() {
        readText = getResources().openRawResource(R.raw.france_details);
        imageView1.setImageResource(R.drawable.france_1);
        imageView2.setImageResource(R.drawable.france_2);
    }

}
