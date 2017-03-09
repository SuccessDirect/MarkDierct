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
import android.widget.TextView;

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
    TextView web_url_marcos;


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
        web_url_marcos = (TextView) view.findViewById(R.id.web_marcos_tarjeta);

        web_url_marcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.marcosvaldi.com"));
                startActivity(viewIntent);
            }
        });

        github_url_daniel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/danielioniceanu"));
                startActivity(viewIntent);
            }
        });


        github_url_matias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/jmajyo"));
                startActivity(viewIntent);
            }
        });


        github_url_marcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/markinhos3"));
                startActivity(viewIntent);
            }
        });


        github_url_elias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/eliasmuol"));
                startActivity(viewIntent);
            }
        });


        github_url_antonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/cazallau"));
                startActivity(viewIntent);
            }
        });


        linkedin_url_daniel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/danielioniceanu"));
                startActivity(viewIntent);

            }
        }); linkedin_url_matias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/josematiasalcaidejimenez"));
                startActivity(viewIntent);

            }
        }); linkedin_url_marcos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/marcosvaldi"));
                startActivity(viewIntent);

            }
        }); linkedin_url_elias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/eliasmuol"));
                startActivity(viewIntent);

            }
        }); linkedin_url_antonio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.linkedin.com/in/antonio-jesus-cazalla"));
                startActivity(viewIntent);

            }
        });

        return view;
    }


}
