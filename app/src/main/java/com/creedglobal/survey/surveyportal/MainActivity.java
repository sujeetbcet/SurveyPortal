package com.creedglobal.survey.surveyportal;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;


import com.creedglobal.survey.surveyportal.infox.Teams;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;
    Profile profile;
    boolean loginStatus = false;
    int id = 1;
    ViewFlipper flipper;
    int res[] = {
            R.drawable.jellyfish,
            R.drawable.koala,
            R.drawable.lighthouse,
            R.drawable.tulip
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_main);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        flipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        if (loginStatus = false) {
            startActivity(new Intent(getApplicationContext(), MainScreen.class));
        } else {

            int width = getWindow().getWindowManager().getDefaultDisplay().getWidth();
            // adding all images in flipper
            for (int i = 0; i < res.length; i++) {
                ImageView image = new ImageView(this);
                image.setImageResource(res[i]);
                flipper.addView(image);
            }
            //set in/out flipping animation
            flipper.setInAnimation(this, android.R.anim.fade_in);
//            flipper.setOutAnimation(this, android.R.anim.fade_out);
            flipper.setAutoStart(true);
            flipper.setFlipInterval(2000);

            // Facebook login integration
            loginButton.setReadPermissions("email", "user_friends");
            callbackManager = CallbackManager.Factory.create();
            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.i("my_info", "Login Success");
                    Log.i("my_info", "userid =>" + loginResult.getAccessToken().getUserId());
                    profile = Profile.getCurrentProfile();
                    String name = profile.getFirstName();
                    Log.i("my_info", "Name :" + name);
                    startActivity(new Intent(getApplicationContext(), MainScreen.class));
                }
                @Override
                public void onCancel() {
                    Log.i("my_info", "Login Cancel");
                }
                @Override
                public void onError(FacebookException error) {
                    Log.i("my_info", "Login Error");
                }
            });
//            End of facebook integration.

            // to print the hash key for facebook purpose
            try {
                PackageInfo info = getPackageManager().getPackageInfo("com.facebook.samples.loginhowto", PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {

            } catch (NoSuchAlgorithmException e) {

            }
            // end of hash key
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }


    public void gotoRegister(View view) {
        startActivity(new Intent(getApplicationContext(), Registration.class));
    }

    public void forgotPassword(View view) {
        startActivity(new Intent(getApplicationContext(), Forgot.class));
    }

    public void gotoHome(View view) {
        startActivity(new Intent(getApplicationContext(), MainScreen.class));
    }
    public void gotoInfox(View view){
        startActivity(new Intent(getApplicationContext(), Teams.class));
    }

}
