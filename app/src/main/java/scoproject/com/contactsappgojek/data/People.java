package scoproject.com.contactsappgojek.data;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by ibnumuzzakkir on 18/05/2017.
 * Android Developer
 * Garena Indonesia
 */

@Entity
public class People implements Parcelable{
    @Id
    @SerializedName("id")
    public long id;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    public String last_name;
    @SerializedName("profile_pic")
    public String profile_pic;
    @SerializedName("favorite")
    public boolean favorite;
    @SerializedName("url")
    public String url;
    @SerializedName("phone_number")
    public String phoneNumber;
    @SerializedName("email")
    public String email;


    @Generated(hash = 2012473941)
    public People(long id, String first_name, String last_name, String profile_pic,
            boolean favorite, String url, String phoneNumber, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_pic = profile_pic;
        this.favorite = favorite;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
    
    protected People(Parcel in) {
        id = in.readLong();
        first_name = in.readString();
        last_name = in.readString();
        profile_pic = in.readString();
        favorite = in.readByte() != 0;
        url = in.readString();
        phoneNumber = in.readString();
        email = in.readString();
    }

    @Generated(hash = 1406030881)
    public People() {
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(profile_pic);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeString(url);
        dest.writeString(phoneNumber);
        dest.writeString(email);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getFavorite() {
        return this.favorite;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
