package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentProvider;
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
import scoproject.com.contactsappgojek.utils.UIHelper;
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
    private boolean isContactNull = false;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder mAlertDialog;

    public ContactListVM(){
    }

    @Override
    public void onLoad(){
        super.onLoad();
        mProgressDialog = UIHelper.showProgressDialog(getContext());
        setLoading(true);
        mProgressDialog.show();
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        //Checking size database for table people
        checkingAndSetData();
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
    public boolean isContactNull(){return isContactNull;}

    public void setIsContactNull(boolean isContactNull){
        this.isContactNull = isContactNull;
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
        mProgressDialog.show();
        checkingAndSetData();
    }

    public void checkingAndSetData(){
        if(mPeopleModel.loadAll().size() > 0){
            onSuccess(mPeopleModel.loadAll());
        }else{
            //Load Data from API
            compositeDisposable.add(
                    mGetContactListAPIService.getContactList().subscribe(peopleData ->onSuccess(peopleData),
                            throwable -> onError()));
        }
    }

    public void onSuccess(List<People> peopleList) {
        if(peopleList.size() > 0){
            setIsContactNull(true);
            for(People people : peopleList){
                mPeopleModel.save(people);
            }
            mContactListAdapter = new ContactListAdapter(getContext(),peopleList);
            mContactListAdapter.notifyDataSetChanged();
        }else{
            setIsContactNull(true);
        }

        mProgressDialog.hide();
        setLoading(false);
        notifyPropertyChanged(BR._all);
    }

    public void onError(){
        mAlertDialog = UIHelper.showAlertDialog(getContext(),"Network Error", "Unable to contact the server");
        mAlertDialog.show();
    }

}
