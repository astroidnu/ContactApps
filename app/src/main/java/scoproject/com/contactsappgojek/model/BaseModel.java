package scoproject.com.contactsappgojek.model;

import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.data.DaoSession;
import scoproject.com.contactsappgojek.di.component.AppComponent;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class BaseModel {
    protected final DaoSession mDaoSession;
    protected final AppComponent mComponent;

    public BaseModel(ContactsApp app, DaoSession daoSession) {
        mComponent = app.component();
        mDaoSession = daoSession;
    }
}
