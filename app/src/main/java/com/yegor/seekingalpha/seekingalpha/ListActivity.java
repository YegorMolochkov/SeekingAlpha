package com.yegor.seekingalpha.seekingalpha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    @BindView(R.id.list)
    RecyclerView listView;
    private Unbinder mUnBinder;
    private PlanetsListScrollListener mListener;
    private PlanetsListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mUnBinder = ButterKnife.bind(this);
        init();
        loadPage(0);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    private void init() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        mListener = new PlanetsListScrollListener(manager) {

            @Override
            public void onLoadMore(int currentPage) {
                loadPage(currentPage);
            }
        };
        listView.addOnScrollListener(mListener);
        mAdapter = new PlanetsListAdapter(this, new PlanetsListAdapter.ItemSelectedListener() {
            @Override
            public void onItemSelected(Planet planet) {
                goToDetails(planet);
            }
        });
        listView.setAdapter(mAdapter);
    }

    private void goToDetails(Planet planet) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.SELECTED_PLANET, planet);
        startActivity(intent);
    }

    public void loadPage(int pageNumber) {
        SolarSystemService service = ServiceProvider.createService();
        Call<PlanetsResponse> call = service.loadPlanetsInfo(String.valueOf(pageNumber));
        call.enqueue(new Callback<PlanetsResponse>() {
            @Override
            public void onResponse(Call<PlanetsResponse> call, Response<PlanetsResponse> response) {
                if (response.body() != null) {
                    mAdapter.addItems(response.body().planets);
                }
            }

            @Override
            public void onFailure(Call<PlanetsResponse> call, Throwable t) {
                Log.d("TAGG", "error");
            }
        });
    }
}
