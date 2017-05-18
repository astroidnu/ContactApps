package scoproject.com.contactsappgojek.di.module;

import android.app.Application;

import com.squareup.leakcanary.RefWatcher;

import dagger.Module;
import dagger.Provides;
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
    RefWatcher providRefWatcher(){ return mRefWatcher;}
}

