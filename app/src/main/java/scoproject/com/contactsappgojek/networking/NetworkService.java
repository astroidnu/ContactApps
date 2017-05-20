package scoproject.com.contactsappgojek.networking;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIResponse;
import scoproject.com.contactsappgojek.networking.updatecontact.UpdateContactAPIResponse;

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
    Observable<AddNewContactAPIResponse> postPeople(@Body People people);

    @PUT("/contacts/{id}.json")
    Observable<UpdateContactAPIResponse> updatePeople(@Path("id") long id, @Body People people);
}
