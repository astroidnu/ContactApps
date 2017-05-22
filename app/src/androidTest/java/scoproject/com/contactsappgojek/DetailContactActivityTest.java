package scoproject.com.contactsappgojek;

import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.component.DaggerTestComponent;
import scoproject.com.contactsappgojek.component.TestComponent;
import scoproject.com.contactsappgojek.networking.detailcontact.GetDetailContactAPIService;
import scoproject.com.contactsappgojek.view.addnewcontact.AddNewContactActivity;
import scoproject.com.contactsappgojek.view.detailcontact.DetailContactActivity;
import scoproject.com.contactsappgojek.viewmodel.detailcontact.DetailContactVM;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by ibnumuzzakkir on 5/22/17.
 */

public class DetailContactActivityTest {
    private DetailContactVM detailContactVM;

    private TestComponent testComponent;
    @Rule
    public ActivityTestRule<DetailContactActivity> myActivityRule = new ActivityTestRule<>(DetailContactActivity.class);

    @Before
    public void SetUp(){
        detailContactVM = new DetailContactVM(12,null);
        testComponent = DaggerTestComponent.builder().appComponent(ContactsApp.getApp().component()).build();
        testComponent.inject(this);
    }

    @Test
    public void checkAllComponentIsExist(){
        onView(withId(R.id.detail_fullname)).check(matches(isDisplayed()));
    }
}
