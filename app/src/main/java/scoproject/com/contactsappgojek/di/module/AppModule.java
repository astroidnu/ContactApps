package scoproject.com.contactsappgojek.di.module;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import dagger.Module;
import dagger.Provides;
import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.di.scope.AppScope;
import scoproject.com.contactsappgojek.data.DaoMaster;
import scoproject.com.contactsappgojek.data.DaoSession;
import scoproject.com.contactsappgojek.model.PeopleModel;
import scoproject.com.contactsappgojek.utils.AppConst;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

@Module
public class AppModule {

    private final ContactsApp mApp;

    public AppModule(ContactsApp app) {
        this.mApp = app;
    }

    @Provides
    @AppScope
    Application provideApplicationContext(){ return mApp;}


    @Provides
    @AppScope
    RefWatcher provideRefWatcher() {
        return LeakCanary.install(mApp);
    }


    @Provides
    @AppScope
    DaoSession provideDaoSession() {
        String DbName = AppConst.database_name.APP_DATABASE_NAME;
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(ContactsApp.getApp(), DbName);
        Log.d("New DB Name: ", DbName);
        SQLiteDatabase db = devOpenHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        Log.d("Schema DB" , String.valueOf(daoMaster.getSchemaVersion()));
        return daoMaster.newSession();
    }

    //Provide DB Model Section

    @Provides
    @AppScope
    PeopleModel providePeopleModel(DaoSession daoSession) {
        return new PeopleModel(mApp, daoSession);
    }

}

