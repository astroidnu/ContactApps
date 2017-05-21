package scoproject.com.contactsappgojek.component;

import dagger.Component;
import scoproject.com.contactsappgojek.ContactListVMUnitTest;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.scope.TestScope;
import scoproject.com.contactsappgojek.view.contactlist.ContactListScope;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */
@TestScope
@Component(dependencies = {AppComponent.class})
public interface TestComponent {
    void inject(ContactListVMUnitTest contactListVMUnitTest);
}
