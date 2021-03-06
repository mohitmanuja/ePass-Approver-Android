package com.anumati.approver.qrCodeCheck.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.anumati.approver.BuildConfig;
import com.anumati.approver.activities.BaseActivity;
import com.anumati.approver.onBoarding.activities.OnBoardingActivity;
import com.anumati.approver.qrCodeCheck.camera.BarcodeGraphicTracker;
import com.anumati.approver.qrCodeCheck.camera.BarcodeTrackerFactory;
import com.anumati.approver.qrCodeCheck.camera.GraphicOverlay;
import com.anumati.approver.R;
import com.anumati.approver.qrCodeCheck.camera.BarcodeGraphic;
import com.anumati.approver.databinding.ActivityQrScannerBinding;
import com.anumati.approver.onBoarding.models.TokenVerifyResponse;
import com.anumati.approver.qrCodeCheck.repos.TokenRepo;
import com.anumati.approver.utils.CommonUtils;
import com.anumati.approver.qrCodeCheck.viewmodels.TokenViewModelFactory;
import com.anumati.approver.qrCodeCheck.viewmodels.VerifyTokenViewModel;
import com.anumati.approver.utils.SharedPrefHelper;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.MultiProcessor;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

/**
 * @author googlesamples - android-vision
 * Activity for the multi-tracker app.  This app detects faces and barcodes with the rear facing
 * camera, and draws overlay graphics to indicate the position, size, and ID of each face and
 * barcode.
 */
public final class QRCodeScannerActivity extends BaseActivity implements BarcodeGraphicTracker.BarcodeUpdateListener {
    private static final String TAG = "ePass Approver";

    // permission request codes need to be < 256
    private static final int RC_HANDLE_CAMERA_PERM = 2;

    private CameraSource mCameraSource = null;
    private ActivityQrScannerBinding binding;
    private GraphicOverlay<BarcodeGraphic> mGraphicOverlay;
    private GestureDetector gestureDetector;
    private VerifyTokenViewModel verifyTokenViewModel;
    private boolean requestInProgress;
    AlertDialog.Builder alertDialogBuilder;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_scanner);
        mGraphicOverlay = findViewById(R.id.graphicOverlay);
        gestureDetector = new GestureDetector(this, new CaptureGestureListener());
        TokenViewModelFactory tokenViewModelFactory = new TokenViewModelFactory(new TokenRepo());
        verifyTokenViewModel = new ViewModelProvider(this, tokenViewModelFactory).get(VerifyTokenViewModel.class);

        setScreenName("QR Code Scanner");
        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc == PackageManager.PERMISSION_GRANTED) {
            createCameraSource();
        } else {
            requestCameraPermission();
        }
        setObservers();
        setListeners();
    }

    void setListeners(){

        binding.enterManualCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QRCodeScannerActivity.this, QRVerificationActivity.class));
            }
        });
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefHelper.INSTANCE.clearData(QRCodeScannerActivity.this);
                Intent intent = new Intent(QRCodeScannerActivity.this, OnBoardingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

            }
        });
    }

    /**
     * Handles the requesting of the camera permission.  This includes
     * showing a "Snackbar" message of why the permission is needed then
     * sending the request.
     */


    private void requestCameraPermission() {
        Log.w(TAG, "Camera permission is not granted. Requesting permission");

        final String[] permissions = new String[]{Manifest.permission.CAMERA};

        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.CAMERA)) {
            ActivityCompat.requestPermissions(this, permissions, RC_HANDLE_CAMERA_PERM);
            return;
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.permission_required)
                .setMessage(R.string.no_camera_permission)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(QRCodeScannerActivity.this, permissions,
                                RC_HANDLE_CAMERA_PERM);
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    /**
     * Creates and starts the camera.  Note that this uses a higher resolution in comparison
     * to other detection examples to enable the barcode detector to detect small barcodes
     * at long distances.
     */
    private void createCameraSource() {


        Context context = getApplicationContext();


        // A barcode detector is created to track barcodes.  An associated multi-processor instance
        // is set to receive the barcode detection results, track the barcodes, and maintain
        // graphics for each barcode on screen.  The factory is used by the multi-processor to
        // create a separate tracker instance for each barcode.
        BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).build();
        BarcodeTrackerFactory barcodeFactory = new BarcodeTrackerFactory(mGraphicOverlay, this);
        barcodeDetector.setProcessor(
                new MultiProcessor.Builder<>(barcodeFactory).build());


        // A multi-detector groups the two detectors together as one detector.  All images received
        // by this detector from the camera will be sent to each of the underlying detectors, which
        // will each do face and barcode detection, respectively.  The detection results from each
        // are then sent to associated tracker instances which maintain per-item graphics on the
        // screen.


        if (!barcodeDetector.isOperational()) {
            // Note: The first time that an app using the barcode or face API is installed on a
            // device, GMS will download a native libraries to the device in order to do detection.
            // Usually this completes before the app is run for the first time.  But if that
            // download has not yet completed, then the above call will not detect any barcodes
            // and/or faces.
            //
            // isOperational() can be used to check if the required native libraries are currently
            // available.  The detectors will automatically become operational once the library
            // downloads complete on device.
            Log.w(TAG, "Detector dependencies are not yet available.");

            // Check for low storage.  If there is low storage, the native library will not be
            // downloaded, so detection will not become operational.
            IntentFilter lowStorageFilter = new IntentFilter(Intent.ACTION_DEVICE_STORAGE_LOW);
            boolean hasLowStorage = registerReceiver(null, lowStorageFilter) != null;

            if (hasLowStorage) {
                Toast.makeText(this, R.string.low_storage_error, Toast.LENGTH_LONG).show();
                Log.w(TAG, getString(R.string.low_storage_error));
            }
        }

        // Creates and starts the camera.  Note that this uses a higher resolution in comparison
        // to other detection examples to enable the barcode detector to detect small barcodes
        // at long distances.
        mCameraSource = new CameraSource.Builder(getApplicationContext(), barcodeDetector)
                .setFacing(CameraSource.CAMERA_FACING_BACK)
                .setRequestedPreviewSize(1600, 1024)
                .setAutoFocusEnabled(true)
                .setRequestedFps(15.0f)
                .build();


    }

    /**
     * Restarts the camera.
     */
    @Override
    protected void onResume() {
        super.onResume();
        startCameraSource();
    }

    /**
     * Stops the camera.
     */
    @Override
    protected void onPause() {
        super.onPause();
        binding.preview.stop();
    }


    /**
     * Releases the resources associated with the camera source, the associated detectors, and the
     * rest of the processing pipeline.
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCameraSource != null) {
            mCameraSource.release();
        }
    }


    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either {@link PackageManager#PERMISSION_GRANTED}
     *                     or {@link PackageManager#PERMISSION_DENIED}. Never null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, final String[] permissions, int[] grantResults) {
        if (requestCode != RC_HANDLE_CAMERA_PERM) {
            Log.d(TAG, "Got unexpected permission result: " + requestCode);
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            return;
        }

        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "Camera permission granted - initialize the camera source");
            // we have permission, so create the camerasource
            createCameraSource();
            return;
        }

        Log.e(TAG, "Permission not granted: results len = " + grantResults.length +
                " Result code = " + (grantResults.length > 0 ? grantResults[0] : "(empty)"));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.permission_required)
                .setMessage(R.string.no_camera_permission)
                .setCancelable(true)
                .setNegativeButton(R.string.not_now, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        ActivityCompat.requestPermissions(QRCodeScannerActivity.this, permissions,
                                RC_HANDLE_CAMERA_PERM);
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Starts or restarts the camera source, if it exists.  If the camera source doesn't exist yet
     * (e.g., because onResume was called before the camera source was created), this will be called
     * again when the camera source is created.
     */
    private void startCameraSource() {

        // check that the device has play services available.
        int code = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(
                getApplicationContext());
        if (code != ConnectionResult.SUCCESS) {
            alertDialogBuilder = new AlertDialog.Builder(
                    QRCodeScannerActivity.this);

            alertDialogBuilder.setTitle(R.string.unable_to_open_scanner);
            alertDialogBuilder
                    .setMessage(R.string.your_phone_does_not_support_scan_feature)
                    .setCancelable(true)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }

        if (mCameraSource != null) {
            try {
                binding.preview.start(mCameraSource, mGraphicOverlay);
            } catch (IOException e) {
                Log.e(TAG, "Unable to start camera source.", e);
                mCameraSource.release();
                mCameraSource = null;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        boolean c = gestureDetector.onTouchEvent(e);
        return c || super.onTouchEvent(e);
    }

    private boolean onTap(float x, float y) {

        int rc = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (rc != PackageManager.PERMISSION_GRANTED) {
            requestCameraPermission();
            return false;
        }
        BarcodeGraphic graphic = mGraphicOverlay.getGraphicAtLocation(x,y);
        Barcode barcode = null;
        if (graphic != null) {
            barcode = graphic.getBarcode();
            if (barcode != null) {
                requestInProgress = true;
                String rawValue = barcode.rawValue;
                validateQrCode(rawValue);

            } else if (BuildConfig.DEBUG) {
                Log.d(TAG, "barcode data is null");
            }
        } else if (BuildConfig.DEBUG) {
            Log.d(TAG, "no barcode detected");
        }
        return barcode != null;
    }


    @Override
    public void onBarcodeDetected(final Barcode barcode) {
        runOnUiThread(new Runnable() {
            public void run() {
                validateQrCode(barcode.rawValue);
            }
        });
    }

    private void validateQrCode(String rawValue) {
        if (CommonUtils.isNull(rawValue)) {
            Toast.makeText(this, R.string.invalid_qr_code, Toast.LENGTH_SHORT).show();
            return;
        }
        if (!requestInProgress) {
            requestInProgress = true;
            verifyTokenViewModel.fetchTokenResponse(rawValue, this);
        }
    }


    private class CaptureGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            return onTap(e.getRawX(), e.getRawY()) || super.onSingleTapConfirmed(e);
        }
    }

    private void setObservers() {
        alertDialogBuilder = new AlertDialog.Builder(
                QRCodeScannerActivity.this);

        verifyTokenViewModel.getUpdateScreenLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.preview.stop();
                alertDialogBuilder.setTitle(getString(R.string.request_failed));
                alertDialogBuilder
                        .setMessage(s)
                        .setCancelable(false)
                        .setPositiveButton(getString(R.string.try_again), new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                                startCameraSource();
                                requestInProgress = false;
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });

        verifyTokenViewModel.getshowToastLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                requestInProgress = false;
                Toast.makeText(QRCodeScannerActivity.this, s, Toast.LENGTH_LONG).show();
            }
        });
        verifyTokenViewModel.getLoadingScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgressDialog(getString(R.string.processing));
                } else {
                    dismissProgressDialog();
                }
            }
        });
        verifyTokenViewModel.getTokenResponseLiveData().observe(this, new Observer<TokenVerifyResponse>() {
            @Override
            public void onChanged(TokenVerifyResponse tokenVerifyResponse) {
                requestInProgress = false;
                Intent intentNew = new Intent(QRCodeScannerActivity.this, QRStatusActivity.class);
                intentNew.putExtra(CommonUtils.DATA,tokenVerifyResponse);
                startActivity(intentNew);
            }
        });

    }

}
