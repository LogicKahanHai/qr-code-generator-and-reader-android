package com.example.qrgeneratorandscanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1 = findViewById(R.id.GENERATEQR);
        Button b2 = findViewById(R.id.ScanQR);

        b2.setOnClickListener(V-> ScanCode());
        b1.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this,generate.class);
            startActivity(intent);
        });
    }

    private void ScanCode()
    {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on.");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }
    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents()!=null)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Scan Results");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).show();
        }
    });

}