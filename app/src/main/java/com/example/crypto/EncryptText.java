package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;


public class EncryptText extends AppCompatActivity {

    EditText inputPassword, inputText;
    TextView outputText;
    Button encryptBtn, copyBtn;
    String outputString;
    String AES = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt_text);
        inputPassword = (EditText) findViewById(R.id.txtKey);
        inputText = (EditText) findViewById(R.id.txtPlain);
        outputText = (TextView) findViewById(R.id.txtOuptput);

        encryptBtn = (MaterialButton) findViewById(R.id.btnEncrypt);
        copyBtn = (MaterialButton) findViewById(R.id.btnCopy);

        encryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outputString = encrypt(inputText.getText().toString(), inputPassword.getText().toString());
                    outputText.setText(outputString);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("EncryptedText", outputText.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(EncryptText.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private String decrypt(String outputString, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(outputString, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;

    }

    private String encrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }


}