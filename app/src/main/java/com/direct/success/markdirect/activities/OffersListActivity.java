package com.direct.success.markdirect.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.direct.success.markdirect.R;

public class OffersListActivity extends AppCompatActivity {

    private RecyclerView offersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_list);

    }
}
