package com.example.carloanpaymentcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SeekBar monthsBar;
    private TextView monthsLabel;
    private EditText carCost;
    private EditText downPay;
    private EditText AIR;
    private RadioButton loanbutton;
    private RadioButton leasebutton;
    private EditText monthlyPay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        monthsBar = findViewById(R.id.monthsBar);
        monthsLabel = findViewById(R.id.monthsLabel);
        carCost = findViewById(R.id.carCost);
        downPay = findViewById(R.id.downPay);
        AIR = findViewById(R.id.AIR);
        loanbutton = findViewById(R.id.loanbutton);
        leasebutton = findViewById(R.id.leaseButton);
        monthlyPay = findViewById(R.id.monthlyPay);

        monthsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                monthsLabel.setText(progress + " ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void calculate(View v) {
        String ccinput = carCost.getText().toString();
        if (ccinput.length() > 0) {
            double ccvalue = Double.parseDouble(ccinput);
            String dpinput = downPay.getText().toString();
            if (dpinput.length() > 0) {
                double dpvalue = Double.parseDouble(dpinput);
                String airinput = AIR.getText().toString();
                if (airinput.length() > 0) {
                    double airvalue = Double.parseDouble(airinput);
                    String monthsStr = monthsLabel.getText().toString();
                    double monthsInt = Double.parseDouble(monthsStr);
                    if (loanbutton.isChecked()) {
                        double mpayvalue = ((airvalue / 100) / 12 * (ccvalue - dpvalue)) / (1 - Math.pow((1 + airvalue / 100 / 12), (monthsInt*-1)));
                        monthlyPay.setText(String.format("%.2f", mpayvalue));
                    } else if (leasebutton.isChecked()) {
                        double mpayvalue = ((airvalue / 100) / 12 * ((ccvalue / 3) - dpvalue)) / (1 - Math.pow(1 + airvalue / 100 / 12, -36));
                        monthlyPay.setText(String.format("%.2f", mpayvalue));
                    } else {
                        Toast.makeText(this, "No A. I. R. entered!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "No Down Payment entered", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "No Down Payment entered", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
