package com.app.ndbarcodescanner.activity;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.ndbarcodescanner.R;
import com.app.ndbarcodescanner.barcode.BarcodeReader;
import com.app.ndbarcodescanner.database.MyAppDatabase;
import com.app.ndbarcodescanner.database.Ticket;
import com.app.ndbarcodescanner.database.TicketList;
import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;


public class ScannerActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    private BarcodeReader barcodeReader;
    private Button btnBarcodeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_fragment);
        btnBarcodeList = findViewById(R.id.btnBarcodeList);
        btnBarcodeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ScannerActivity.this, BarcodeListActivity.class));
            }
        });
        MyAppDatabase.getInstance(this).daoNote().getAllTicket().observe(this, new Observer<List<Ticket>>() {
            @Override
            public void onChanged(@Nullable List<Ticket> notes) {
                if (notes.size() == 0) {
                    MyAppDatabase.getInstance(ScannerActivity.this).daoNote().addTicket(TicketList.getInstance().getListTicket());
                }
            }
        });

    }

    @Override
    public void onScanned(final Barcode barcode) {
        Log.e("asdf", "onScanned: " + barcode.displayValue);
        barcodeReader.playBeep();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (MyAppDatabase.getInstance(ScannerActivity.this).daoNote().isValiedTicket(barcode.displayValue, false)) {
                    MyAppDatabase.getInstance(ScannerActivity.this).daoNote().updateTicket(barcode.displayValue, true);
                    showResult(barcode.displayValue, true);
                } else {
                    showResult(barcode.displayValue, false);
                }

                barcodeReader.onPause();
                Toast.makeText(getApplicationContext(), "Barcode: " + barcode.displayValue, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {
    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {
    }

    @Override
    public void onScanError(String errorMessage) {

    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(getApplicationContext(), "Camera permission denied!", Toast.LENGTH_LONG).show();
        finish();
    }

    private void showResult(String result, boolean b) {
        final Dialog dialog = new Dialog(this);

        //tell the Dialog to use the dialog.xml as it's layout description
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Android Custom Dialog Box");

        TextView txt = (TextView) dialog.findViewById(R.id.txt);
        TextView txtStaus = (TextView) dialog.findViewById(R.id.txtStaus);
        ImageView img = (ImageView) dialog.findViewById(R.id.imgStatus);
        if (b) {
            img.setImageResource(R.drawable.right);
            txtStaus.setText("Enjoy the party...");
            txtStaus.setTextColor(getResources().getColor(R.color.green));
        } else {
            img.setImageResource(R.drawable.cross);
            txtStaus.setText("Ticket it not valied...");
            txtStaus.setTextColor(getResources().getColor(R.color.red));

        }
        txt.setText(result);

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButton);

        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                barcodeReader.onResume();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
