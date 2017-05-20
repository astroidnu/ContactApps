package scoproject.com.contactsappgojek.networking.addnewcontact;

import android.util.Log;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.networking.NetworkService;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class AddNewContactAPIService {
    private NetworkService mNetworkService;

    public AddNewContactAPIService(NetworkService networkService){
        mNetworkService = networkService;
    }

    public Flowable<AddNewContactAPIServiceResponse> addMember(People people) {
        return mNetworkService.postPeople(people)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::handleAccountError)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private void handleAccountError(Throwable throwable) {
        Log.e(getClass().getName(), throwable.getMessage());
    }
}
