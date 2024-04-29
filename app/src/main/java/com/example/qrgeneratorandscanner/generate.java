package com.example.qrgeneratorandscanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Objects;

public class generate extends AppCompatActivity {


    private ImageView setQR;
    private EditText inpStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        Button genQR = findViewById(R.id.genQRbut);
        setQR = findViewById(R.id.QRshow);
        inpStr = findViewById(R.id.getinpQR);
        genQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inp = inpStr.getText().toString();
                if(inp.isEmpty())
                    Toast.makeText(generate.this, "Please enter some data!", Toast.LENGTH_SHORT).show();
                else
                {
                    try
                    {
                        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                        BitMatrix bitMatrix = multiFormatWriter.encode(inp, BarcodeFormat.QR_CODE,250,250);
                        BarcodeEncoder encoder = new BarcodeEncoder();
                        Bitmap bitmap = encoder.createBitmap(bitMatrix);
                        setQR.setImageBitmap(bitmap);
                        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        inputMethodManager.hideSoftInputFromWindow(inpStr.getApplicationWindowToken(),0);
                    } catch (WriterException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        });
    }
}