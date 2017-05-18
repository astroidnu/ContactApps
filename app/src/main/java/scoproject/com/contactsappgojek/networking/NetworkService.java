package scoproject.com.contactsappgojek.networking;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import scoproject.com.contactsappgojek.data.People;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public interface NetworkService {
    @GET("/contacts.json")
    Observable<List<People>> getPeopleList();
}
