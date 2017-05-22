package scoproject.com.contactsappgojek.component;

import dagger.Component;
import scoproject.com.contactsappgojek.AddContactActivityInstumentedTest;
import scoproject.com.contactsappgojek.ContactListActivityInstrumentedTest;
import scoproject.com.contactsappgojek.DetailContactActivityInstrumentedTest;
import scoproject.com.contactsappgojek.EditContactActivityInstrumentedTest;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.scope.TestScope;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */
@TestScope
@Component(dependencies = {AppComponent.class})
public interface TestComponent {
    void inject(ContactListActivityInstrumentedTest contactListActivityInstrumentedTest);
    void inject(AddContactActivityInstumentedTest addContactActivityTest);
    void inject(DetailContactActivityInstrumentedTest detailContactActivityInstrumentedTest);
    void inject(EditContactActivityInstrumentedTest editContactActivityInstrumentedTest);
}
