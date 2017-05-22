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
import scoproject.com.contactsappgojek.viewmodel.editcontact.EditContactVM;

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

public class AllContactVMUnitTest {
    @Mock
    private ContactListVM contactListVM;
    private AddNewContactVM addNewContactVM;
    private EditContactVM editContactVM;
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
        addNewContactVM = new AddNewContactVM();
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

    @Test
    public void onCreateContactValidation() {
        assertEquals(addNewContactVM.isValidName(""), false);
        assertEquals(addNewContactVM.isValidMobile(""), false);
        assertEquals(addNewContactVM.isValidMail(""), false);

        assertEquals(addNewContactVM.isValidName("aa"), false);
        assertEquals(addNewContactVM.isValidMobile("999999"), false);
        assertEquals(addNewContactVM.isValidMail("aaaa.com"), false);

        assertEquals(addNewContactVM.isValidName("aaa"), true);
        assertEquals(addNewContactVM.isValidMobile("+91998012341234"), false);
        assertEquals(addNewContactVM.isValidMobile("998012341234"), false);
        assertEquals(addNewContactVM.isValidMobile("0998012341234"), false);
        assertEquals(addNewContactVM.isValidMail("test@ibnu.com"), true);
        assertEquals(addNewContactVM.isValidMobile("+1234567890"), true);
    }

    @Test
    public void onEditContactValidation() {
        assertEquals(editContactVM.isValidName(""), false);
        assertEquals(editContactVM.isValidMobile(""), false);
        assertEquals(editContactVM.isValidMail(""), false);

        assertEquals(editContactVM.isValidName("aa"), false);
        assertEquals(editContactVM.isValidMobile("999999"), false);
        assertEquals(editContactVM.isValidMail("aaaa.com"), false);

        assertEquals(editContactVM.isValidName("aaa"), true);
        assertEquals(editContactVM.isValidMobile("+91998012341234"), false);
        assertEquals(editContactVM.isValidMobile("998012341234"), false);
        assertEquals(editContactVM.isValidMobile("0998012341234"), false);
        assertEquals(editContactVM.isValidMail("test@ibnu.com"), true);
        assertEquals(editContactVM.isValidMobile("+1234567890"), true);
    }
}
