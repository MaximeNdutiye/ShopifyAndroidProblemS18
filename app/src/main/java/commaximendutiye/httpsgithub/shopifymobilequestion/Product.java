package commaximendutiye.httpsgithub.shopifymobilequestion;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxime on 12/23/2017.
 */

public class Product implements Parcelable{

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public String id;
    public String title;
    public String body_html;
    public String vendor;
    public String product_type;
    public String tags;
    public List<ProductImage> images = new ArrayList<ProductImage>();
    public ThumbnailImage image;
    public List<Product> variants =  new ArrayList<Product>();


    public Product(Parcel input){
        id = input.readString();
        title = input.readString();
        body_html = input.readString();
        vendor  = input.readString();
        product_type  = input.readString();
        tags  = input.readString();
        input.readTypedList(images, ProductImage.CREATOR);
        input.readTypedList(variants, Product.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel destination, int i) {
        destination.writeString(this.id);
        destination.writeString(this.title);
        destination.writeString(this.body_html);
        destination.writeString(this.vendor);
        destination.writeString(this.product_type);
        destination.writeString(this.tags);
        destination.writeTypedList(this.images);
        destination.writeTypedList(this.variants);
    }
}