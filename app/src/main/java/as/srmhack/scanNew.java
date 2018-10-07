package as.srmhack;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.Toast;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class scanNew extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {

    BarcodeReader barcodeReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_new);

        barcodeReader=(BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.qrCodeScanner);
        if(checkSelfPermission(android.Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[] {Manifest.permission.CAMERA},1);
        }
    }

    @Override
    public void onScanned(Barcode barcode) {
        barcodeReader.playBeep();
        Intent intent = new Intent(scanNew.this, InfoActivity.class);
        String s=barcode.displayValue;
        intent.putExtra("code", s);
        startActivity(intent);

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
        Toast.makeText(this,"Camera permission required",Toast.LENGTH_LONG ).show();
    }
}
