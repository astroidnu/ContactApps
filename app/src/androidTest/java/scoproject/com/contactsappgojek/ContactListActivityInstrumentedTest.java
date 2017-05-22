package scoproject.com.contactsappgojek;

import android.content.Intent;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.component.DaggerTestComponent;
import scoproject.com.contactsappgojek.component.TestComponent;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.model.PeopleModel;
import scoproject.com.contactsappgojek.ui.base.view.ActivityScreenSwitcher;
import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;
import scoproject.com.contactsappgojek.view.detailcontact.DetailContactActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.AnyOf.anyOf;
import static scoproject.com.contactsappgojek.utils.RecyclerViewMatcher.withRecyclerView;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */
@RunWith(AndroidJUnit4.class)
public class ContactListActivityInstrumentedTest {
    private TestComponent testComponent;

    @Inject
    PeopleModel mPeopleModel;

    @Inject
    ActivityScreenSwitcher activityScreenSwitcher;

    @Rule
    public ActivityTestRule<ContactListActivity> mActivityRule =
            new ActivityTestRule<>(ContactListActivity.class);

    @Before
    public void SetUp(){
        testComponent = DaggerTestComponent.builder().appComponent(ContactsApp.getApp().component()).build();
        testComponent.inject(this);
    }

    @Test
    public void checkContactList(){
        if(mPeopleModel.loadAll().size() == 0){
            onView(withText("No Contact found"));
        }else{
            onView(withId(R.id.recycle_contact_list)).check(matches(isDisplayed()));
            onView(withRecyclerView(R.id.recycle_contact_list).atPosition(0))
                    .check(matches(hasDescendant(withText("123HH Bachchan"))));
            onView(withId(R.id.recycle_contact_list))
                    .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        }
    }
    @Test
    public void addNewMemberTest(){
        onView(withId(R.id.detail_fullname)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_add_new_contact)).perform(click());
    }

}
