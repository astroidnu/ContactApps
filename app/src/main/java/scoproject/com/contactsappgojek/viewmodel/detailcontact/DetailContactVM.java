package scoproject.com.contactsappgojek.viewmodel.detailcontact;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.model.PeopleModel;
import scoproject.com.contactsappgojek.networking.detailcontact.GetDetailContactAPIService;
import scoproject.com.contactsappgojek.networking.updatecontact.UpdateContactAPIResponse;
import scoproject.com.contactsappgojek.networking.updatecontact.UpdateContactAPIService;
import scoproject.com.contactsappgojek.ui.base.BaseVM;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;
import scoproject.com.contactsappgojek.utils.UIHelper;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;
import scoproject.com.contactsappgojek.view.editcontact.EditContactActivity;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class DetailContactVM extends BaseVM implements IDetailContact{
    @Inject
    GetDetailContactAPIService mGetDetailContactAPIService;
    @Inject
    UpdateContactAPIService mUpdateContactAPIService;
    @Inject
    ActivityScreenSwitcher mActivityScreenSwitcher;
    @Inject
    Gson gson;
    @Inject
    PeopleModel mPeopleModel;

    public ObservableField<String> mFullName = new ObservableField<>();
    public ObservableField<String> mPhoneNumber = new ObservableField<>();
    public ObservableField<String> mEmail = new ObservableField<>();

    private long mPeopleId;
    private People mPeople;
    public boolean mIsFavorite;

    private ClipboardManager myClipboard;
    private ClipData myClip;

    public DetailContactVM(long id){
        Log.d(getClass().getName(), String.valueOf(id));
        Log.d(getClass().getName(), "Constructor DetailContactVM");
        mPeopleId = id;
    }

    @Override
    public void onLoad(){
        super.onLoad();
        myClipboard = (ClipboardManager)getContext().getSystemService(getContext().CLIPBOARD_SERVICE);
        mPeople = new People();
        compositeDisposable.add(
                mGetDetailContactAPIService.getContactListById(mPeopleId).subscribe(peopleData ->setContactDetailData(peopleData),
                        throwable -> Log.d(getClass().getName(), throwable.getMessage())));
    }

    @Override
    public void setContactDetailData(People people) {
        mPeople = people;
        if(mPeople.favorite){
            setFavorite(true);
        }else{
            setFavorite(false);
        }
        mFullName.set(people.first_name + " " + people.last_name);
        mEmail.set(people.email);
        mPhoneNumber.set(people.phoneNumber);
    }

    public void onPhoneClick(){
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mPeople.phoneNumber));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(i);
    }

    public void onMessageClick(){
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + mPeople.phoneNumber));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(i);
    }

    public String urlPhoto() {
        // The URL will usually come from a model (i.e Profile)
        return mPeople.profile_pic;
    }

    public void onEmailClick(){
        myClip = ClipData.newPlainText("text", mPeople.email);
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(getContext(), mPeople.email, Toast.LENGTH_SHORT).show();
    }

    public void onPhoneNumberClick(){
        myClip = ClipData.newPlainText("text", mPeople.phoneNumber);
        myClipboard.setPrimaryClip(myClip);
        Toast.makeText(getContext(), mPeople.phoneNumber, Toast.LENGTH_SHORT).show();
    }

    public void onShareMenuClick(){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                mPeople.phoneNumber);
        sendIntent.setType("text/plain");
        getContext().startActivity(sendIntent);
    }

    public void onEditMenuClick(){
        mActivityScreenSwitcher.open(new EditContactActivity.Screen());
    }

    public void onIconEmailClick(){
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+mPeople.email));
        getContext().startActivity(Intent.createChooser(emailIntent, "Send email"));
    }

    public void onFavoriteClick(){
        //Set Favorite and update data to API
        if(isFavorite()){
            setFavorite(false);
            mPeople.setFavorite(false);
        }else{
            mPeople.setFavorite(true);
            setFavorite(true);
        }
        if(mPeople != null){
            //Update locally
            mPeopleModel.save(mPeople);
            //Send data to Server
            compositeDisposable.add(
                    mUpdateContactAPIService.updateContact(mPeople.id,mPeople).subscribe(peopleData ->  onSuccess(peopleData),
                            throwable -> onError(throwable)));
        }else{
            Log.d(getClass().getName(), "People Object must be not null");
        }
    }

    public void setFavorite(boolean isFavorite){
        mIsFavorite = isFavorite;
        notifyPropertyChanged(BR._all);
    }

    public boolean isFavorite(){
        return mIsFavorite;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_betty_allen)
                .into(view);
    }

    private void onSuccess(UpdateContactAPIResponse response) {
        if(response.error == null){
            UIHelper.showToastMessage(getContext(), "Successfully update contact");
        }else{
            UIHelper.showToastMessage(getContext(),response.error);
        }
    }

    private void onError(Throwable throwable) {
        UIHelper.showToastMessage(getContext(),throwable.getMessage());
    }

}
