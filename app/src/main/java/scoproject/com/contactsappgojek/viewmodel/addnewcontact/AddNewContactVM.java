package scoproject.com.contactsappgojek.viewmodel.addnewcontact;

import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.view.addnewcontact.AddNewContactActivity;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class AddNewContactVM extends BaseVM{

    public ObservableField<String> mFullName = new ObservableField<>();
    public ObservableField<String> mPhoneNumber = new ObservableField<>();
    public ObservableField<String> mEmail = new ObservableField<>();

    public AddNewContactVM(){
    }
    @Override
    public void onLoad(){
        super.onLoad();
    }


    public String titlebar(){
        return "Add new Contact";
    }

    public Drawable iconLeft(){
        return getContext().getResources().getDrawable(R.drawable.ic_back);
    }

    public Drawable iconRight(){
        return getContext().getResources().getDrawable(R.drawable.ic_save);
    }
}
