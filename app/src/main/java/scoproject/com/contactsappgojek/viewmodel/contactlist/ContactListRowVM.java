package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.content.Context;
import android.databinding.ObservableField;

import scoproject.com.contactsappgojek.databinding.ItemContactListBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.model.People;
import scoproject.com.contactsappgojek.ui.base.BaseRowVM;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class ContactListRowVM extends BaseRowVM{
    private ItemContactListBinding mItemContactListBinding;
    private People mPeople;
    private String mUrlPhoto;

    public ObservableField<String> mFullName = new ObservableField<>();
    public ObservableField<String> mInitialName = new ObservableField<>();

    public ContactListRowVM(People people, ItemContactListBinding itemContactListBinding){
        mPeople = people;
        mItemContactListBinding = itemContactListBinding;
        mInitialName.set(people.first_name);
        mFullName.set(mPeople.first_name + " " + mPeople.last_name);
    }

    @Override
    protected void onCreateComponent(AppComponent appComponent) {

    }
}
