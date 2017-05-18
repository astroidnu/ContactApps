package scoproject.com.contactsappgojek.di.component;

import android.app.Application;

import com.google.gson.Gson;
import com.squareup.leakcanary.RefWatcher;

import java.io.File;

import dagger.Component;
import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.di.module.AppModule;
import scoproject.com.contactsappgojek.di.module.NetworkModule;
import scoproject.com.contactsappgojek.di.scope.AppScope;
import scoproject.com.contactsappgojek.networking.contactlist.GetContactListAPIService;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */
@AppScope
@Component(
        modules = {AppModule.class, NetworkModule.class}
)
public interface AppComponent extends IAppComponent {
    final static class Initializer {
        public static AppComponent init(ContactsApp app){
            File cacheFile = new File(app.getCacheDir(), "responses");
            return DaggerAppComponent.builder()
                    .appModule(new AppModule(app))
                    .networkModule(new NetworkModule(cacheFile))
                    .build();
        }
    }

    Application getApplication();
    Gson getGson();
    RefWatcher refWatcher();

    //API Services
    GetContactListAPIService getContactListApiService();
}