package scoproject.com.contactsappgojek.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;

import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import io.realm.Realm;
import scoproject.com.contactsappgojek.BR;
import scoproject.com.contactsappgojek.ContactsApp;
import scoproject.com.contactsappgojek.di.component.AppComponent;
import scoproject.com.contactsappgojek.ui.base.view.ViewVM;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public abstract class BaseActivity<B extends ViewDataBinding, V extends IBaseVM> extends AppCompatActivity {


    // Inject a Realm instance into every Activity, since the instance
    // is cached and reused for a thread (avoids create/destroy overhead)
    @Inject
    protected Realm realm;

    protected B binding;

    protected V viewModel;
//
    @Inject
    RefWatcher refWatcher;

    protected abstract void onCreateUI(Bundle bundle);
    protected abstract void onCreateComponent(AppComponent appComponent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateComponent(ContactsApp.getApp().component());
        onCreateUI(savedInstanceState);
//        initDataBinding();
//        if (activityScreenSwitcher == null) {
//            throw new IllegalStateException(
//                    "No injection happened. Add component.inject(this) in onCreateComponent() implementation.");
//        }
//        activityScreenSwitcher.attach(this);
    }


    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(viewModel != null) { viewModel.saveInstanceState(outState); }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        if(refWatcher != null) {
            if(viewModel != null) { refWatcher.watch(viewModel); }
        }
        if(viewModel != null) { viewModel.detachView(); }
        binding = null;
        viewModel = null;
        if(realm != null) { realm.close(); }
    }

    /* Sets the content view, creates the binding and attaches the view to the view model */
    protected final void setAndBindContentView(@Nullable Bundle savedInstanceState, @LayoutRes int layoutResID) {
        binding = DataBindingUtil.setContentView(this, layoutResID);
        binding.setVariable(BR.vm, viewModel);

//        try {
//            //noinspection unchecked
//            viewModel.attachView((ViewVM) this, savedInstanceState);
//        } catch(ClassCastException e) {
//            if (!(viewModel instanceof NoBaseVM)) {
//                throw new RuntimeException(getClass().getSimpleName() + " must implement MvvmView subclass as declared in " + viewModel.getClass().getSimpleName());
//            }
//        }
    }

    public ViewDataBinding getViewBinding(){
        return binding;
    }

    public int dimen(@DimenRes int resId) {
        return (int) getResources().getDimension(resId);
    }

    public int color(@ColorRes int resId) {
        return getResources().getColor(resId);
    }

    public int integer(@IntegerRes int resId) {
        return getResources().getInteger(resId);
    }

    public String string(@StringRes int resId) {
        return getResources().getString(resId);
    }
}
