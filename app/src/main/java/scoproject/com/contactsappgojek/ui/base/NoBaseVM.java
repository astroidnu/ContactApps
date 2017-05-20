package scoproject.com.contactsappgojek.ui.base;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.os.Bundle;

import javax.inject.Inject;

import scoproject.com.contactsappgojek.ui.base.view.ViewVM;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public final class NoBaseVM extends BaseObservable implements IBaseVM<ViewVM> {

    @Inject
    public NoBaseVM() { }

    @Override
    public void attachView(ViewVM viewVM, Bundle savedInstanceState) { }

    @Override
    public void saveInstanceState(Bundle outState) { }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {}

    @Override
    public void detachView() { }

}
