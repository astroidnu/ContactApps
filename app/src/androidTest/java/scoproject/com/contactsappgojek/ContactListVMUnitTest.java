package scoproject.com.contactsappgojek;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import scoproject.com.contactsappgojek.component.TestComponent;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListVM;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */
public class ContactListVMUnitTest {
    private ContactListVM contactListVM;
    private TestComponent mComponent;

    @Rule
    public ActivityTestRule<ContactListActivity> activityTestRule = new ActivityTestRule<>(ContactListActivity.class,true, false);

    @Before
    public void setUp() throws Exception {
//        mComponent = DaggerTestComponent.builder().appComponent(ContactsApp.getApp().component()).build();
//        mComponent.inject(this);
//        contactListVM = new ContactListVM();

    }

    @Test
    public void onGetContactList(){

    }



}
