package scoproject.com.contactsappgojek.viewmodel.editcontact;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.networking.updatecontact.UpdateContactAPIService;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;
import scoproject.com.contactsappgojek.utils.FileUtil;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

public class EditContactVM extends BaseVM implements IEditContactVM {
    @Inject
    UpdateContactAPIService mUpdateContactAPIService;
    @Inject
    Gson gson;
    @Inject
    ActivityScreenSwitcher mActivityScreenSwitcher;

    public ObservableField<String> mFullName = new ObservableField<>();
    public ObservableField<String> mPhoneNumber = new ObservableField<>();
    public ObservableField<String> mEmail = new ObservableField<>();
    public ObservableField<String> mFullNameError = new ObservableField<>();
    public ObservableField<String> mPhoneNUmberError = new ObservableField<>();
    public ObservableField<String> mEmailError = new ObservableField<>();

    public EditContactVM(){

    }

    @Override
    public void onLoad(){
        super.onLoad();
        Log.d(getClass().getName(),"onLoad");
    }

    public String titlebar(){
        return "Edit Contact";
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
//        onSubmitData();

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
}
