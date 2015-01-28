package com.youyoucy.tanghy.core;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogExit {

    private Activity context;

    public DialogExit(Activity context){
        this.context = context;
    }
    public void exit(){
        new AlertDialog.Builder(context).
                setTitle("Alert").
                setMessage("Do you want to exit ?").
                setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        context.finish();
                    }
                }).
                setNegativeButton("No", null).show();
    }
}
