package scoproject.com.contactsappgojek.networking.detailcontact;

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

public class GetDetailContactAPIService {
    private NetworkService mNetworkService;
    private int mId;

    public void init(int id){
        mId = id;
    }
    public GetDetailContactAPIService(NetworkService networkService){
        mNetworkService = networkService;
    }

    public Flowable<People> getContactListById(int id) {
        return mNetworkService.getPeopleById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(this::handleError)
                .toFlowable(BackpressureStrategy.BUFFER);
    }

    private void handleError(Throwable throwable) {
        Log.e(getClass().getName(), throwable.getMessage());
    }
}
