package scoproject.com.contactsappgojek;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import scoproject.com.contactsappgojek.viewmodel.addnewcontact.AddNewContactVM;

import static org.junit.Assert.assertEquals;

/**
 * Created by ibnumuzzakkir on 5/21/17.
 */

public class AddContactVMUnitTest {
    private AddNewContactVM addNewContactVM;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        addNewContactVM = new AddNewContactVM();
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

}
