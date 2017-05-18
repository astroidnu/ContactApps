package scoproject.com.contactsappgojek.networking.contactlist;

import android.util.Log;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import scoproject.com.contactsappgojek.networking.NetworkService;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactReponse;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class ContactListAPIService {
    private NetworkService mNetworkService;

    public ContactListAPIService(NetworkService networkService){
        mNetworkService = networkService;
    }

    public Flowable<ContactListResponse> getContactList() {

        return mNetworkService.getPeopleList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::handleAccountError)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private void handleAccountError(Throwable throwable) {
        Log.e(getClass().getName(), throwable.getMessage());
    }
}
