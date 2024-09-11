package com.example.calculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;



import javax.xml.xpath.XPathExpression;

public class MainActivity extends AppCompatActivity {

    Button num0,num1,num2,num3,num4,num5,num6,num7,num8,num9,dot;
    Button rem,sum,div,sub,multi,equal,clear;
    ImageView deleteChar;
    StringBuilder stringBuilder =new StringBuilder();

    TextView result,input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num0=findViewById(R.id.num0);
        num1=findViewById(R.id.num1);
        num2=findViewById(R.id.num2);
        num3=findViewById(R.id.num3);
        num4=findViewById(R.id.num4);
        num5=findViewById(R.id.num5);
        num6=findViewById(R.id.num6);
        num7=findViewById(R.id.num7);
        num8=findViewById(R.id.num8);
        num9=findViewById(R.id.num9);
        dot=findViewById(R.id.dot);

        rem=findViewById(R.id.rem);
        sub=findViewById(R.id.sub);
        sum=findViewById(R.id.sum);
        div=findViewById(R.id.div);
        multi=findViewById(R.id.multi);
        equal=findViewById(R.id.equal);
        deleteChar=findViewById(R.id.deleteChar);
        clear=findViewById(R.id.clear);

        result=findViewById(R.id.result);
        input=findViewById(R.id.input);

        ArrayList<Button> btn = new ArrayList<Button>();
        btn.add(num0);
        btn.add(num1);
        btn.add(num2);
        btn.add(num3);
        btn.add(num4);
        btn.add(num5);
        btn.add(num6);
        btn.add(num7);
        btn.add(num8);
        btn.add(num9);
        btn.add(dot);

        ArrayList<Button> btn2 = new ArrayList<Button>();
        btn2.add(rem);
        btn2.add(div);
        btn2.add(multi);
        btn2.add(sub);
        btn2.add(sum);



        for (Button i : btn){
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stringBuilder.append(i.getText().toString()+"");
                    input.setText(stringBuilder);
                    input.setTextColor(Color.WHITE);

                    result.setText(FinalResult(stringBuilder.toString()));
                    result.setVisibility(View.VISIBLE);
                }
            });
        }

        for (Button i2 : btn2){
            i2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    stringBuilder.append(i2.getText().toString()+"");
                    input.setText(stringBuilder);
                    input.setTextColor(Color.WHITE);
                    result.setVisibility(View.INVISIBLE);
                }
            });
        }


        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(stringBuilder.length()==0)) {
                    String finRes = FinalResult(stringBuilder.toString());
                    input.setText(finRes);
                    input.setTextColor(Color.GREEN);
                    stringBuilder.delete(0, stringBuilder.length());
                    stringBuilder.append(finRes);

                    result.setVisibility(View.INVISIBLE);
                }
            }
        });

        deleteChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(stringBuilder.length()==0)){
                    stringBuilder.deleteCharAt(stringBuilder.length()-1);
                    input.setText(stringBuilder);
                    input.setTextColor(Color.WHITE);
                    result.setText(FinalResult(stringBuilder.toString()));
                    if ((stringBuilder.length()==0)){
                        result.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringBuilder.delete(0,stringBuilder.length());
                input.setText("0");
                input.setTextColor(Color.WHITE);
                result.setVisibility(View.INVISIBLE);
            }
        });
    }

    String FinalResult(String data){
        try {
            String st = stringBuilder.toString();
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String FinRes = context.evaluateString(scriptable, st, "Javascript", 1, null).toString();
            return FinRes;
        }catch (Exception e){
            return "";
        }
    }
}