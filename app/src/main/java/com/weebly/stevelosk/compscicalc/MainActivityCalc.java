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

import static com.weebly.stevelosk.compscicalc.R.id.addButton;
import static com.weebly.stevelosk.compscicalc.R.id.divideButton;
import static com.weebly.stevelosk.compscicalc.R.id.multiplyButton;
import static com.weebly.stevelosk.compscicalc.R.id.numberScreen;
import static com.weebly.stevelosk.compscicalc.R.id.subtractButton;

public class MainActivityCalc extends AppCompatActivity {

    String enteredChars = "";
    String charsOnScreen = "";
    TextView numberScreen;
    Button[] digitButtons;
    boolean showingOperatorSymbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_calc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // textView of selected characters
        numberScreen = (TextView) findViewById(R.id.numberScreen);
        showingOperatorSymbol = false;

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
        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredChars += charsOnScreen;
                if (!enteredChars.equals("")) {
                    // process string of ops
                    String[] operations = Calculate.processString(enteredChars);
                    long result = Calculate.calculateString(operations);
                    charsOnScreen = Long.toString(result);
                    updateNumberScreen(numberScreen);
                }
            }
        });

        Button dataTypeButton = (Button) findViewById(R.id.dataTypeButton);

        Button clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredChars = "";
                charsOnScreen = "";
                updateNumberScreen(numberScreen);
            }
        });

        Button nFactorialButton = (Button) findViewById(R.id.nFactorialButton);
        nFactorialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // ensure number input is not empty
                if (!charsOnScreen.equals("")) {
                    long input = Long.parseLong(charsOnScreen);
                    long result = Calculate.factorial(input);
                    enteredChars = "";
                    charsOnScreen = Long.toString(result);
                    updateNumberScreen(numberScreen);
                }
            }
        });

        Button nSquared = (Button) findViewById(R.id.nSquaredButton);
        Button nToTheX = (Button) findViewById(R.id.nToTheXButton);
        nSquared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!charsOnScreen.equals("")) {
                    try {
                        long input = Long.parseLong(charsOnScreen);
                        long result = Calculate.square(input);
                        enteredChars = "";
                        charsOnScreen = Long.toString(result);
                    }
                    catch (java.lang.NumberFormatException e) {
                        enteredChars = "";
                        charsOnScreen = "";
                    }
                    updateNumberScreen(numberScreen);

                }
            }
        });
        nToTheX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // digit button press handler
        registerDigitButtonHandler();
        // arithmetic button handlers
        registerArithmeticButtonHandle();
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
        //if (enteredChars != null)
            numberScreen.setText(charsOnScreen);
    }


    private void registerDigitButtonHandler() {
        for (int i = 0; i < 10; i++) {
            final Button button = digitButtons[i];
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // clear leading operator symbol
                    if (showingOperatorSymbol) {
                        charsOnScreen = "";
                        showingOperatorSymbol = false;
                    }
                    // enter digits
                    charsOnScreen += button.getText().toString();
                    updateNumberScreen(numberScreen);
                }

            });
        }
    }

    private void registerArithmeticButtonHandle() {
        final int[] arithmeticButtonIds = {addButton, subtractButton, multiplyButton, divideButton};
        for (int i = 0; i < 4; i++) {
            final Button button = (Button) findViewById(arithmeticButtonIds[i]);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    enteredChars += charsOnScreen;
                    String operator = button.getText().toString();
                    enteredChars += operator;
                    charsOnScreen = operator;
                    showingOperatorSymbol = true;
                    updateNumberScreen(numberScreen);
                }
            });
        }
    }
}