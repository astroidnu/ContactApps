package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.content.Context;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.BR;
import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.adapter.ContactListAdapter;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.model.PeopleModel;
import scoproject.com.contactsappgojek.networking.contactlist.GetContactListAPIService;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;
import scoproject.com.contactsappgojek.ui.base.view.ViewVM;
import scoproject.com.contactsappgojek.view.addnewcontact.AddNewContactActivity;
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
    PeopleModel mPeopleModel;
    @Inject
    Gson gson;
    @Inject
    ActivityScreenSwitcher activityScreenSwitcher;


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
                mGetContactListAPIService.getContactList().subscribe(peopleData ->saveToDB(peopleData),
                        throwable -> Log.d(getClass().getName(), throwable.getMessage())));

    }

    @Override
    public void saveToDB(List<People> peopleList) {
        for(People people : peopleList){
            mPeopleModel.save(people);
        }
        mContactListAdapter = new ContactListAdapter(mContext,peopleList);
        mContactListAdapter.notifyDataSetChanged();
        setLoading(false);
    }

    public String titlebar(){
        return "Contacts";
    }

    public Drawable iconLeft(){
        return getContext().getResources().getDrawable(R.drawable.ic_menu);
    }

    public Drawable iconRight(){
        return getContext().getResources().getDrawable(R.drawable.ic_search);
    }

    public void addNewContact(){
        activityScreenSwitcher.open(new AddNewContactActivity.Screen());
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

    public void onResume() {
        Log.d(getClass().getName(),"OnResume()");
        compositeDisposable.add(
                mGetContactListAPIService.getContactList().subscribe(peopleData ->saveToDB(peopleData),
                        throwable -> Log.d(getClass().getName(), throwable.getMessage())));
    }
}
