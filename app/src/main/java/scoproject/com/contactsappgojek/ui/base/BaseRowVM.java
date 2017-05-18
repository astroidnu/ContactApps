package scoproject.com.contactsappgojek.ui.base;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

import android.content.Context;
import android.databinding.BaseObservable;
import android.util.Log;

import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.di.component.AppComponent;

public abstract class BaseRowVM extends BaseObservable {
    private Context mContext;

    public final void takeContext(Context context){
        onCreateComponent(ContactsApp.getApp().component());
        mContext = context;
        if(mContext != null){
            onLoad();
        }else{
            Log.e(getClass().getName(), "Context must be not null");
        }
    }

    public final Context getContext() {
        return mContext;
    }

    protected void onLoad() {
    }

    protected void onStop(){
    }

    protected abstract void onCreateComponent(AppComponent appComponent);

}
