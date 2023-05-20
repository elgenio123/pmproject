package com.example.myapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2;
    boolean isFirstNumber = true;
    int total = 0;
    Button button, button2, button3, button4, button5, button6, button7, button8, button9, button0, buttonPlus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.myEditText);
        editText2 = findViewById(R.id.myEditText2);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.resetButton:
                onReset();
                break;
            case R.id.button:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.button0:
            case R.id.buttonMult:
            case R.id.buttonPlus:
            case R.id.button10:
            case R.id.button11:
            case R.id.button12:
            case R.id.buttonDivision:
                onNumberClick(v);
                break;
        }
    }

    public void onNumberClick(View v) {
        Button button = (Button) v;
        String currentText = editText1.getText().toString();
        String buttonText = button.getText().toString();
        editText1.setText(currentText + buttonText);
    }
    public void onEqualsClick(View view) {
        EditText displayEditText = editText1;
        String expression = displayEditText.getText().toString();

        try {
            ExpressionBuilder builder = new ExpressionBuilder(expression);

            Expression expr = builder.build();
            double result = expr.evaluate();

            editText2.setText(Double.toString(result));
        }
        catch (IllegalArgumentException e) {
           showMessageBox("Error :"+ e.getMessage());
        }
        catch (ArithmeticException e) {
            showMessageBox("Arithmetic Error: "+ e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            showMessageBox("Error: "+ e.getMessage());
        }
    }

    private void showMessageBox(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void onReset() {
        total = 0;
        isFirstNumber = true;
        editText1.setText("");
        editText2.setText("");
    }

}
