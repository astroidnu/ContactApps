package scoproject.com.contactsappgojek.view.addnewcontact;

import dagger.Component;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.viewmodel.addnewcontact.AddNewContactVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */
@AddNewContactScope
@Component(dependencies = {AppComponent.class})
public interface AddNewContactComponent {
    void inject(AddNewContactVM mViewModel);
    void inject(AddNewContactActivity addNewContactActivity);
}
