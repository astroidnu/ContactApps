package scoproject.com.contactsappgojek.viewmodel.contactlist;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ItemContactListBinding;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.ui.base.BaseRowVM;
import scoproject.com.contactsappgojek.view.contactlist.ContactListComponent;
import scoproject.com.contactsappgojek.view.contactlist.DaggerContactListComponent;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class ContactListRowVM extends BaseRowVM{
    private ItemContactListBinding mItemContactListBinding;
    private People mPeople;
    private ContactListComponent mComponent;
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
        mComponent = DaggerContactListComponent.builder().appComponent(appComponent).build();
        mComponent.inject(this);
    }

    public String urlPhoto() {
        // The URL will usually come from a model (i.e Profile)
        return mPeople.getProfile_pic();
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, @NonNull String imageUrl) {
//        if(imageUrl.contains("/images/missing.png")){
//            imageUrl = "http://gojek-contacts-app.herokuapp.com/"+imageUrl;
//        }
        Glide.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_message)
                .into(view);
    }
}
