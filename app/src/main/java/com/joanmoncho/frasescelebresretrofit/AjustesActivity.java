package com.joanmoncho.frasescelebresretrofit;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.joanmoncho.frasescelebresretrofit.fragments.FragmentAjustes;

public class AjustesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, new FragmentAjustes())
                .commit();
    }
}
