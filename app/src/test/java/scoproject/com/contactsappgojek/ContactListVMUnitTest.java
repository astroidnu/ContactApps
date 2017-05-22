package scoproject.com.contactsappgojek;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.reactivestreams.Subscriber;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import scoproject.com.contactsappgojek.adapter.ContactListAdapter;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.databinding.ActivityContactListBinding;
import scoproject.com.contactsappgojek.model.PeopleModel;
import scoproject.com.contactsappgojek.networking.contactlist.GetContactListAPIService;
import scoproject.com.contactsappgojek.viewmodel.addnewcontact.AddNewContactVM;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListVM;
import scoproject.com.contactsappgojek.viewmodel.contactlist.IContactListVM;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */

public class ContactListVMUnitTest {
    @Mock
    private ContactListVM contactListVM;
    private CompositeDisposable compositeDisposable;
    ContactListAdapter contactListAdapter;
    @Mock
    GetContactListAPIService getContactListAPIService;
    ActivityContactListBinding mActivityContactListBinding;
    @Mock
    PeopleModel mPeopleModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        contactListAdapter = PowerMockito.mock(ContactListAdapter.class);
        contactListVM = PowerMockito.mock(ContactListVM.class);
        getContactListAPIService = PowerMockito.mock(GetContactListAPIService.class);
    }

    @Test
    public void onGetContactListTest(){
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People());
        when(getContactListAPIService.getContactList()).thenReturn(new Flowable<List<People>>() {
            @Override
            protected void subscribeActual(Subscriber<? super List<People>> s) {

            }
        });
        contactListVM.onSuccess(peopleList);
    }

    @Test
    public void onGetContactListFailedTest(){
        List<People> peopleList = new ArrayList<>();
        peopleList.add(new People());
        when(getContactListAPIService.getContactList()).thenReturn(new Flowable<List<People>>() {
            @Override
            protected void subscribeActual(Subscriber<? super List<People>> s) {

            }
        });
        contactListVM.onError();
    }
}
