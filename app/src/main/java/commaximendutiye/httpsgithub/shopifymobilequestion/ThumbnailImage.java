package commaximendutiye.httpsgithub.shopifymobilequestion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maxime on 12/24/2017.
 */

public class ThumbnailImage implements Parcelable{
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ThumbnailImage createFromParcel(Parcel in) {
            return new ThumbnailImage(in);
        }

        public ThumbnailImage[] newArray(int size) {
            return new ThumbnailImage[size];
        }
    };

    public String src;

    public ThumbnailImage(Parcel input){
        src = input.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int i) {
        destination.writeString(this.src);
    }
}
