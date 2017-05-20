package scoproject.com.contactsappgojek.view.editcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import java.io.IOException;

import scoproject.com.contactsappgojek.R;
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
        mEditContactVM = new EditContactVM();
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
