package scoproject.com.contactsappgojek.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import scoproject.com.contactsappgojek.BR;
import scoproject.com.contactsappgojek.R;
import scoproject.com.contactsappgojek.databinding.ItemContactFavoriteListBinding;
import scoproject.com.contactsappgojek.databinding.ItemContactListBinding;
import scoproject.com.contactsappgojek.data.People;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListRowFavVM;
import scoproject.com.contactsappgojek.viewmodel.contactlist.ContactListRowVM;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> {
    private Context mContext;
    private List<People> peopleList;
    private List<People> mPeopleFavoriteList = new ArrayList<>();
    private List<People> mPeopleUnFavoriteList = new ArrayList<>();
    private ListIterator<People> listIterator;

    public ContactListAdapter(Context context, List<People> peoples){
        peopleList = peoples;
        mContext = context;
        for(People people : peopleList){
            if(people.getFavorite()){
                mPeopleFavoriteList.add(people);
            }else{
                mPeopleUnFavoriteList.add(people);
            }
        }
        listIterator = peoples.listIterator();
        Log.d(getClass().getName(), String.valueOf(mPeopleFavoriteList.size()) + " "+String.valueOf(mPeopleUnFavoriteList.size()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),viewType, parent, false);
        return new ViewHolder(viewDataBinding, false);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (holder.getItemViewType()){
            case R.layout.item_contact_favorite_list:
                ContactListRowFavVM contactListRowFavVM = new ContactListRowFavVM(mPeopleFavoriteList.get(position),  ((ItemContactFavoriteListBinding) holder.getDataBinding()));
                ItemContactFavoriteListBinding mViewDataBindingFav = ((ItemContactFavoriteListBinding) holder.getDataBinding());
                contactListRowFavVM.takeContext(mContext);
                if(position == 0){
                    mViewDataBindingFav.setVariable(BR.isIndex, true);
                }else{
                    mViewDataBindingFav.setVariable(BR.isIndex, false);
                }
                mViewDataBindingFav.setVm(contactListRowFavVM);
                break;
            case R.layout.item_contact_list:
                String first_name = null;
                String first_name_prev = null;
                int index = position - mPeopleFavoriteList.size();
                int index_prev = position - mPeopleFavoriteList.size();
                if(index == 0){
                    index_prev = index;
                }else{
                    index_prev = index_prev -1;
                }
                first_name = mPeopleUnFavoriteList.get(index).first_name.substring(0,1).toLowerCase();
                first_name_prev =  mPeopleUnFavoriteList.get(index_prev).first_name.substring(0,1).toLowerCase();
                Log.d(getClass().getName(), "Next" + String.valueOf(index_prev) + first_name_prev + "- prev" + String.valueOf(index)+ first_name);
                ContactListRowVM contactListRowVM = new ContactListRowVM(mPeopleUnFavoriteList.get(position  - mPeopleFavoriteList.size()),  ((ItemContactListBinding) holder.getDataBinding()));
                contactListRowVM.takeContext(mContext);
                holder.getDataBinding().setVariable(BR.isIndex, true);
                ItemContactListBinding mViewDataBinding = ((ItemContactListBinding) holder.getDataBinding());
                if(index == 0){
                    mViewDataBinding.setVariable(BR.isIndex, true);
                }else{
                    if(!first_name.equals(first_name_prev)){
                        mViewDataBinding.setVariable(BR.isIndex, true);
                    }else{
                        mViewDataBinding.setVariable(BR.isIndex, false);
                    }
                }
                mViewDataBinding.setVm(contactListRowVM);
                break;
            case R.layout.item_section:
                //Only for section view
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position < mPeopleFavoriteList.size())
            return R.layout.item_contact_favorite_list;
//        else if((mPeopleFavoriteList.size() + 1) == position)
//            return R.layout.item_section;
        else
            return R.layout.item_contact_list;
    }

    @Override
    public int getItemCount() {
        return mPeopleFavoriteList.size() + mPeopleUnFavoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;
        private Boolean isIndex = false;

        public ViewHolder(ViewDataBinding viewDataBinding,Boolean isIndex){
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
            this.isIndex = isIndex;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getDataBinding(){
            return mViewDataBinding;
        }
    }

}
