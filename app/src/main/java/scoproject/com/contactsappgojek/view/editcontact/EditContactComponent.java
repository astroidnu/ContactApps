package scoproject.com.contactsappgojek.view.editcontact;

import dagger.Component;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.viewmodel.editcontact.EditContactVM;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

@EditContactScope
@Component(dependencies = {AppComponent.class})
public interface EditContactComponent {
    void inject(EditContactActivity editContactActivity);
    void inject(EditContactVM editContactVMn);
}
