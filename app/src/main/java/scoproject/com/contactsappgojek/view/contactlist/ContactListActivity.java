package scoproject.com.contactsappgojek.view.contactlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ActivityContactListBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.BaseActivity;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreen;
import scoproject.com.contactsappgojek.ui.base.view.Screen;
import scoproject.com.contactsappgojek.view.addnewcontact.AddNewContactActivity;
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
    public void onResume(){
        super.onResume();
        mContactListVM.onResume();
    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {
        mComponent = DaggerContactListComponent.builder().appComponent(appComponent).build();
        mComponent.inject(this);
    }

    public static class Screen extends ActivityScreen {


        public Screen(){

        }

        @Override
        protected void configureIntent(@NonNull Intent intent) {

        }

        @Override
        protected Class<? extends Activity> activityClass() {
            return ContactListActivity.class;
        }
    }

}
