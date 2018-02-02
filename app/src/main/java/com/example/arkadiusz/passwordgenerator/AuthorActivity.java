package com.example.arkadiusz.passwordgenerator;

import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AuthorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);

        //back pressed arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }



    public void onMail(View view) {
        Intent mailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","arek.marcinowski18@gmail.com", null));
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "I saw your app!");
        mailIntent.putExtra(Intent.EXTRA_TEXT, "Hi!\n");
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
        makeToast(R.string.leave);
    }



    private void makeToast(int message) {
        Toast.makeText(AuthorActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}
