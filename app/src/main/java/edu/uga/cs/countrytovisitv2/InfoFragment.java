package edu.uga.cs.countrytovisitv2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.io.InputStream;

//Fragment containing all the country information, and depending on which
//index was selected, it will show the associated info
public class InfoFragment extends Fragment {
    String country;
    TextView detailTextView, mainTextView;
    ImageView imageView1, imageView2;
    InputStream readDetailText, readMainText;

    public InfoFragment() {
        // required default constructor
    }

    public static InfoFragment newInstance( int index ) {

        // this uses the default constructor
        InfoFragment fragment = new InfoFragment();

        // save the selected country/index in the new fragment's Bundle data
        Bundle args = new Bundle();
        args.putInt( "index", index );
        fragment.setArguments( args );

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        //Creates a new view instance with the correct info
        View myFragmentView = inflater.inflate(R.layout.info_fragment_layout, container, false);

        detailTextView = (TextView) myFragmentView.findViewById(R.id.detail_text);
        mainTextView = (TextView) myFragmentView.findViewById(R.id.main_info_text);
        imageView1 = (ImageView) myFragmentView.findViewById(R.id.img1);
        imageView2 = (ImageView) myFragmentView.findViewById(R.id.img2);

        setCountry(getShownVersionIndex());
//        System.out.println("-------------------------------------------------------");
//        System.out.println(getShownVersionIndex());
//        System.out.println("-------------------------------------------------------");
        showSelectedCountry(country);

        return myFragmentView;
    }

    //Calls the correct method to read text and set images depending on which country was selected
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
            byte[] b = new byte[readDetailText.available()];
            readDetailText.read(b);
            detailTextView.setText(new String(b));
            byte[] main = new byte[readMainText.available()];
            readMainText.read(main);
            mainTextView.setText(new String(main));
        } catch (Exception e) {
            detailTextView.setText( "Error: can't show info text." );
/*           System.out.println("-------------------------------------------------------");
            System.out.println("NOT WORKING");
            System.out.println("-------------------------------------------------------"); */
        }
    }

    //Sets the correct country name based on the index of the list item that was selected
    public void setCountry(int index) {
        if (index == 0) {
            country="Colombia";
        } else if (index == 1) {
            country="Venezuela";
        } else if (index == 2) {
            country="Mexico";
        } else if (index == 3) {
            country="Spain";
        } else if (index == 4) {
            country="France";
        }
    }

    public int getShownVersionIndex() {
        return getArguments().getInt("index", 0 );
    }

    //---------------------------Set images and text file to be read depending on country
    public void setColombia() {
        readMainText = getResources().openRawResource(R.raw.colombia);
        readDetailText = getResources().openRawResource(R.raw.colombia_details);
        imageView1.setImageResource(R.drawable.col_flag);
        imageView2.setImageResource(R.drawable.col_med);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setVenezuela() {
        readMainText = getResources().openRawResource(R.raw.venezuela);
        readDetailText = getResources().openRawResource(R.raw.venezuela_details);
        imageView1.setImageResource(R.drawable.ven_flag);
        imageView2.setImageResource(R.drawable.ven_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setMexico() {
        readMainText = getResources().openRawResource(R.raw.mexico);
        readDetailText = getResources().openRawResource(R.raw.mexico_details);
        imageView1.setImageResource(R.drawable.mex_flag);
        imageView2.setImageResource(R.drawable.mex_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setSpain() {
        readMainText = getResources().openRawResource(R.raw.spain);
        readDetailText = getResources().openRawResource(R.raw.spain_details);
        imageView1.setImageResource(R.drawable.spain_flag);
        imageView2.setImageResource(R.drawable.spain_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setFrance() {
        readMainText = getResources().openRawResource(R.raw.france);
        readDetailText = getResources().openRawResource(R.raw.france_details);
        imageView1.setImageResource(R.drawable.france_flag);
        imageView2.setImageResource(R.drawable.france_2);
    }


}

