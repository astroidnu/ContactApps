package scoproject.com.contactsappgojek.ui.base;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.generated.callback.OnClickListener;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import io.reactivex.disposables.CompositeDisposable;
import scoproject.com.contactsappgojek.ui.base.view.ViewVM;

/**
 * Created by ibnumuzzakkir on 5/18/17.
 */

public abstract class BaseVM <V extends ViewVM, T extends Context> extends BaseObservable implements IBaseVM<V>{
    private V mvvmView;
    private T context = null;
    private boolean loaded;
    protected CompositeDisposable compositeDisposable;

    public BaseVM(){
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    @CallSuper
    public void attachView(V mvvmView, @Nullable Bundle savedInstanceState) {
        this.mvvmView = mvvmView;
        if(savedInstanceState != null) { restoreInstanceState(savedInstanceState); }
    }

    @Override
    @CallSuper
    public void detachView() {
        mvvmView = null;
    }

    public final void takeContext(T context){
        if(context == null){
            throw new NullPointerException("Context must be not null");
        }

        if (this.context != context) {
            this.context = context;
            if (getContext() != null && !loaded) {
                loaded = true;
                onLoad();
            }
        }
    }

    protected void onLoad() {}

    protected void onRightToolbarIconClick(){}

    protected void onLeftToolbarIconClick(){}

    public void rightIconClick(){
        onRightToolbarIconClick();
    }

    public void leftIconClick(){
        onLeftToolbarIconClick();
    }

    protected void restoreInstanceState(@NonNull Bundle savedInstanceState) { }

    public void saveInstanceState(Bundle outState) { }

    public final boolean isViewAttached() {
        return mvvmView != null;
    }

    public final V getView() {
        return mvvmView;
    }

    public final void checkViewAttached() {
        if (!isViewAttached()) throw new NullPointerException("Please call ViewModel.attachView(MvvmView) before requesting data to the ViewModel");
    }

    public final T getContext() {
        return context;
    }

    protected  View.OnClickListener rightClick;


    public void clearCompositeDisposable() {
        compositeDisposable.clear();
    }
}
