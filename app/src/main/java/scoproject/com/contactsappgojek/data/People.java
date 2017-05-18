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


    protected People(Parcel in) {
        id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        profile_pic = in.readString();
        favorite = in.readByte() != 0;
        url = in.readString();
    }

    @Generated(hash = 1879578571)
    public People(long id, String first_name, String last_name, String profile_pic,
            boolean favorite, String url) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.profile_pic = profile_pic;
        this.favorite = favorite;
        this.url = url;
    }

    @Generated(hash = 1406030881)
    public People() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(profile_pic);
        dest.writeByte((byte) (favorite ? 1 : 0));
        dest.writeString(url);
    }

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

}
