package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.content.Context;
import android.databinding.Bindable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.BR;
import scoproject.com.contactsappgojek.adapter.ContactListAdapter;
import scoproject.com.contactsappgojek.data.People;
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


    public ContactListAdapter mContactListAdapter;
    public LinearLayoutManager mLinearLayoutManager;
    private List<People> mPeopleList;
    private boolean isLoading = false;

    private Context mContext;
    public ContactListVM(Context context){
        mContext = context;
        Log.d(getClass().getName(), "ContactList Constructor");
    }

    @Override
    public void onLoad(){
        super.onLoad();
        setLoading(true);
        mLinearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        compositeDisposable.add(
                mGetContactListAPIService.getContactList().subscribe(peopleData ->setAdapter(peopleData),
                        throwable -> Log.d(getClass().getName(), throwable.getMessage())));
    }

    @Override
    public void setAdapter(List<People> peopleList) {
        mPeopleList = peopleList;
        setLoading(false);
        mContactListAdapter = new ContactListAdapter(mContext,mPeopleList);
        mContactListAdapter.notifyDataSetChanged();
    }

    @Bindable
    @Override
    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void setLoading(boolean loading) {
        isLoading = loading;
        notifyPropertyChanged(BR._all);
    }
}
