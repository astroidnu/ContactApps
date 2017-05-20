package scoproject.com.contactsappgojek.networking.updatecontact;

import android.util.Log;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.networking.NetworkService;

/**
 * Created by ibnumuzzakkir on 5/20/17.
 */

public class UpdateContactAPIService {
    private NetworkService mNetworkService;

    public UpdateContactAPIService(NetworkService networkService){
        mNetworkService = networkService;
    }

    public Flowable<UpdateContactAPIResponse> updateContact(long id, People people) {
        return mNetworkService.updatePeople(id, people)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::handleAccountError)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private void handleAccountError(Throwable throwable) {
        Log.e(getClass().getName(), throwable.getMessage());
    }
}
