package scoproject.com.contactsappgojek.component;

import dagger.Component;
import scoproject.com.contactsappgojek.AddContactActivityInstumentedTest;
import scoproject.com.contactsappgojek.ContactListActivityTest;
import scoproject.com.contactsappgojek.DetailContactActivityTest;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.scope.TestScope;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */
@TestScope
@Component(dependencies = {AppComponent.class})
public interface TestComponent {
    void inject(ContactListActivityTest contactListActivityTest);
    void inject(AddContactActivityInstumentedTest addContactActivityTest);
    void inject(DetailContactActivityTest detailContactActivityTest);
}
