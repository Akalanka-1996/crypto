package com.example.crypto;

import androidx.appcompat.app.AppCompatActivity;

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

public class DecryptText extends AppCompatActivity {

    EditText inputPassword, inputText;
    TextView outputText;
    Button decryptBtn, copyBtn;
    String outputString;
    String AES = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decrypt_text);

        inputText = (EditText) findViewById(R.id.txtPlain);
        outputText = (TextView) findViewById(R.id.txtOuptput);

        decryptBtn = (MaterialButton) findViewById(R.id.btnDecrypt);
        copyBtn = (MaterialButton) findViewById(R.id.btnCopy);

        decryptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    outputString = decrypt(inputText.getText().toString(), inputPassword.getText().toString());
                } catch (Exception e) {
                    Toast.makeText(DecryptText.this, "Wrong Key!", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                outputText.setText(outputString);
            }
        });


    }

    private String decrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(Data, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;

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