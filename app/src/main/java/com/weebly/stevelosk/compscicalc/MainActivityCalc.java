package com.weebly.stevelosk.compscicalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import static com.weebly.stevelosk.compscicalc.R.id.numberScreen;

public class MainActivityCalc extends AppCompatActivity {

    String enteredChars = "";
    TextView numberScreen;
    Button[] digitButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // textView of selected characters
        numberScreen = (TextView) findViewById(R.id.numberScreen);

        // initialize buttons
        digitButtons = new Button[10];
        digitButtons[0] = (Button) findViewById(R.id.zeroButton);
        digitButtons[1] = (Button) findViewById(R.id.oneButton);
        digitButtons[2] = (Button) findViewById(R.id.twoButton);
        digitButtons[3] = (Button) findViewById(R.id.threeButton);
        digitButtons[4] = (Button) findViewById(R.id.fourButton);
        digitButtons[5] = (Button) findViewById(R.id.fiveButton);
        digitButtons[6] = (Button) findViewById(R.id.sixButton);
        digitButtons[7] = (Button) findViewById(R.id.sevenButton);
        digitButtons[8] = (Button) findViewById(R.id.eightButton);
        digitButtons[9] = (Button) findViewById(R.id.nineButton);

        Button addButton = (Button) findViewById(R.id.addButton);
        Button subtractButton = (Button) findViewById(R.id.subtractButton);
        Button multiplyButton = (Button) findViewById(R.id.multiplyButton);
        Button divideButton = (Button) findViewById(R.id.divideButton);
        Button equalsButton = (Button) findViewById(R.id.equalsButton);

        Button dataTypeButton = (Button) findViewById(R.id.dataTypeButton);
        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredChars = "";
                updateNumberScreen(numberScreen);
            }
        });

        // digit button press handler
        registerDigitButtonHandler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_calc, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void updateNumberScreen(TextView numberScreen) {
        if (enteredChars != null)
            numberScreen.setText(enteredChars);
    }


    private void registerDigitButtonHandler() {
        for (int i = 0; i < 10; i++) {
            final Button button = digitButtons[i];
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enteredChars += button.getText().toString();
                    updateNumberScreen(numberScreen);
                }

            });
        }
    }
}