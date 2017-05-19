package scoproject.com.contactsappgojek.view.detailcontact;

import android.os.Bundle;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ActivityDetailContactListBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.BaseActivity;
import scoproject.com.contactsappgojek.viewmodel.detailcontact.DetailContactVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class DetailContactActivity extends BaseActivity<ActivityDetailContactListBinding, DetailContactVM> {
    private DetailContactComponent mComponent;
    private DetailContactVM mViewModel;

    @Override
    protected void onCreateUI(Bundle bundle) {
        setAndBindContentView(bundle, R.layout.activity_contact_list);
        mViewModel = new DetailContactVM();
        mComponent.inject(mViewModel);
        mViewModel.takeContext(this);
        binding.setVm(mViewModel);

    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {
        mComponent = DaggerDetailContactComponent.builder().appComponent(appComponent).build();
        mComponent.inject(this);
    }
}
