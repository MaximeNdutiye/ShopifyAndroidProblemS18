package commaximendutiye.httpsgithub.shopifymobilequestion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by maxime on 12/23/2017.
 */

public class ProductImage implements Parcelable{

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public ProductImage createFromParcel(Parcel in) {
            return new ProductImage(in);
        }

        public ProductImage[] newArray(int size) {
            return new ProductImage[size];
        }
    };

    public String src;

    public ProductImage(Parcel input){
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