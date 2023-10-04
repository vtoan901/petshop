
	 
	/*
	 *	This content is generated from the API File Info.
	 *	(Alt+Shift+Ctrl+I).
	 *
	 *	@desc 		
	 *	@file 		login
	 *	@date 		Friday 15th of September 2023 08:37:47 AM
	 *	@title 		Page 1
	 *	@author 	
	 *	@keywords 	
	 *	@generator 	Export Kit v1.3.figma
	 *
	 */


package com.example.petshop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

	public class Login_Activity extends Activity {

	

	private static final int ADD_ITEM_REQUEST_CODE = 1;
	private EditText etEmail, etPassword;
	private CheckBox checkBox;
	private Button btnLogin;
	private UserDataSource dataSource;
	private boolean isPasswordVisible = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		etEmail = findViewById(R.id.etEmail);
		etPassword = findViewById(R.id.etPassword);
		btnLogin = findViewById(R.id.btnLogin);
		checkBox = findViewById(R.id.saveLoginCheckBox);

		dataSource = new UserDataSource(this);
		dataSource.open();
		SharedPreferences sharedPreferences = getSharedPreferences("loginfile", MODE_PRIVATE);

		// Kiểm tra xem đã lưu thông tin đăng nhập trước đó hay không
		boolean remember = sharedPreferences.getBoolean("remember", false);
		if (remember) {
			String savedEmail = sharedPreferences.getString("email", "");
			String savedPassword = sharedPreferences.getString("password", "");

			etEmail.setText(savedEmail);
			etPassword.setText(savedPassword);
			checkBox.setChecked(true);
		} else {
			// Nếu không có thông tin đăng nhập đã lưu, đảm bảo là EditText trống
			etEmail.setText("");
			etPassword.setText("");
		}
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String email = etEmail.getText().toString();
				String password = etPassword.getText().toString();

				boolean isAuthenticated = dataSource.loginUser(email, password);
				if (isAuthenticated) {
					Toast.makeText(Login_Activity.this, "Login thành công", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Login_Activity.this, dog_activity.class);
					startActivity(intent);

				} else {
					Toast.makeText(Login_Activity.this, "Sai email hoặc password", Toast.LENGTH_SHORT).show();
				}
				if (checkBox.isChecked()) {
					// Lưu thông tin đăng nhập
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("email", email);
					editor.putString("password", password);
					editor.putBoolean("remember", true);
					editor.apply();
				} else {
					// Xóa thông tin đăng nhập đã lưu
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.clear();
					editor.apply();
				}
			}
		});

	
	}
	public void btnSign_In(View view)
	{
		Intent intent = new Intent(Login_Activity.this, Sign_Up_Activity.class);
		startActivityForResult(intent, ADD_ITEM_REQUEST_CODE);

	}
		public void showpassword(View view){
			if (isPasswordVisible) {
				// Hide password
				etPassword.setTransformationMethod(new PasswordTransformationMethod());
			} else {
				// Show password
				etPassword.setTransformationMethod(null);
			}
			isPasswordVisible = !isPasswordVisible; // Toggle the flag
		}
}
	
	