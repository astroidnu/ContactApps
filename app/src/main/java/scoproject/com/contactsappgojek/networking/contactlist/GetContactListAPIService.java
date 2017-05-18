package scoproject.com.contactsappgojek.networking.contactlist;

import android.util.Log;

import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import scoproject.com.contactsappgojek.model.People;
import scoproject.com.contactsappgojek.networking.NetworkService;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactReponse;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class GetContactListAPIService {
    private NetworkService mNetworkService;

    public GetContactListAPIService(NetworkService networkService){
        mNetworkService = networkService;
    }

    public Flowable<List<People>> getContactList() {
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
