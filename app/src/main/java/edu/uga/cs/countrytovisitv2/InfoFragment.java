package edu.uga.cs.countrytovisitv2;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.io.InputStream;


public class InfoFragment extends Fragment {
    String country;
    TextView textView;
    InputStream readText;

    public InfoFragment()
    {
        // required default constructor
    }

    // This method is similar to the Factory Method design pattern
    // to create new instances of this fragment.
    // There is a specific reason for having this method, though.  We want to send some data (VersionIndex, here) into the
    // new fragment.  Android disallows overloaded constructors for fragments, and so we can't create a Fragment with
    // the versionIndex as argument.  But we can use the Bundle and send the data this way.  Also, the setArguments call
    // must happen BEFORE the fragment is attached an activity.
    public static InfoFragment newInstance( int index ) {

        // this uses the default constructor (not defined in this class, but Java-supplied)
        InfoFragment fragment = new InfoFragment();

        // save the selected list versionIndex in the new fragment's Bundle data
        Bundle args = new Bundle();
        args.putInt( "index", index );
        fragment.setArguments( args );

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
        // Programmatically, create a scrollable TextView to show the Android version information
        ScrollView scroller = new ScrollView( getActivity()) ;
        textView = new TextView( getActivity() );
        scroller.addView( textView );

        // Set the padding for the new TextView
        // These padding attributes are normally defined in the XML file
        // here, they are set programmatically.
        int padding = (int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 12, getActivity().getResources().getDisplayMetrics() );
        textView.setPadding( padding, padding, padding, padding );

        // set the text size
        textView.setTextSize( TypedValue.COMPLEX_UNIT_SP, 18f );

        setCountry(getShownVersionIndex());
//        System.out.println("-------------------------------------------------------");
//        System.out.println(getShownVersionIndex());
//        System.out.println("-------------------------------------------------------");
        showSelectedCountry(country);

        return scroller;
    }

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
            textView.setText(new String(b));
        } catch (Exception e) {
            textView.setText( "Error: can't show info text." );
        }
    }

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
        readText = getResources().openRawResource(R.raw.colombia_details);
//        imageView1.setImageResource(R.drawable.col_uber);
//        imageView2.setImageResource(R.drawable.col_med);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setVenezuela() {
        readText = getResources().openRawResource(R.raw.venezuela_details);
//        imageView1.setImageResource(R.drawable.ven_1);
//        imageView2.setImageResource(R.drawable.ven_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setMexico() {
        readText = getResources().openRawResource(R.raw.mexico_details);
//        imageView1.setImageResource(R.drawable.mex_1);
//        imageView2.setImageResource(R.drawable.mex_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setSpain() {
        readText = getResources().openRawResource(R.raw.spain_details);
//        imageView1.setImageResource(R.drawable.spain_1);
//        imageView2.setImageResource(R.drawable.spain_2);
    }

    //---------------------------Set images and text file to be read depending on country
    public void setFrance() {
        readText = getResources().openRawResource(R.raw.france_details);
//        imageView1.setImageResource(R.drawable.france_1);
//        imageView2.setImageResource(R.drawable.france_2);
    }


}

