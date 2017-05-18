package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.content.Context;
import android.util.Log;

import scoproject.com.contactsappgojek.ui.base.BaseVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class ContactListVM extends BaseVM implements IContactListVM {
    private Context mContext;
    public ContactListVM(Context context){
        mContext = context;
        Log.d(getClass().getName(), "ContactList Constructor");
    }
    @Override
    public void getContactList() {

    }
}
