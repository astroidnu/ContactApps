package scoproject.com.contactsappgojek.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

public class People extends RealmObject {
    @PrimaryKey
    public int id;
    public String first_name;
    public String last_name;
    public String profile_pic;
    public boolean favorite;
    public String url;

}
