package com.avenues.lib.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.avenues.lib.utility.AvenuesParams;
import com.avenues.lib.utility.ServiceUtility;

import java.net.URI;
import java.net.URISyntaxException;

public class InitialScreenActivity extends AppCompatActivity {
	
	private EditText currency, amount, orderId;
	private String accessCode = "4YRUXLSRO20O8NIH";
	private String merchantId = "2";
	private String redirectUrl= "http://122.182.6.216/merchant/ccavResponseHandler.jsp";
	private String rsaKeyUrl= "https://secure.ccavenue.com/transaction/jsp/GetRSA.jsp";
	private String cancelUrl= "http://122.182.6.216/merchant/ccavResponseHandler.jsp";

	private void init(){
//		accessCode = (EditText) findViewById(R.id.accessCode);
//		merchantId = (EditText) findViewById(R.id.merchantId);
		orderId  = (EditText) findViewById(R.id.orderId);
		currency = (EditText) findViewById(R.id.currency);
		amount = (EditText) findViewById(R.id.amount);
//		rsaKeyUrl = (EditText) findViewById(R.id.rsaUrl);
//		redirectUrl = (EditText) findViewById(R.id.redirectUrl);
//		cancelUrl = (EditText) findViewById(R.id.cancelUrl);

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_screen);
		init();
	}

	public void onClick(View view) {
		//Mandatory parameters. Other parameters can be added if required.
//		String vAccessCode = ServiceUtility.chkNull(accessCode.getText()).toString().trim();
//		String vMerchantId = ServiceUtility.chkNull(merchantId.getText()).toString().trim();
		String vCurrency = ServiceUtility.chkNull(currency.getText()).toString().trim();
		String vAmount = ServiceUtility.chkNull(amount.getText()).toString().trim();
		if(!vCurrency.equals("") && !vAmount.equals("")){
			Intent intent = new Intent(this,WebViewActivity.class);
			intent.putExtra(AvenuesParams.ACCESS_CODE, accessCode);
			intent.putExtra(AvenuesParams.MERCHANT_ID, merchantId);
			intent.putExtra(AvenuesParams.ORDER_ID, ServiceUtility.chkNull(orderId.getText()).toString().trim());
			intent.putExtra(AvenuesParams.CURRENCY, ServiceUtility.chkNull(currency.getText()).toString().trim());
			intent.putExtra(AvenuesParams.AMOUNT, ServiceUtility.chkNull(amount.getText()).toString().trim());
			
			intent.putExtra(AvenuesParams.REDIRECT_URL, redirectUrl);
			intent.putExtra(AvenuesParams.CANCEL_URL, cancelUrl);
			intent.putExtra(AvenuesParams.RSA_KEY_URL, rsaKeyUrl);


			startActivity(intent);
		}else{
			showToast("All parameters are mandatory.");
		}
	}
	
	public void showToast(String msg) {
		Toast.makeText(this, "Toast: " + msg, Toast.LENGTH_LONG).show();
	}


	@Override
	protected void onStart() {
		super.onStart();
		//generating new order number for every transaction
		Integer randomNum = ServiceUtility.randInt(0, 9999999);
		orderId.setText(randomNum.toString());
	}

}