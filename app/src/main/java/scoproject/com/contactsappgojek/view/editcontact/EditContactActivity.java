package scoproject.com.contactsappgojek.view.editcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.io.IOException;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.databinding.ActivityEditContactBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.BaseActivity;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreen;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;
import scoproject.com.contactsappgojek.viewmodel.editcontact.EditContactVM;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

public class EditContactActivity extends BaseActivity<ActivityEditContactBinding,EditContactVM> {
    private EditContactComponent mComponent;
    private EditContactVM mEditContactVM;

    @Override
    protected void onCreateUI(Bundle bundle) {
        setAndBindContentView(bundle, R.layout.activity_edit_contact);
        People people = getIntent().getParcelableExtra(Screen.PEOPLE_DATA);
        mEditContactVM = new EditContactVM(people, getViewBinding());
        mComponent.inject(mEditContactVM);
        mEditContactVM.takeContext(this);
        binding.setVm(mEditContactVM);
    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {
        mComponent = DaggerEditContactComponent.builder().appComponent(appComponent).build();
        mComponent.inject(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        try {
            mEditContactVM.onActivityResult(requestCode,resultCode,data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Screen extends ActivityScreen {

        private static final String PEOPLE_DATA = "people_data";
        private People mPeople;
        public Screen(People people){
            mPeople = people;

        }

        @Override
        protected void configureIntent(@NonNull Intent intent) {
            intent.putExtra(PEOPLE_DATA, mPeople);
        }

        @Override
        protected Class<? extends Activity> activityClass() {
            return EditContactActivity.class;
        }
    }
}
