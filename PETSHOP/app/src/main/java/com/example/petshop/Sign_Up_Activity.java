package com.example.petshop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Sign_Up_Activity extends Activity {

	private EditText etName, etEmail, etPassword, etConfirmPassword;
	private Button btnSignUp;
	private UserDataSource dataSource;
	private boolean isPasswordVisible = false;



	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_up);

		etName = findViewById(R.id.etName);
		etEmail = findViewById(R.id.etEmail);
		etPassword = findViewById(R.id.etPassword);
		etConfirmPassword = findViewById(R.id.etConfirmPassword);
		btnSignUp = findViewById(R.id.btnSignUp);

		dataSource = new UserDataSource(this);
		dataSource.open();

		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String name = etName.getText().toString();
				String email = etEmail.getText().toString();
				String password = etPassword.getText().toString();
				String confirmPassword = etConfirmPassword.getText().toString();

				if(password.equals(confirmPassword)){
					long id = dataSource.insertUser(name,email,password);
					if(id!=-1){
						Toast.makeText(Sign_Up_Activity.this, "Thêm dữ liệu thành công", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent(Sign_Up_Activity.this, Login_Activity.class);
						startActivity(intent);
					}
					else {
						Toast.makeText(Sign_Up_Activity.this, "Thêm dữ liệu thất bại", Toast.LENGTH_SHORT).show();
					}
				}
				else
					Toast.makeText(Sign_Up_Activity.this, "Password không khớp", Toast.LENGTH_SHORT).show();
			}
		});

	}
	public void showpassword(View view){
		if (isPasswordVisible) {
			// Hide password
			etPassword.setTransformationMethod(new PasswordTransformationMethod());
			etConfirmPassword.setTransformationMethod(new PasswordTransformationMethod());
		} else {
			// Show password
			etPassword.setTransformationMethod(null);
			etConfirmPassword.setTransformationMethod(null);
		}
		isPasswordVisible = !isPasswordVisible; // Toggle the flag
	}
}
	
	