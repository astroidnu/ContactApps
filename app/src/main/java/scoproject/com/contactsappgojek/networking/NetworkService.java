package scoproject.com.contactsappgojek.networking;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIServiceResponse;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public interface NetworkService {
    @GET("/contacts.json")
    Observable<List<People>> getPeopleList();

    @GET("/contacts/{id}.json")
    Observable<People> getPeopleById(@Path("id") long id);

    @POST("/contacts.json")
    Observable<AddNewContactAPIServiceResponse> postPeople(@Body People people);
}
