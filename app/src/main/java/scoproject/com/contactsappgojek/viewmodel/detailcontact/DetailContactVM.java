package scoproject.com.contactsappgojek.viewmodel.detailcontact;

import android.databinding.ObservableField;
import android.util.Log;

import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.ui.base.BaseVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class DetailContactVM extends BaseVM implements IDetailContact{
    ObservableField<String> mFullName = new ObservableField<>();
    ObservableField<String> mPhoneNumber = new ObservableField<>();
    ObservableField<String> mEmail = new ObservableField<>();

    public DetailContactVM(){
        Log.d(getClass().getName(), "Constructor DetailContactVM");
    }

    @Override
    public void onLoad(){
        super.onLoad();
        Log.d(getClass().getName(), "onLoad DetailContactVM");
    }

    @Override
    public void getDetailContact(int id) {

    }
}
