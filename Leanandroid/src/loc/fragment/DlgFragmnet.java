package loc.fragment;

import org.leanandroid.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class DlgFragmnet extends DialogFragment{

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());  
        // Get the layout inflater  
        LayoutInflater inflater = getActivity().getLayoutInflater();  
        View view = inflater.inflate(R.layout.dlgfragment, null);  
        // Inflate and set the layout for the dialog  
        // Pass null as the parent view because its going in the dialog layout  
        builder.setView(view)  
                // Add action buttons  
                .setPositiveButton("Sign in",  
                        new DialogInterface.OnClickListener()  
                        {  
                            @Override  
                            public void onClick(DialogInterface dialog, int id)  
                            {  
                            }  
                        }).setNegativeButton("Cancel", null);  
        return builder.create(); 
	}
	/*@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View v = inflater.inflate(R.layout.dlgfragment, null);
		return v;
	}*/
}
