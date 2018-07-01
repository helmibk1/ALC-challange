package hbk.com.helmibk.journalapplication.views.user_auth;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import hbk.com.helmibk.journalapplication.MainActivity;
import hbk.com.helmibk.journalapplication.R;
import hbk.com.helmibk.journalapplication.utils.SharePreferenceKeys;
import hbk.com.helmibk.journalapplication.utils.SigninMode;

public class UserAuthActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_auth);

        findViewById(R.id.google_signin_button).setOnClickListener(this);


        if(FirebaseAuth.getInstance().getCurrentUser() != null || PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean(SharePreferenceKeys.USER_SIGNED_IN, false)) {
            startActivity(new Intent(this, MainActivity.class));

            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.google_signin_button){
            startActivity(new Intent(UserAuthActivity.this, GoogleSigninActivity.class));

        }

    }
}
