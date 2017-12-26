package commaximendutiye.httpsgithub.shopifymobilequestion;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 */

public class MainActivity extends AppCompatActivity {

    private RecyclerView resultView;
    private OkHttpClient client;
    private List<Product> shopifyProductList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        client = new OkHttpClient();
        resultView = findViewById(R.id.resultView);
        getWebService();
    }

    public void getWebService(){

        String url = "https://shopicruit.myshopify.com/admin/products.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6";
        final Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException ioe) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response){

                try {
                    String body = response.body().string();
                    Gson gson = new Gson();
                    final ShopifyProducts myProducts = gson.fromJson(body, ShopifyProducts.class);
                    shopifyProductList = myProducts.products;

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            resultView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                            resultView.setAdapter(new MainAdapter(context, myProducts));
                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                MainAdapter adapter = (MainAdapter) resultView.getAdapter();
                adapter.filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                MainAdapter adapter = (MainAdapter) resultView.getAdapter();
                adapter.filter(newText);
                return false;
            }
        });
        return true;
    }
}

