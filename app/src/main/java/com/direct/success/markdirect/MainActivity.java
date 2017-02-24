package com.direct.success.markdirect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.direct.success.markdirect.managers.OfertasApiManager;
import com.direct.success.markdirect.model.Oferta;

import com.direct.success.markdirect.activities.GeneralActivity;

public class MainActivity extends AppCompatActivity {
    private TextView testText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = new Intent(MainActivity.this, GeneralActivity.class);
        startActivity(i);
        testText = (TextView) findViewById(R.id.activity_main___text_test);

        testText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OfertasApiManager ofertasApiManager = new OfertasApiManager();
                ofertasApiManager.setListener(new OfertasApiManager.OfertasApiManagerNewOfertasListener() {
                    @Override
                    public void onNewOferta(Oferta oferta) {
                        testText.setText(oferta.getTittle());
                    }
                });
                ofertasApiManager.newOferta(v.getContext());
            }
        });
    }
}
