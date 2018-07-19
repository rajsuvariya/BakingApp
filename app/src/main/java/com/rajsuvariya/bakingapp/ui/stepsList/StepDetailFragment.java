package com.rajsuvariya.bakingapp.ui.stepsList;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.rajsuvariya.bakingapp.R;
import com.rajsuvariya.bakingapp.data.remote.model.Step;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a single Step detail screen.
 * This fragment is either contained in a {@link StepListActivity}
 * in two-pane mode (on tablets) or a {@link StepDetailActivity}
 * on handsets.
 */
public class StepDetailFragment extends Fragment {

    public static final String STEP_DETAILS = "_Step_Details";

    private Step mStepDetails;

    @BindView(R.id.epv_player_view)
    PlayerView epvVideoPlayerView;

    @BindView(R.id.tv_long_description)
    TextView tvLongDescription;

    @BindView(R.id.iv_step_thumbnail)
    ImageView ivStepThumbnail;

    private SimpleExoPlayer mPlayer;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public StepDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(STEP_DETAILS)) {
            mStepDetails = getArguments().getParcelable(STEP_DETAILS);

            Activity activity = this.getActivity();
        }
    }

    private void setUpExoPlayer(String videoUrl) {
        // 1. Create a default TrackSelector
        Handler mainHandler = new Handler();
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        // 2. Create the player
        mPlayer = ExoPlayerFactory.newSimpleInstance(getActivity(), trackSelector);

        // 3. Bind the player to the view.
        epvVideoPlayerView.setPlayer(mPlayer);

        // Measures bandwidth during playback. Can be null if not required.
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getActivity(),
                Util.getUserAgent(getActivity(), "yourApplicationName"), bandwidthMeter);
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl));
        // Prepare the player with the source.
        mPlayer.prepare(videoSource);
        mPlayer.setPlayWhenReady(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_detail, container, false);
        ButterKnife.bind(this, rootView);

        String videoUrl = null;
        String photoUrl = null;

        if (mStepDetails.getVideoURL()!=null && mStepDetails.getVideoURL().contains("mp4")){
            videoUrl = mStepDetails.getVideoURL();
        }

        if (mStepDetails.getThumbnailURL()!=null && (mStepDetails.getThumbnailURL().contains("jpg") || mStepDetails.getThumbnailURL().contains("png"))){
            photoUrl = mStepDetails.getThumbnailURL();
        }

        if (!TextUtils.isEmpty(videoUrl)) {
            epvVideoPlayerView.setVisibility(View.VISIBLE);
            setUpExoPlayer(videoUrl);
        } else {
            epvVideoPlayerView.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(photoUrl)) {
            ivStepThumbnail.setVisibility(View.VISIBLE);
            Glide.with(this).load(photoUrl).into(ivStepThumbnail);
        } else {
            ivStepThumbnail.setVisibility(View.GONE);
        }

        tvLongDescription.setText(mStepDetails.getDescription());

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer!=null) {
            mPlayer.release();
        }
    }
}
