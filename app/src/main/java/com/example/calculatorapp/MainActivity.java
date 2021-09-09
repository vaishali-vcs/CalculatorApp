package com.example.calculatorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import org.mariuszgromada.math.mxparser.*;


public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);


        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getString(R.string.display).equals(display.getText().toString()))
                    display.setText("");
            }
        });
    }

    private void UpdateText(String strToAdd)
    {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);

        if (getString(R.string.display).equals(display.getText().toString()))
           display.setText(strToAdd);
        else
           display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));

        display.setSelection(cursorPos + 1);
    }

    public void zeroBTN(View view)
    {
        UpdateText("0");
    }

    public void oneBTN(View view)
    {
        UpdateText("1");
    }

    public void twoBTN(View view)
    {
        UpdateText("2");
    }

    public void threeBTN(View view)
    {
        UpdateText("3");
    }

    public void fourBTN(View view)
    {
        UpdateText("4");
    }

    public void fiveBTN(View view)
    {
        UpdateText("5");
    }

    public void sixBTN(View view)
    {
        UpdateText("6");
    }

    public void sevenBTN(View view)
    {
        UpdateText("7");
    }

    public void eigthBTN(View view)
    {
        UpdateText("8");
    }

    public void nineBTN(View view)
    {
        UpdateText("9");
    }

    public void addBTN(View view)
    {
        UpdateText("+");
    }

    public void subtractBTN(View view)
    {
        UpdateText("-");
    }

    public void divideBTN(View view)
    {
        UpdateText("÷");
    }

    public void multiplyBTN(View view)
    {
        UpdateText("×");
    }

    public void plusMinusBTN(View view)
    {

    }

    public void clearBTN(View view)
    {
       display.setText("");
    }

    public void exponentBTN(View view)
    {
        UpdateText("^");
    }

    public void pointBTN(View view)
    {
        UpdateText(".");
    }

    public void equalsBTN(View view)
    {
        String userExp = display.getText().toString();
        userExp  = userExp.replaceAll("÷", "/");
        userExp  = userExp.replaceAll("×", "*");

        Expression exp  = new Expression(userExp);
        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    public void backspaceBTN(View view)
    {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen !=0)
        {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");

            display.setText(selection);
            display.setSelection(cursorPos-1);
        }
    }

    public void parenthesisBTN(View view)
    {
        int cursorPos = display.getSelectionStart();
        int openPars = 0;
        int closedPars = 0;
        int textLen  = display.getText().length();

        for(int i = 0; i <cursorPos ;i++)
        {
            if(display.getText().toString().substring(i, i+1).equals("("))
                openPars++;
            if(display.getText().toString().substring(i, i+1).equals(")"))
                closedPars++;
        }

        if(openPars == closedPars || display.getText().toString().substring(textLen-1, textLen).equals("("))
          UpdateText("(");


        else if(closedPars < openPars || !display.getText().toString().substring(textLen-1, textLen).equals("("))
            UpdateText(")");

        display.setSelection(cursorPos+1);
    }
}