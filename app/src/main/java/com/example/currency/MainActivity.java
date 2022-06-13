package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class Money {
    Money(String code, String symbol ,double ratio){
        this.code = code;
        this.symbol = symbol;
        this.ratio = ratio;
    }
    String code;
    String symbol;
    double ratio;
}

public class MainActivity extends AppCompatActivity {

    double srcRatio = 1, desRatio = 1;
    String srcCode = "USD", desCode = "USD";

    List<Money> listMoney = new ArrayList<>();
    List<String> listCode = new ArrayList<>();

    TextView srcIconText, srcDisplayText,
             desIconText, desDisplayText,
             ratioText;

    Spinner srcSpinner, desSpinner;

    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6,
           btn7, btn8, btn9, btnCE, btnX, btnDot;

    private void addText(String s){
        String txt = srcDisplayText.getText().toString();

        if(!txt.equals("0")) txt += s;
        else txt = s;

        updateText(txt);
    }

    private void updateText(String s){
        long valSrc = Long.parseLong(s);
        double valDes = valSrc / srcRatio * desRatio;

        srcDisplayText.setText("" + valSrc);
        desDisplayText.setText(String.format("%.2f", valDes));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        srcIconText = findViewById(R.id.srcIconText);
        srcDisplayText = findViewById(R.id.srcDisplayText);
        desIconText = findViewById(R.id.desIconText);
        desDisplayText = findViewById(R.id.desDisplayText);
        ratioText = findViewById(R.id.ratioText);

        srcSpinner = findViewById(R.id.srcSpinner);
        desSpinner = findViewById(R.id.desSpinner);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnX = findViewById(R.id.btnX);
        btnCE = findViewById(R.id.btnCE);
        btnDot = findViewById(R.id.btnDot);

        listMoney.add(new Money("USD", "$", 1));
        listMoney.add(new Money("AUD", "$", 1.4178));
        listMoney.add(new Money("CNY", "¥", 6.7081));
        listMoney.add(new Money("EUR", "€", 0.9507));
        listMoney.add(new Money("HKD", "HK$", 7.849));
        listMoney.add(new Money("JPY", "¥", 134.42));
        listMoney.add(new Money("SGD", "S$", 1.3876));
        listMoney.add(new Money("ZAR", "R", 15.83));
        listMoney.add(new Money("TWD", "NT$", 29.657));
        listMoney.add(new Money("THB", "฿", 34.72));
        listMoney.add(new Money("GBP", "£", 0.81));
        listMoney.add(new Money("VND", "đ", 23177));

        listCode.add("USD");
        listCode.add("AUD");
        listCode.add("CNY");
        listCode.add("EUR");
        listCode.add("HKD");
        listCode.add("JPY");
        listCode.add("SGD");
        listCode.add("ZAR");
        listCode.add("TWD");
        listCode.add("THB");
        listCode.add("GBP");
        listCode.add("VND");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listCode);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);

        srcSpinner.setAdapter(adapter);
        srcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String code = srcSpinner.getSelectedItem().toString();
                for(int j = 0; j < listMoney.size(); j++){
                    if(code.equals(listMoney.get(j).code)){
                        srcIconText.setText(listMoney.get(j).symbol);
                        srcRatio = listMoney.get(j).ratio;
                        srcCode = code;
                        updateText(srcDisplayText.getText().toString());
                        ratioText.setText("Tỷ giá: 1 " + srcCode + " = " + desRatio/srcRatio + " " + desCode);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        desSpinner.setAdapter(adapter);
        desSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String code = desSpinner.getSelectedItem().toString();
                for(int j = 0; j < listMoney.size(); j++){
                    if(code.equals(listMoney.get(j).code)){
                        desIconText.setText(listMoney.get(j).symbol);
                        desRatio = listMoney.get(j).ratio;
                        desCode = code;
                        updateText(srcDisplayText.getText().toString());
                        ratioText.setText("Tỷ giá: 1 " + srcCode + " = " + desRatio/srcRatio + " " + desCode);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        btn0.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("0");
        });

        btn1.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("1");
        });

        btn2.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("2");
        });

        btn3.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("3");
        });

        btn4.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("4");
        });

        btn5.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("5");
        });

        btn6.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("6");
        });

        btn7.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("7");
        });

        btn8.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("8");
        });

        btn9.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            addText("9");
        });

        btnX.setOnClickListener((v)->{
            String txt = srcDisplayText.getText().toString();
            if(txt.length() == 1){
                updateText("0");
            }
            else{
                txt = txt.substring(0, txt.length()-1);
                updateText(txt);
            }
        });

        btnCE.setOnClickListener((v)->{
            srcDisplayText.setText("0");
            desDisplayText.setText("0");
            srcDisplayText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 72f);
            desDisplayText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 72f);
        });

    }
}