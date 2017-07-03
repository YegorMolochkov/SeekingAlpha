package com.yegor.seekingalpha.seekingalpha;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Screen with details of planet
 */
public class DetailsActivity extends AppCompatActivity {

    public static final String SELECTED_PLANET = "SELECTED_PLANET";

    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.description)
    TextView description;

    private Unbinder mUnBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mUnBinder = ButterKnife.bind(this);
        init();
    }

    private void init() {
        Planet planet = (Planet) getIntent().getSerializableExtra(SELECTED_PLANET);
        name.setText(planet.name);
        description.setText(planet.description);
        Picasso.with(this)
                .load(planet.URL)
                .fit()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.error)
                .into(image);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }
}
