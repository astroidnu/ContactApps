package scoproject.com.contactsappgojek;

import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import io.realm.Realm;
import scoproject.com.contactsappgojek.di.component.AppComponent;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public class ContactsApp extends MultiDexApplication{
    public static ContactsApp mInstance;

    private AppComponent mAppComponent;

    public static ContactsApp getApp() { return mInstance;}

    @Override
    public void onCreate(){
        super.onCreate();
        Realm.init(this);
        MultiDex.install(this);
        mInstance  = this;
        mAppComponent = AppComponent.Initializer.init(this);
    }

    public AppComponent component(){ return mAppComponent;}
}
