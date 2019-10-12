package pciu.cse011.helloworld.dalyshoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText pass;
    private Button   btnlogin;
    private TextView signup;

    private FirebaseAuth mauth;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mauth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        email = findViewById(R.id.email_login);
        pass = findViewById(R.id.password_login);
        btnlogin = findViewById(R.id.btn_login);
        signup = findViewById(R.id.signup_txt);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String memail = email.getText().toString().trim();
            String mpass = pass.getText().toString().trim();
            if (TextUtils.isEmpty(memail)){
                email.setError("Required Field..");
                return;
            }
            if (TextUtils.isEmpty(mpass)){
                pass.setError("Required Field..");
                return;

            }
             mDialog.setMessage("Processing..");
             mDialog.show();
             mauth.signInWithEmailAndPassword(memail,mpass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                 @Override
                 public void onComplete(@NonNull Task<AuthResult> task) {
                     if (task.isSuccessful()){
                         startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                         Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                         mDialog.dismiss();
                     }
                     else {
                         Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                         mDialog.dismiss();
                     }
                 }
             });
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
            }
        });

    }
}
