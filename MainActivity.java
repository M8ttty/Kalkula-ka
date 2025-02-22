package com.example.kalkulacka_Trejkl;
import com.example.kalkulacka_trejkl.R;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    private TextView display;
    private Spinner spinner;
    private String input = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Propojení s XML
        display = findViewById(R.id.display);
        spinner = findViewById(R.id.spinner);

        // Naplnění Spinneru operacemi
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.operations, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        // Přidání listenerů pro tlačítka
        setNumberClickListener(R.id.btn0, "0");
        setNumberClickListener(R.id.btn1, "1");
        setNumberClickListener(R.id.btn2, "2");
        setNumberClickListener(R.id.btn3, "3");
        setNumberClickListener(R.id.btn4, "4");
        setNumberClickListener(R.id.btn5, "5");
        setNumberClickListener(R.id.btn6, "6");
        setNumberClickListener(R.id.btn7, "7");
        setNumberClickListener(R.id.btn8, "8");
        setNumberClickListener(R.id.btn9, "9");

        setOperatorClickListener(R.id.btnAdd, "+");
        setOperatorClickListener(R.id.btnSubtract, "-");
        setOperatorClickListener(R.id.btnMultiply, "×");
        setOperatorClickListener(R.id.btnDivide, "÷");

        findViewById(R.id.btnClear).setOnClickListener(view -> {
            input = "";
            display.setText("0");
        });

        findViewById(R.id.btnEquals).setOnClickListener(view -> calculateResult());
    }

    private void setNumberClickListener(int buttonId, String number) {
        findViewById(buttonId).setOnClickListener(view -> {
            input += number;
            display.setText(input);
        });
    }

    private void setOperatorClickListener(int buttonId, String operator) {
        findViewById(buttonId).setOnClickListener(view -> {
            input += " " + operator + " ";
            display.setText(input);
        });
    }

    private void calculateResult() {
        try {
            String selectedOperation = spinner.getSelectedItem().toString();
            double result = 0;

            if (selectedOperation.contains("Mocnina")) {
                result = Math.pow(Double.parseDouble(input.split(" ")[0]), 2);
            } else if (selectedOperation.contains("Odmocnina")) {
                result = Math.sqrt(Double.parseDouble(input.split(" ")[0]));
            } else if (selectedOperation.contains("Faktoriál")) {
                int num = Integer.parseInt(input);
                result = factorial(num);
            }

            display.setText(String.valueOf(result));
        } catch (Exception e) {
            display.setText("Chyba");
        }
    }

    private int factorial(int n) {
        return (n <= 1) ? 1 : n * factorial(n - 1);
    }
}