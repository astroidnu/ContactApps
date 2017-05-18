package scoproject.com.contactsappgojek.model;

import android.support.annotation.Nullable;

import java.util.List;

import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.data.DaoSession;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.data.PeopleDao;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class PeopleModel extends BaseModel{
    private PeopleDao mEntityDao;
    public PeopleModel(ContactsApp app, DaoSession daoSession) {
        super(app, daoSession);
        app.component().inject(this);
        mEntityDao = daoSession.getPeopleDao();
    }

    @Nullable
    public List<People> loadAll() {
        return mEntityDao.loadAll();
    }

    public synchronized void save(People people) {
        mEntityDao.insertOrReplace(people);
    }

    public void clear() {
        mEntityDao.deleteAll();
    }
}
