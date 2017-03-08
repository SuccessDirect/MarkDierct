package com.direct.success.markdirect.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.direct.success.markdirect.R;


public class AboutUsFragment extends Fragment {

    ImageView linkedin_url_daniel;
    ImageView linkedin_url_matias;
    ImageView linkedin_url_marcos;
    ImageView linkedin_url_elias;
    ImageView linkedin_url_antonio;
    ImageView github_url_daniel;
    ImageView github_url_matias;
    ImageView github_url_marcos;
    ImageView github_url_elias;
    ImageView github_url_antonio;


    public AboutUsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_about_us, container, false);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Desarrolladores");

        linkedin_url_daniel= (ImageView) view.findViewById(R.id.card_view_linkedin_daniel);
        linkedin_url_matias= (ImageView) view.findViewById(R.id.card_view_linkedin_matias);
        linkedin_url_marcos= (ImageView) view.findViewById(R.id.card_view_linkedin_marcos);
        linkedin_url_elias= (ImageView) view.findViewById(R.id.card_view_linkedin_elias);
        linkedin_url_antonio= (ImageView) view.findViewById(R.id.card_view_linkedin_antonio);
        github_url_daniel= (ImageView) view.findViewById(R.id.card_view_github_daniel);
        github_url_matias= (ImageView) view.findViewById(R.id.card_view_github_matias);
        github_url_marcos= (ImageView) view.findViewById(R.id.card_view_github_marcos);
        github_url_elias= (ImageView) view.findViewById(R.id.card_view_github_elias);
        github_url_antonio= (ImageView) view.findViewById(R.id.card_view_github_antonio);

        github_url_daniel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.git_daniel)));
                startActivity(viewIntent);
            }
        });


        github_url_matias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.git_matias)));
                startActivity(viewIntent);
            }
        });


        github_url_marcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.git_marcos)));
                startActivity(viewIntent);
            }
        });


        github_url_elias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.git_elias)));
                startActivity(viewIntent);
            }
        });


        github_url_antonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.git_antonio)));
                startActivity(viewIntent);
            }
        });





        linkedin_url_daniel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.linkedin_daniel)));
                startActivity(viewIntent);

            }
        }); linkedin_url_matias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.linkedin_matias)));
                startActivity(viewIntent);

            }
        }); linkedin_url_marcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.linkedin_marcos)));
                startActivity(viewIntent);

            }
        }); linkedin_url_elias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.linkedin_elias)));
                startActivity(viewIntent);

            }
        }); linkedin_url_antonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse(getString(R.string.linkedin_antonio)));
                startActivity(viewIntent);

            }
        });

        return view;
    }


}
