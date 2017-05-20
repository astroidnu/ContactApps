package scoproject.com.contactsappgojek.view.addnewcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ActivityAddNewContactBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.BaseActivity;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreen;
import scoproject.com.contactsappgojek.viewmodel.addnewcontact.AddNewContactVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class AddNewContactActivity extends BaseActivity<ActivityAddNewContactBinding, AddNewContactVM> {
    private AddNewContactComponent mComponent;
    private AddNewContactVM mViewModel;

    @Override
    protected void onCreateUI(Bundle bundle) {
        setAndBindContentView(bundle, R.layout.activity_add_new_contact);
        mViewModel = new AddNewContactVM();
        mComponent.inject(mViewModel);
        mViewModel.takeContext(this);
        binding.setVm(mViewModel);

    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {
        mComponent = DaggerAddNewContactComponent.builder().appComponent(appComponent).build();
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
            return AddNewContactActivity.class;
        }
    }

}
