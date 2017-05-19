package scoproject.com.contactsappgojek.ui.base.view;

import android.content.Intent;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public interface IScreenSwitcher {

    void open(Screen screen);

    void goBack();

    void openAndFinish(Screen screen);

    void closeWithResult(int resultCode, Intent result);

    //public void startForResult(Screen screen);
}
