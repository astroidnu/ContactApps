package scoproject.com.contactsappgojek.viewmodel.addnewcontact;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;

import com.android.databinding.library.baseAdapters.BR;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.model.PeopleModel;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIService;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIResponse;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;
import scoproject.com.contactsappgojek.utils.FileUtil;
import scoproject.com.contactsappgojek.utils.UIHelper;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class AddNewContactVM extends BaseVM implements IAddNewContactVM{

    @Inject
    AddNewContactAPIService mAddNewContactAPIService;
    @Inject
    Gson gson;
    @Inject
    PeopleModel mPeopleModel;
    @Inject
    ActivityScreenSwitcher mActivityScreenSwitcher;

    public ObservableField<String> mFullName = new ObservableField<>();
    public ObservableField<String> mPhoneNumber = new ObservableField<>();
    public ObservableField<String> mEmail = new ObservableField<>();
    public ObservableField<String> mFullNameError = new ObservableField<>();
    public ObservableField<String> mPhoneNUmberError = new ObservableField<>();
    public ObservableField<String> mEmailError = new ObservableField<>();

    private AlertDialog mAlertDialog;

    public AddNewContactVM(){
    }
    @Override
    public void onLoad(){
        super.onLoad();
        mAlertDialog = UIHelper.showProgressDialog(getContext());
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
        People people= new People();
        if(mFullName.get()!= null){
            if(mFullName.get().length() < 3){
                mFullNameError.set("First Name and Last Name should be more than 3 characters");
            }else{
                try{
                    String[] mFullNameSplit = mFullName.get().split(" ");
                    if(mFullNameSplit.length > 1){
                        people.setFirst_name(mFullNameSplit[0]);
                        people.setLast_name(mFullNameSplit[1]);
                        mFullNameError.set("");
                    }else{
                        mFullNameError.set("Name must be contains first and last");
                    }
                }catch (NullPointerException e){
                    mFullNameError.set("Name must be contains first and last");
                }
            }
        }else{
            mFullNameError.set("Please input name");
        }

        if(mPhoneNumber.get()!= null){
           if(isValidMobile(mPhoneNumber.get())){
               people.setPhoneNumber(mPhoneNumber.get());
               mPhoneNUmberError.set("");
           }
        }else{
            mPhoneNUmberError.set("Please input phone number");
        }

        if(mEmail.get()!= null){
            if(isValidMail(mEmail.get())){
                people.setEmail(mEmail.get());
                mEmailError.set("");
            }
        }else{
            mEmailError.set("Please input email");
        }
        String dataSend = gson.toJson(people);
        People peopleCheckData = gson.fromJson(dataSend, People.class);
        if(peopleCheckData.getFirst_name() != null && peopleCheckData.getEmail() != null && peopleCheckData.getPhoneNumber()!=null){
            mAlertDialog.show();
            compositeDisposable.add(
                    mAddNewContactAPIService.addContact(peopleCheckData).subscribe(peopleData ->  onSuccess(peopleData),
                            throwable -> onError(throwable)));
        }
        notifyPropertyChanged(BR._all);
    }

    private boolean isValidMobile(String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(!(phone.length() >= 10 && phone.length() <=11)) {
                check = false;
                mPhoneNUmberError.set("Phone Number should be of 10 digits");
            } else {
                mPhoneNUmberError.set("");
                check = true;
            }
        } else {
            mPhoneNUmberError.set("Phone Number should be of 10 digits");
            check=false;
        }
        return check;
    }

    private boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        if(!check) {
            mEmailError.set("Invalid email format");
        }
        return check;
    }


    private void onSuccess(AddNewContactAPIResponse response){
        mAlertDialog.hide();
        if(response.error == null){
            mPeopleModel.clear();
            mActivityScreenSwitcher.open(new ContactListActivity.Screen());
        }else{
            UIHelper.showToastMessage(getContext(),response.error);
        }
    }

    private void onError(Throwable throwable){
        mAlertDialog.hide();
        UIHelper.showToastMessage(getContext(), throwable.getMessage().toString());
    }
}
