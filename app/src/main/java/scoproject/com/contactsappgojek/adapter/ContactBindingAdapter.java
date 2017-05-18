package scoproject.com.contactsappgojek.adapter;

import android.databinding.BindingAdapter;
import android.support.compat.BuildConfig;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import scoproject.com.contactsappgojek.R;

/**
 * Created by ibnumuzzakkir on 5/19/17.
 */

public class ContactBindingAdapter {
    @BindingAdapter({"bind:profile_pic"})
    public static void loadImage(ImageView imageView, String url)
    {
        Log.i("bindingLogImage", "url : " + url);
        Glide.with(imageView.getContext())
                .load(url)
                .placeholder(R.drawable.ic_message)
                .into(imageView);
    }
}
