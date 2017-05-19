package scoproject.com.contactsappgojek.view.detailcontact;

import dagger.Component;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.viewmodel.detailcontact.DetailContactVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */
@DetailContactScope
@Component(dependencies = {AppComponent.class})
public interface DetailContactComponent {
    void inject(DetailContactVM mViewModel);
    void inject(DetailContactActivity detailContactActivity);
}
