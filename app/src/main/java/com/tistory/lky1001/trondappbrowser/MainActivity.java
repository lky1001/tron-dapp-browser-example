package com.tistory.lky1001.trondappbrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.editText)
    EditText privateKeyEditText;

    private CustomPreference customPreference;
    private KeyStore keyStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        customPreference = new CustomPreference(this);
        keyStore = new KeyStoreApi23Impl(customPreference);

        keyStore.init();
        keyStore.createKeys(CustomPreference.ALIAS_ADDRESS_KEY);

        if (!TextUtils.isEmpty(customPreference.getPk())) {
            startActivity(new Intent(this, WebViewActivity.class));
            finish();
        }
    }

    @OnClick(R.id.button)
    public void onSaveClick() {
        String pk = privateKeyEditText.getText().toString();

        if (TextUtils.isEmpty(pk)) {
            Toast.makeText(this, "Private key is required.", Toast.LENGTH_SHORT).show();
        } else {
            customPreference.setPk(keyStore.encryptString(pk, CustomPreference.ALIAS_ADDRESS_KEY));
            startActivity(new Intent(this, WebViewActivity.class));
            finish();
        }
    }
}
