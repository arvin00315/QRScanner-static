/*==========================================================
*  A Sample QR Scanner / Barcode Scanner that will read
*  QR code using static image
*
*
*  reference:
*  https://search-codelabs.appspot.com/codelabs/bar-codes#1
* ==========================================================*/


package com.example.arlan.qrscanner;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

public class MainActivity extends AppCompatActivity {

    TextView txtView;
    Button buttonScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("arvinsTag","OnCreate was called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*==============================================================
        *  Setup the Barcode Detector
        *  check if our detector is operational before we use it
        *  If it isn’t, we may have to wait for a download to complete,
        *  or let our users know that they need to find an internet connection
        *===============================================================*/
        BarcodeDetector detector = new BarcodeDetector.Builder(getApplicationContext())
                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
                .build();

        if(!detector.isOperational()){
            txtView = (TextView)findViewById(R.id.txtContent);
            txtView.setText("Could not set up the detector!");
            return; //program will not proceed further
        }


        /*=================
        *  Load the Image
        *==================*/
        ImageView myImageView = (ImageView) findViewById(R.id.imgview);
        Bitmap myBitmap = BitmapFactory.decodeResource(
                getApplicationContext().getResources(),
                R.drawable.sample_image);
        myImageView.setImageBitmap(myBitmap);


        /*====================
        *  Detect the Barcode
        *=====================*/
        Frame frame = new Frame.Builder().setBitmap(myBitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);

        /*====================
        *  Decode the Barcode
        *=====================*/
        Barcode thisCode = barcodes.valueAt(0);
        TextView txtView = (TextView) findViewById(R.id.txtContent);
        txtView.setText(thisCode.rawValue);



    }

}
