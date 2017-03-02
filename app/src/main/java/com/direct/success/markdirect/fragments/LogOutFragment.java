package com.direct.success.markdirect.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.direct.success.markdirect.R;
import com.direct.success.markdirect.activities.LoginActivity;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogOutFragment extends Fragment {

    private LoginButton loginButton;

    public LogOutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_log_out, container, false);

        loginButton = (LoginButton) view.findViewById(R.id.loo_out_login_button_);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences prefs =
                        v.getContext().getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("Token", null);
                editor.commit();
                LoginManager.getInstance().logOut();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);

            }
        });

        return view;
    }

}
