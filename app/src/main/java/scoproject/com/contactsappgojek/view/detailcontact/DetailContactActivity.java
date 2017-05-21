package scoproject.com.contactsappgojek.view.detailcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ActivityDetailContactListBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.BaseActivity;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreen;
import scoproject.com.contactsappgojek.viewmodel.detailcontact.DetailContactVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class DetailContactActivity extends BaseActivity<ActivityDetailContactListBinding, DetailContactVM> {
    private DetailContactComponent mComponent;
    private DetailContactVM mViewModel;
    private long mPeopleId;

    @Override
    protected void onCreateUI(Bundle bundle) {
        setAndBindContentView(bundle, R.layout.activity_detail_contact_list);
        mPeopleId = getIntent().getLongExtra(Screen.PEOPLE_ID, 0);
        mViewModel = new DetailContactVM(mPeopleId, getViewBinding());
        mComponent.inject(mViewModel);
        mViewModel.takeContext(this);
        binding.setVm(mViewModel);
    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {
        mComponent = DaggerDetailContactComponent.builder().appComponent(appComponent).build();
        mComponent.inject(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mViewModel.onDestroy();
    }

    public static class Screen extends ActivityScreen{
        private static final String PEOPLE_ID = "People Id";

        private long mPeopleId;

        public Screen(long id){
            this.mPeopleId = id;
        }

        @Override
        protected void configureIntent(@NonNull Intent intent) {
            intent.putExtra(PEOPLE_ID, mPeopleId);
        }

        @Override
        protected Class<? extends Activity> activityClass() {
            return DetailContactActivity.class;
        }
    }
}
