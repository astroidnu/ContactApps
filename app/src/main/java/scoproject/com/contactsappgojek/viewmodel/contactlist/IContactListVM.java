package scoproject.com.contactsappgojek.viewmodel.contactlist;

import java.util.List;

import scoproject.com.contactsappgojek.model.People;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public interface IContactListVM {
    void setAdapter(List<People> peopleList);
    void setLoading(boolean loading);
    boolean isLoading();
}
