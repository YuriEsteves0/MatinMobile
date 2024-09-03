package com.yuri.v20.HELPER;

import android.content.Context;
import android.content.Intent;

import com.yuri.v20.ACTIVITIES.LoginActivity;
import com.yuri.v20.MainActivity;

public class AndroidHelper {
    public static void trocarTela(Context context, Class <?> cls){
        Intent intent = new Intent(context, cls);
        context.startActivity(intent);
    }
}
