package exercise.tbecker.aetnacodingexercise.displaydetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import exercise.tbecker.aetnacodingexercise.App;
import exercise.tbecker.aetnacodingexercise.R;
import exercise.tbecker.aetnacodingexercise.di.DisplayListComponent;

public class DetailsActivity extends AppCompatActivity implements DetailsContract.View {

    private static final String TAG = DetailsActivity.class.getSimpleName() + "_TAG";

    @Inject
    DetailsPresenter presenter;

    @BindView(R.id.detail_image)
    ImageView detailImgView;

    @BindView(R.id.detail_title_tv)
    TextView detailTitleTv;

    @BindView(R.id.detail_height_tv)
    TextView imgHeightTv;

    @BindView(R.id.detail_width_tv)
    TextView imgWidthTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(this);

        injectDependencies();

        presenter.addView(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.getData(getIntent().getExtras());
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
    public void displayData(String imgUrl, String title) {
        Glide.with(this).load(imgUrl).into(detailImgView);
        detailTitleTv.setText(title);
    }

    @Override
    public void ErrorDisplayingData() {
        Log.d(TAG, "ErrorDisplayingUserData: Data Bundle is null");
    }
}
