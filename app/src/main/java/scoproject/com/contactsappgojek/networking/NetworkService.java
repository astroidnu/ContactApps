package scoproject.com.contactsappgojek.networking;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import scoproject.com.contactsappgojek.model.People;
import scoproject.com.contactsappgojek.networking.contactlist.ContactListResponse;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public interface NetworkService {
    @GET("/contacts.json")
    Observable<ContactListResponse> getPeopleList();
}
