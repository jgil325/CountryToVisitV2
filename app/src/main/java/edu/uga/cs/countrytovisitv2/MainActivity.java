package edu.uga.cs.countrytovisitv2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.InputStream;

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

        try {
            byte[] b = new byte[readText.available()];
            readText.read(b);
            splashText.setText(new String(b));
        } catch (Exception e) {
            splashText.setText( "Error: can't show info text." );
        }

        splashButton.setOnClickListener(new ButtonClickSplash());
    }

    private class ButtonClickSplash implements
            View.OnClickListener
    {
        @Override
        public void onClick( View view ) {
//            System.out.println("I WAS CLICKED");
            Intent intent = new Intent( view.getContext(), FragmentHolderActivity.class );
//            intent.putExtra("CurrentCountry",currentCountry);
            startActivity( intent );
        }
    }

}
