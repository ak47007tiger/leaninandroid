package org.leanandroid;

import org.leanandroid.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ConfirmActivity extends Activity {
   
    private Button btnOK ;
    private Button btnCancel;
   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.confirm);
        btnOK = (Button)this.findViewById(R.id.btnOk);
        btnCancel = (Button)this.findViewById(R.id.btnCancel);
        btnOK.setText("OK");
        btnCancel.setText("CANCEL");
        btnOK.setOnClickListener(new OKClickListener());
        btnCancel.setOnClickListener(new CancelClickListener());
    }
   
    class OKClickListener implements OnClickListener
    {

        @Override
        public void onClick(View v) {
            ConfirmActivity.this.finish();
        }       
    }
   
    class CancelClickListener implements OnClickListener
    {

        @Override
        public void onClick(View v) {
            ConfirmActivity.this.finish();
        }
       
    }
}