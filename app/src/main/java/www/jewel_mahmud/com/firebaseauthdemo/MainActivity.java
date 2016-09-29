package www.jewel_mahmud.com.firebaseauthdemo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        //assign variables.
        register = (Button) findViewById(R.id.buttonRegister);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);

        register.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonRegister:
                registerUser();
                break;


            case R.id.textViewSignin:
                //open signin/login activity.
                break;
        }
    }

    private void showToast(CharSequence msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();


        progressDialog = new ProgressDialog(this);

        if(TextUtils.isEmpty(email)){
            //email is empty.
            showToast("Please enter email address.");
            //stoping the function.
            return;
        }

        if(TextUtils.isEmpty(password)){
            //password is empty.
            showToast("Please enter password.");
            //stoping the function.
            return;
        }

        //if valid and perfect.
        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    //successfully done registration
                    showToast("Registration Successfully.");
                }else{
                    showToast("Please try again.");
                }
            }
        });






    }
}
