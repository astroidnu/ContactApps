package scoproject.com.contactsappgojek.di.component;

import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public interface IAppComponent {
    void inject (ContactsApp app);
    ActivityScreenSwitcher activityScreenSwitcher();
}
