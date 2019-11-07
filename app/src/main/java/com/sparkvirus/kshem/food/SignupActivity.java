package com.sparkvirus.kshem.food;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sparkvirus.kshem.food.R;

/**
 * Created by kshem on 3/19/17.
 */

public class SignupActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    EditText repassword;
    Button signup;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

     //instanciating firebase auth
        mAuth = FirebaseAuth.getInstance();

        //get current user
        getCurrentSer();

        //method to reference id
      collectIds();
      //method to sign up user
      signUpUser();

    }

    //fuction to get current user
    public void getCurrentSer()
    {
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
    }


    //functoion to collect Ids
    public void collectIds()
    {
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        repassword = (EditText) findViewById(R.id.input_reEnterPassword);
        signup = (Button) findViewById(R.id.btn_signup);
    }

    //funtion to sign up user
    public void signUpUser()
    {
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    email.setError("Required");
                }else if(TextUtils.isEmpty(password.getText().toString())){
                    password.setError("Required");
                }

                if(!password.getText().toString().equals(repassword.getText().toString())){
                    Toast.makeText(SignupActivity.this, "The passwords don't match", Toast.LENGTH_SHORT).show();

                }else{
                    final ProgressDialog pd = new ProgressDialog(SignupActivity.this);
                    pd.setMessage("Please Wait");
                    pd.show();
                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(SignupActivity.this, R.string.auth_failed,
                                                Toast.LENGTH_SHORT).show();
                                    }else{

                                        Intent intent = new Intent(SignupActivity.this, InfoAcivity.class);
                                        startActivity(intent);

                                    }
                                    pd.hide();
                                }
                            });
                }

            }
        });
    }


}
