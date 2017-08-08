package exercise.tbecker.aetnacodingexercise.displaylist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockApplication;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import exercise.tbecker.aetnacodingexercise.App;
import exercise.tbecker.aetnacodingexercise.R;
import exercise.tbecker.aetnacodingexercise.adapter.MyRecyclerAdapter;
import exercise.tbecker.aetnacodingexercise.di.DisplayListComponent;
import exercise.tbecker.aetnacodingexercise.displaydetails.DetailsActivity;
import exercise.tbecker.aetnacodingexercise.models.MyData;
import exercise.tbecker.aetnacodingexercise.models.ResponseItem;
import exercise.tbecker.aetnacodingexercise.network.MyRetrofitService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListActivity extends AppCompatActivity implements ListContract.View, MyRecyclerAdapter.ListItemClickListener {

    private static final String TAG = ListActivity.class.getSimpleName() + "_TAG";

    public static final String IMG_URL_TAG = "image_url";
    public static final String TITLE_TAG = "title";
    public static final String WIDTH_TAG = "width";
    public static final String HEIGHT_TAG = "height";

    @Inject
    ListPresenter presenter;

    @BindView(R.id.search_et)
    EditText searchEditText;

    @BindView(R.id.results_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.loading_progress)
    ProgressBar loadingProgressBar;

    private List<MyData> myDataList;
    private MyRecyclerAdapter myRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        injectDependencies();

        presenter.addView(this);

        initRecycler();
    }

    private void initRecycler() {
        myDataList = new ArrayList<>();
        myRecyclerAdapter = new MyRecyclerAdapter(myDataList, this);
        myRecyclerAdapter.setListItemClickListener(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myRecyclerAdapter);
    }

    private void injectDependencies() {
        DisplayListComponent displayListComponent = ((App) getApplication()).getDisplayListComponent();
        displayListComponent.inject(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        presenter.removeView();
    }

    @Override
    public void showDataErrorMessage() {
        Log.d(TAG, "onFailure: Error retrieving network data.");
        Toast.makeText(this, "Error retrieving data.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        Log.d(TAG, "onFailure: Error connecting to network.");
        Toast.makeText(this, "Network error. Check internet settings", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserList(List<MyData> listInfo) {
        updateRecyclerAdapter(listInfo);
        loadingProgressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.search_btn)
    public void makeNetworkCall() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        presenter.populateUserList(searchEditText.getText().toString());
    }

    private void updateRecyclerAdapter(List<MyData> list) {
        myDataList.clear();
        myDataList.addAll(list);
        myRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void OnItemClick(View v, int position) {
        Intent i = new Intent(ListActivity.this, DetailsActivity.class);
        i.putExtras(presenter.getDataBundle(position));
        startActivity(i);
    }
}
