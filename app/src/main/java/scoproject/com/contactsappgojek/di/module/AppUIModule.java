package scoproject.com.contactsappgojek.di.module;

import dagger.Module;
import dagger.Provides;
import scoproject.com.contactsappgojek.di.scope.AppScope;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */
@Module
public class AppUIModule {
    @Provides
    @AppScope
    ActivityScreenSwitcher provideActivityScreenSwitcher() {
        return new ActivityScreenSwitcher();
    }
}
