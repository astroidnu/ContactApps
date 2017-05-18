package scoproject.com.contactsappgojek.di.module;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.di.scope.AppScope;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

@Module
public class AppModule {

    private final ContactsApp mApp;

    private RefWatcher mRefWatcher;

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
    static RealmConfiguration provideRealmConfiguration() {
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
//        if(BuildConfig.DEBUG) { builder = builder.deleteRealmIfMigrationNeeded(); }
        return builder.build();
    }

    @Provides
    static Realm provideRealm(RealmConfiguration realmConfiguration) {
        return Realm.getInstance(realmConfiguration);
    }

}

