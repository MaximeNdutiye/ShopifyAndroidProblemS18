package commaximendutiye.httpsgithub.shopifymobilequestion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ProductViewActivity extends AppCompatActivity {

    Product product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        product = getIntent().getParcelableExtra("product");
        ProductImage image = product.images.get(0);
        List<Product> variants = product.variants;

        TextView productTitle = findViewById(R.id.textView_product_title);
        TextView productDescription = findViewById(R.id.textView_product_description);
        TextView productVendor = findViewById(R.id.textView_product_vendor);
        TextView productType = findViewById(R.id.textView_product_type);
        TextView productTags = findViewById(R.id.textView_product_tags);
        ImageView productImage = findViewById(R.id.imageView_product_image);
        ListView variantsList = findViewById(R.id.listView_product_variants);

        Picasso.with(this.getBaseContext()).load(image.src).into(productImage);
        productTitle.setText(product.title);
        productDescription.setText(product.body_html);
        productVendor.setText("Vendor : " + product.vendor);
        productType.setText("Product type : " + product.product_type);
        productTags.setText("Tags : " + product.tags);

        ArrayList<String> variantsTitlesAsStrings = new ArrayList<>();

        for(Product prod:variants){
            variantsTitlesAsStrings.add(prod.title);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.variant_list_item , R.id.variant_list_textView,  variantsTitlesAsStrings);
        ListView listView = findViewById(R.id.listView_product_variants);
        listView.setAdapter(dataAdapter);

    }
}
