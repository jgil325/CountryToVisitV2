package edu.uga.cs.countrytovisitv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

//This Activity will be simple: It will have an introduction to the app and
//have a button that takes you to the functional component that is holding the fragments
public class MainActivity extends AppCompatActivity {
    TextView splashText;
    Button splashButton;
    InputStream readText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set variables to the buttons
        splashText = findViewById(R.id.splashTextView);
        splashButton = findViewById(R.id.splashButton);
        readText = getResources().openRawResource(R.raw.splash_text);

        //Read the welcome message from a text file instead of hard coding it here
        try {
            byte[] b = new byte[readText.available()];
            readText.read(b);
            splashText.setText(new String(b));
        } catch (Exception e) {
            splashText.setText("Error: can't show info text.");
        }

        splashButton.setOnClickListener(new ButtonClickSplash());
    }

    //Button click should send us to the activity where the fragments are held
    private class ButtonClickSplash implements
            View.OnClickListener {
        @Override
        public void onClick(View view) {
//            System.out.println("I WAS CLICKED");
            Intent intent = new Intent(view.getContext(), FragmentHolderActivity.class);
//            intent.putExtra("CurrentCountry",currentCountry);
            startActivity(intent);
        }
    }

}
