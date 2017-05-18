package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.networking.contactlist.GetContactListAPIService;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.ui.base.view.ViewVM;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class ContactListVM extends BaseVM<ViewVM, ContactListActivity> implements IContactListVM {
    @Inject
    GetContactListAPIService mGetContactListAPIService;

    @Inject
    Gson gson;

    private Context mContext;
    public ContactListVM(Context context){
        mContext = context;
        Log.d(getClass().getName(), "ContactList Constructor");
    }

    @Override
    public void onLoad(){
        super.onLoad();
        Log.d(getClass().getName(), gson.toJson("hello"));
        compositeDisposable.add(
                mGetContactListAPIService.getContactList().subscribe(peopleData -> Log.d(getClass().getName(),gson.toJson(peopleData)),
                        throwable -> Log.d(getClass().getName(), throwable.getMessage())));
    }

    @Override
    public void getContactList() {
        mGetContactListAPIService.getContactList();
    }
}
