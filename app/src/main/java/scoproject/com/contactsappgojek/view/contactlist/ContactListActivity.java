package scoproject.com.contactsappgojek.view.contactlist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ActivityContactListBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.BaseActivity;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class ContactListActivity extends BaseActivity<ActivityContactListBinding,ContactListVM> {
    private ContactListComponent mComponent;
    private ContactListVM mContactListVM;

    @Override
    protected void onCreateUI(Bundle bundle) {
        setAndBindContentView(bundle,R.layout.activity_contact_list);
        mContactListVM = new ContactListVM(this);
        mComponent.inject(mContactListVM);
        mContactListVM.takeContext(this);
        binding.setVm(mContactListVM);

    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {
        mComponent = DaggerContactListComponent.builder().appComponent(appComponent).build();
        mComponent.inject(this);
    }

}
