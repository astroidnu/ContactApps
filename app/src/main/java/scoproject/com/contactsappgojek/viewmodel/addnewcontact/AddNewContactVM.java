package scoproject.com.contactsappgojek.viewmodel.addnewcontact;

import android.app.Activity;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.ui.base.BaseVM;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class AddNewContactVM extends BaseVM implements IAddNewContactVM{

    public ObservableField<String> mFullName = new ObservableField<>();
    public ObservableField<String> mPhoneNumber = new ObservableField<>();
    public ObservableField<String> mEmail = new ObservableField<>();
    public ObservableField<String> mFullNameError = new ObservableField<>();
    public ObservableField<String> mPhoneNUmberError = new ObservableField<>();
    public ObservableField<String> mEmailError = new ObservableField<>();

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

    @Override
    public void onRightToolbarIconClick (){
        super.onRightToolbarIconClick();
        //Submit and save data
        onSubmitData();

    }

    @Override
    public void onLeftToolbarIconClick (){
        super.onLeftToolbarIconClick();
        //Finish Activity
        ((Activity) getContext()).finish();

    }

    public void photoOnClick(){
        Log.d(getClass().getName(), "PhotoOnClick()");
    }


    @Override
    public void onSubmitData() {
        if(mFullName.get()!= null){
            if(mFullName.get().length() < 3){
                mFullNameError.set("First Name and Last Name should be more than 3 characters");
            }else{
                mFullNameError.set("");
            }
        }else{
            mFullNameError.set("Please input first name and last name");
        }

        if(mPhoneNumber.get()!= null){
            if(mPhoneNumber.get().length() <10 && mPhoneNumber.get().length() >10){
                mPhoneNUmberError.set("Phone Number should be of 10 digits");
            }else{
                mPhoneNUmberError.set("");
            }
        }else{
            mPhoneNUmberError.set("Please input phone number");
        }

        if(mEmail.get()!= null){
            if(!mEmail.get().contains("@")){
                mEmailError.set("Invalid email format");
            }else{
                mEmailError.set("");
            }
        }else{
            mEmailError.set("Please input email");
        } 
        notifyPropertyChanged(BR._all);
    }
}
