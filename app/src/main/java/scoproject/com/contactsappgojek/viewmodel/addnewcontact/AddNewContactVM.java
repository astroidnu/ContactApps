package scoproject.com.contactsappgojek.viewmodel.addnewcontact;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;

import java.io.File;
import java.io.IOException;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.utils.FileUtil;

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
        //Checking Permssion
        if(Build.VERSION.SDK_INT == Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                selectImage();
            }else{
                ActivityCompat.requestPermissions(((Activity)getContext()), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
            }
        }else{
            selectImage();
        }
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        ((Activity)getContext()).startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) throws IOException {
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            final File actualImage = FileUtil.from(getContext(), data.getData());
            Log.d(getClass().getName(), String.valueOf(actualImage));
        }

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
