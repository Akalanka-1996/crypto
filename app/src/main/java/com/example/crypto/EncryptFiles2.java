package com.example.crypto;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.security.crypto.EncryptedFile;
import androidx.security.crypto.MasterKeys;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.crypto.Utils.Encrypter;
import com.google.android.material.button.MaterialButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.crypto.NoSuchPaddingException;

public class EncryptFiles2 extends AppCompatActivity {

    private static final String FILE_NAME_ENC = "download";
    private static final String FILE_NAME_DEC = "download.png";
    Button btn_enc, btn_dec, btn_browse;
    ImageView imageView;
    EditText filePath;
    File myDir;
    File newFile;

    String my_key = "mDxGC8Q4EDDGdcOV";
    String my_spec_key = "clq0LefoQvApP5M2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_files2);


        btn_dec = (MaterialButton) findViewById(R.id.btn_decrypt);
        btn_enc = (MaterialButton) findViewById(R.id.btn_encrypt);
        btn_browse = (MaterialButton) findViewById(R.id.btnbrowse);
        filePath = (EditText) findViewById(R.id.txtFilePath);

        myDir = new File(Environment.getExternalStorageDirectory().toString() + "/saved_images");


        Dexter.withActivity(this)
                .withPermissions(new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                })
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                        btn_dec.setEnabled(true);
                        btn_enc.setEnabled(true);
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        Toast.makeText(EncryptFiles2.this, "You must enable permission", Toast.LENGTH_SHORT).show();

                    }
                }).check();

        btn_dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputFileDec = new File(myDir, FILE_NAME_DEC);
                File encFile = new File(myDir, FILE_NAME_ENC);
                try {
                    Encrypter.decryptToFile(my_key, my_spec_key, new FileInputStream(encFile), new FileOutputStream(outputFileDec));

                    imageView.setImageURI(Uri.fromFile(outputFileDec));

                    // delete file after decrypt

                    outputFileDec.delete();
                    Toast.makeText(EncryptFiles2.this, "Decrypted!", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                } catch (InvalidKeyException e) {
                    e.printStackTrace();
                } catch (InvalidAlgorithmParameterException e) {
                    e.printStackTrace();
                } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_enc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context context = EncryptFiles2.this;


                EncryptedFile encryptedFile = null;
                try {
                    encryptedFile = new EncryptedFile.Builder(
                            newFile,
                            context,
                            my_spec_key,
                            EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
                    ).build();
//                    Toast.makeText(EncryptFiles2.this, newFile.getPath() , Toast.LENGTH_SHORT).show();
                    System.out.println(newFile.getPath());

//                    Toast.makeText(EncryptFiles2.this, encryptedFile.toString() , Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // write to the encrypted file
                try {
                    FileOutputStream encryptedOutputStream = encryptedFile.openFileOutput();
                    Toast.makeText(EncryptFiles2.this, "two", Toast.LENGTH_SHORT).show();
                }  catch (Exception e) {
                    e.printStackTrace();
                }

                // read the encrypted file
                try {
                    FileInputStream encryptedInputStream = encryptedFile.openFileInput();
                    Toast.makeText(EncryptFiles2.this, "three", Toast.LENGTH_SHORT).show();
                }  catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    // file picker

    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        newFile = new File(uri.getPath());
//                        Toast.makeText(this, filePath, Toast.LENGTH_LONG).show();
                        Toast.makeText(EncryptFiles2.this, newFile.getPath(), Toast.LENGTH_LONG).show();
                        filePath.setText(getFileName(uri, getApplicationContext()));
                    }
                }
            }
    );

    public void openFileDialog(View view) {
        Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        data.setType("*/*");
        data = Intent.createChooser(data, "Choose a file");
        activityResultLauncher.launch(data);

    }

    String getFileName(Uri uri, Context context) {
        String res = null;
        if(uri.getScheme().equals("content")){
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if(cursor != null && cursor.moveToFirst()) {
                    res = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));

                }
            } finally {
                cursor.close();
            }
            if (res == null) {
                res = uri.getPath();
                int cutt = res.lastIndexOf('/');
                if(cutt != -1) {
                    res = res.substring(cutt +1);
                }
            }
        }

        return res;
    }


//    private String getRealPathFromURI(Uri contentURI) {
//        String result;
//        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = contentURI.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }
//        return result;
//    }

}