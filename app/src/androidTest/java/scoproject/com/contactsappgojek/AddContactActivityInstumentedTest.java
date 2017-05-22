package scoproject.com.contactsappgojek;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import scoproject.com.contactsappgojek.component.DaggerTestComponent;
import scoproject.com.contactsappgojek.component.TestComponent;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIResponse;
import scoproject.com.contactsappgojek.networking.addnewcontact.AddNewContactAPIService;
import scoproject.com.contactsappgojek.view.addnewcontact.AddNewContactActivity;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Created by ibnumuzzakkir on 5/22/17.
 */
@RunWith(AndroidJUnit4.class)

public class AddContactActivityInstumentedTest {
    @Inject
    AddNewContactAPIService mAddNewContactAPIService;
    @Rule
    public ActivityTestRule<ContactListActivity> myActivityRule = new ActivityTestRule<>(ContactListActivity.class, true, false);

    private TestComponent mComponent;
    private MockWebServer mMockWebServer;
    private People mPeople;
    private CompositeDisposable mCompositeDisposable;

    @Before
    public void SetUp() throws Exception{
        mMockWebServer = new MockWebServer();
        mCompositeDisposable = new CompositeDisposable();
        mMockWebServer.start();
        mComponent = DaggerTestComponent.builder().appComponent(ContactsApp.getApp().component()).build();
        mComponent.inject(this);

        mPeople = new People();
        mPeople.setFirst_name("test");
        mPeople.setLast_name("test");
        mPeople.setFavorite(false);
        mPeople.setPhoneNumber("1234567890");
        mPeople.setEmail("test@test.com");
    }


    @Test
    public void testFirstNameValidation() throws Exception {
        myActivityRule.launchActivity(null);
        ViewInteraction btnAddNewContact = onView(allOf(ViewMatchers.withId(R.id.btn_add_new_contact), isDisplayed()));
        btnAddNewContact.perform(click());
        ViewInteraction mEdFullName = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_full_name), isDisplayed()));
        mEdFullName.perform(typeText("aa"), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdPhoneNumber = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_phone), isDisplayed()));
        mEdPhoneNumber.perform(typeText(String.valueOf("1234567890")), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdEmail = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_email), isDisplayed()));
        mEdEmail.perform(typeText("ibnu@ibnu.com"), closeSoftKeyboard());
        Thread.sleep(250);
        onView(withId(R.id.toolbar_submit)).perform(click());
        onView(withId(R.id.add_new_contact_full_name_error)).check(matches(withText("First Name and Last Name should be more than 3 characters")));
    }

    @Test
    public void testPhoneNumberValidation() throws Exception{
        myActivityRule.launchActivity(null);
        ViewInteraction btnAddNewContact = onView(allOf(ViewMatchers.withId(R.id.btn_add_new_contact), isDisplayed()));
        btnAddNewContact.perform(click());
        ViewInteraction mEdFullName = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_full_name), isDisplayed()));
        mEdFullName.perform(typeText("Test test"), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdPhoneNumber = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_phone), isDisplayed()));
        mEdPhoneNumber.perform(typeText(String.valueOf("1234")), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdEmail = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_email), isDisplayed()));
        mEdEmail.perform(typeText("ibnu@ibnu.com"), closeSoftKeyboard());
        Thread.sleep(250);
        onView(withId(R.id.toolbar_submit)).perform(click());
        onView(withId(R.id.add_new_contact_phone_error)).check(matches(withText("Phone Number should be of 10 digits")));
    }

    @Test
    public void testEmailValidation() throws Exception{
        myActivityRule.launchActivity(null);
        ViewInteraction btnAddNewContact = onView(allOf(ViewMatchers.withId(R.id.btn_add_new_contact), isDisplayed()));
        btnAddNewContact.perform(click());
        ViewInteraction mEdFullName = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_full_name), isDisplayed()));
        mEdFullName.perform(typeText("Test test"), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdPhoneNumber = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_phone), isDisplayed()));
        mEdPhoneNumber.perform(typeText(String.valueOf("1234567890")), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdEmail = onView(allOf(ViewMatchers.withId(R.id.add_new_contact_email), isDisplayed()));
        mEdEmail.perform(typeText("ibnu.com"), closeSoftKeyboard());
        Thread.sleep(250);
        onView(withId(R.id.toolbar_submit)).perform(click());
        onView(withId(R.id.add_new_contact_email_error)).check(matches(withText("Invalid email format")));
    }

    @Test
    public void testOnCreatingContactRequest() throws Exception{
        myActivityRule.launchActivity(null);
        ViewInteraction btnAddNewContact = onView(allOf(ViewMatchers.withId(R.id.btn_add_new_contact), isDisplayed()));
        btnAddNewContact.perform(click());
        mMockWebServer.enqueue(new MockResponse());
        mCompositeDisposable.add(
                mAddNewContactAPIService.addContact(mPeople).subscribe(peopleData ->  onSuccess(peopleData),
                        throwable -> onError(throwable)));
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        RecordedRequest request = this.mMockWebServer.takeRequest();
        assertEquals("/contacts", request.getPath());
        assertEquals("POST", request.getMethod());
        assertEquals(gson.toJson(this.mPeople).toString(), request.getBody().readUtf8());
    }

    private void onError(Throwable throwable) {
        //onError
    }

    private void onSuccess(AddNewContactAPIResponse peopleData) {
        //Success
    }


    @After
    public void tearDown() throws Exception {
        mMockWebServer.shutdown();
    }

}

