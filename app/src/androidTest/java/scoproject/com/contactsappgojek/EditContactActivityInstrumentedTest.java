package scoproject.com.contactsappgojek;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

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
import scoproject.com.contactsappgojek.networking.updatecontact.UpdateContactAPIService;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;
import scoproject.com.contactsappgojek.view.editcontact.EditContactActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static scoproject.com.contactsappgojek.utils.RecyclerViewMatcher.withRecyclerView;

/**
 * Created by ibnumuzzakkir on 5/22/17.
 */

public class EditContactActivityInstrumentedTest {
    @Inject
    UpdateContactAPIService mUpdateContactAPIService;
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
    public void testFullnameValidation() throws Exception {
        myActivityRule.launchActivity(null);
        onView(withId(R.id.recycle_contact_list)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.recycle_contact_list).atPosition(0))
                .check(matches(hasDescendant(withText("123HH Bachchan"))));
        onView(withId(R.id.recycle_contact_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.detail_edit_contact)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_edit_contact)).perform(click());
        ViewInteraction mEdFullName = onView(allOf(ViewMatchers.withId(R.id.edit_fullname), isDisplayed()));
        mEdFullName.perform(clearText());
        mEdFullName.perform(typeText("ibnu ga"), closeSoftKeyboard());
        ViewInteraction mEdPhoneNumber = onView(allOf(ViewMatchers.withId(R.id.edit_phone), isDisplayed()));
        mEdPhoneNumber.perform(clearText());
        mEdPhoneNumber.perform(typeText(String.valueOf("1234567890")), closeSoftKeyboard());
        ViewInteraction mEdEmail = onView(allOf(ViewMatchers.withId(R.id.edit_email), isDisplayed()));
        mEdEmail.perform(clearText());
        mEdEmail.perform(typeText("ibnu@ibnu.com"), closeSoftKeyboard());
        onView(withId(R.id.toolbar_submit)).perform(click());
        onView(withId(R.id.edit_fullname_error)).check(matches(withText("Last Name should be contains more than 2 characters")));
    }

    @Test
    public void testPhoneNumberValidation() throws Exception{
        myActivityRule.launchActivity(null);
        onView(withId(R.id.recycle_contact_list)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.recycle_contact_list).atPosition(0))
                .check(matches(hasDescendant(withText("123HH Bachchan"))));
        onView(withId(R.id.recycle_contact_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Thread.sleep(250);
        onView(withId(R.id.detail_edit_contact)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_edit_contact)).perform(click());
        ViewInteraction mEdFullName = onView(allOf(ViewMatchers.withId(R.id.edit_fullname), isDisplayed()));
        mEdFullName.perform(typeText("ibnu muzzakkir"), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdPhoneNumber = onView(allOf(ViewMatchers.withId(R.id.edit_phone), isDisplayed()));
        mEdPhoneNumber.perform(typeText(String.valueOf("12345")), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdEmail = onView(allOf(ViewMatchers.withId(R.id.edit_email), isDisplayed()));
        mEdEmail.perform(typeText("ibnu@ibnu.com"), closeSoftKeyboard());
        Thread.sleep(250);
        onView(withId(R.id.toolbar_submit)).perform(click());
        onView(withId(R.id.edit_phone_error)).check(matches(withText("Phone Number should be of 10 digits")));
    }

    @Test
    public void testEmailValidation() throws Exception{
        myActivityRule.launchActivity(null);
        onView(withId(R.id.recycle_contact_list)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.recycle_contact_list).atPosition(0))
                .check(matches(hasDescendant(withText("123HH Bachchan"))));
        onView(withId(R.id.recycle_contact_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        Thread.sleep(250);
        onView(withId(R.id.detail_edit_contact)).check(matches(isDisplayed()));
        onView(withId(R.id.detail_edit_contact)).perform(click());
        ViewInteraction mEdFullName = onView(allOf(ViewMatchers.withId(R.id.edit_fullname), isDisplayed()));
        mEdFullName.perform(typeText("ibnu muzzakkir"), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdPhoneNumber = onView(allOf(ViewMatchers.withId(R.id.edit_phone), isDisplayed()));
        mEdPhoneNumber.perform(typeText(String.valueOf("1234567890")), closeSoftKeyboard());
        Thread.sleep(250);
        ViewInteraction mEdEmail = onView(allOf(ViewMatchers.withId(R.id.edit_email), isDisplayed()));
        mEdEmail.perform(typeText("ibnu@ibnu"), closeSoftKeyboard());
        Thread.sleep(250);
        onView(withId(R.id.toolbar_submit)).perform(click());
        onView(withId(R.id.edit_email_error)).check(matches(withText("Invalid email format")));
    }
}
