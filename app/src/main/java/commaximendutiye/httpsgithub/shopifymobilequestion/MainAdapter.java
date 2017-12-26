package commaximendutiye.httpsgithub.shopifymobilequestion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxime on 12/22/2017.
 * RecyclerView Adapter for the a List of Products
 */

public class MainAdapter extends RecyclerView.Adapter<MainAdapterCustomViewHolder>{

    ShopifyProducts shopProducts;
    List<Product> productList;
    List<Product> filteredProducts = new ArrayList<>();
    Context context;
    public MainAdapter(Context context, ShopifyProducts shopProducts){
        this.context = context;
        this.shopProducts = shopProducts;
        this.productList = shopProducts.products;
        filteredProducts.addAll(productList);
    }

    @Override
    public int getItemCount(){
        return filteredProducts.size();
    }

    @Override
    public void onBindViewHolder(final MainAdapterCustomViewHolder holder, final int position) {

        TextView productTitleView = holder.itemView.findViewById(R.id.textView_product_title);
        TextView productDescriptionView = holder.itemView.findViewById(R.id.textView_product_description);
        ImageView productImageView = holder.itemView.findViewById(R.id.imageView_product_image);
        final Product currentProduct = filteredProducts.get(position);
        String productImageUrl = currentProduct.image.src;

        productTitleView.setText(currentProduct.title);
        productDescriptionView.setText(currentProduct.body_html);
        Picasso.with(holder.itemView.getContext()).load(productImageUrl).into(productImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ProductViewActivity.class);
                intent.putExtra("product",currentProduct);
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public MainAdapterCustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View cellForRow = layoutInflater.inflate(R.layout.product_layout, parent, false);
        return new MainAdapterCustomViewHolder(cellForRow);
    }

    // Do Search...
    public void filter(final String text) {

        filteredProducts.clear();
        List<Product> temp = new ArrayList<>();

        if (TextUtils.isEmpty(text)) {
            filteredProducts.addAll(productList);

        } else {

            for (Product item : productList) {
                if (item.title.toLowerCase().contains(text.toLowerCase()) ||
                        item.body_html.toLowerCase().contains(text.toLowerCase())) {

                    filteredProducts.add(item);
                    temp.add(item);
                }
            }
        }

        notifyDataSetChanged();
    }
}

class MainAdapterCustomViewHolder extends RecyclerView.ViewHolder{

    public MainAdapterCustomViewHolder(View itemView) {
        super(itemView);
    }
}