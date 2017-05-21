package scoproject.com.contactsappgojek;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import scoproject.com.contactsappgojek.view.contactlist.ContactListActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AnyOf.anyOf;
import static scoproject.com.contactsappgojek.utils.RecyclerViewMatcher.withRecyclerView;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */
@RunWith(AndroidJUnit4.class)
public class ContactListActivityTest {
    @Rule
    public ActivityTestRule<ContactListActivity> mActivityRule =
            new ActivityTestRule<>(ContactListActivity.class);

//    @Test
//    public void checkAllComponentContactPage(){
//        onView(anyOf(withId(R.id.btn_add_new_contact),withId(R.id.recycle_contact_list))).check(matches(isDisplayed()));
//    }

    @Test
    public void checkContactList(){
        onView(withId(R.id.recycle_contact_list)).check(matches(isDisplayed()));
        onView(withRecyclerView(R.id.recycle_contact_list).atPosition(0))
                .check(matches(hasDescendant(withText("Maaki Aankh"))));
        onView(withId(R.id.recycle_contact_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.detail_fullname)).check(matches(withText("Maaki Aankh")));

    }
    @Test
    public void addNewMemberTest(){
        onView(withId(R.id.btn_add_new_contact)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_add_new_contact)).perform(click());
    }

}
