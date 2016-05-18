package com.example.chaitanya.carsdetail;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //EditText
    EditText editTextUsername, editTextPassword;

    //TextInputLayout
    TextInputLayout textInputLayoutUsername, textInputLayoutPassword;

    //Button
    Button buttonLogin;

    //ProgressDialog
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.input_layout_username);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        buttonLogin = (Button) findViewById(R.id.btn_login);

        //TextInputLayout change listeners
        editTextUsername.addTextChangedListener(new MyTextWatcher(editTextUsername));
        editTextPassword.addTextChangedListener(new MyTextWatcher(editTextPassword));

        //Button onClick listener
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    /**
     * validating form
     */
    private void submitForm() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading..");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setIndeterminate(false);
        progressDialog.setProgress(0);
        final int totalTime = 100;
        final Thread thread = new Thread() {
            @Override
            public void run() {
                int jumpTime = 0;
                while (jumpTime < totalTime) {
                    try {
                        sleep(200);
                        jumpTime += 5;
                        progressDialog.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }

        /**if the username and password are not empty then, when user clicks on Login button, the next activity will start
         * which will display all the cars in a listview
         */
        Intent intent = new Intent(MainActivity.this, CarsListview.class);
        startActivity(intent);

    }

    /**
     * validating username
     */
    private boolean validateUsername() {
        if (editTextUsername.getText().toString().trim().isEmpty()) {
            textInputLayoutUsername.setError("Please enter Username!!!");
            requestFocus(editTextUsername);
            return false;
        } else {
            textInputLayoutUsername.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * validating password
     */
    private boolean validatePassword() {
        if (editTextPassword.getText().toString().trim().isEmpty()) {
            textInputLayoutPassword.setError("Please enter Password!!!");
            requestFocus(editTextPassword);
            return false;
        } else {
            textInputLayoutPassword.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * requestFocus method
     */
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    /**
     * class for validating username and password
     */
    private class MyTextWatcher implements TextWatcher {

        private View view;
        private MyTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            switch (view.getId()) {
                case R.id.username:
                    validateUsername();
                    break;
                case R.id.password:
                    validatePassword();
                    break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        editTextUsername.setText("");
        editTextPassword.setText("");
    }
}
