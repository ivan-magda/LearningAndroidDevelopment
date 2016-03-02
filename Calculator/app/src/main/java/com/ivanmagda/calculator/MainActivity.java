package com.ivanmagda.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText firstOperand;
    private EditText secondOperand;
    private Button additionButton;
    private Button substractionButton;
    private Button divisionButton;
    private Button multiplicationButton;
    private Button clearButton;
    private TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstOperand = (EditText) findViewById(R.id.firstOperandEditText);
        secondOperand = (EditText) findViewById(R.id.secondOperandEditText);
        additionButton = (Button) findViewById(R.id.additionButton);
        substractionButton = (Button) findViewById(R.id.subtractionButton);
        divisionButton = (Button) findViewById(R.id.divisionButton);
        multiplicationButton = (Button) findViewById(R.id.multiplicationButton);
        clearButton = (Button) findViewById(R.id.clearButton);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        setOnClickListenerForButton(additionButton);
        setOnClickListenerForButton(substractionButton);
        setOnClickListenerForButton(divisionButton);
        setOnClickListenerForButton(multiplicationButton);
        setOnClickListenerForButton(clearButton);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void setOnClickListenerForButton(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstOperand.getText().length() <= 0 || secondOperand.getText().length() <= 0) {
                    Toast.makeText(MainActivity.this, "Please enter numbers in both operands fields",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                double operand1 = Double.parseDouble(firstOperand.getText().toString());
                double operand2 = Double.parseDouble(secondOperand.getText().toString());

                double result = 0.0;

                switch (button.getId()) {
                    case R.id.additionButton:
                        result = operand1 + operand2;
                        break;
                    case R.id.subtractionButton:
                        result = operand1 - operand2;
                        break;
                    case R.id.divisionButton:
                        result = operand1 / operand2;
                        break;
                    case R.id.multiplicationButton:
                        result = operand1 * operand2;
                        break;
                    case R.id.clearButton:
                        firstOperand.setText("");
                        secondOperand.setText("");
                        firstOperand.requestFocus();
                        break;
                }

                resultTextView.setText(Double.toString(result));
            }
        });
    }

}
