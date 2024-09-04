package com.yuri.v20.HELPER;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.yuri.v20.ACTIVITIES.LoginActivity;
import com.yuri.v20.MainActivity;

public class AndroidHelper {
    public static void trocarTela(Context context, Class <?> cls){
        Intent intent = new Intent(context, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static void fazerToast(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }
}
