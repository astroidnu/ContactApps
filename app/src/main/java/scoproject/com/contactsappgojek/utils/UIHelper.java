package scoproject.com.contactsappgojek.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

public class UIHelper {
    public static void showToastMessage(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
