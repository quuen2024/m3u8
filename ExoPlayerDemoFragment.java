
ExoPlayerDemoFragment.java
/*
** Created by bapspatil
*/

public class ExoPlayerDemoFragment extends Fragment {
    @BindView(R.id.video_exoplayer_view) SimpleExoPlayerView mPlayerView;
    private SimpleExoPlayer mPlayer;
    private Unbinder unbinder;

    public ExoPlayerDemoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exoplayer_demo, container, false);
        unbinder = ButterKnife.bind(this, rootView);
      
        getPlayer();
      
        return rootView;
    }
  
  private void getPlayer() {
	// URL of the video to stream
	String videoURL = "http://157.15.187.3:25092/hls/2/2.m3u8";
		
	// Handler for the video player
        Handler mainHandler = new Handler();
	
	/* A TrackSelector that selects tracks provided by the MediaSource to be consumed by each of the available Renderers.
	  A TrackSelector is injected when the player is created. */
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector =
                new DefaultTrackSelector(videoTrackSelectionFactory);
	  
	// Create the player with previously created TrackSelector
        mPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector);

	// Load the default controller
        mPlayerView.setUseController(true);
        mPlayerView.requestFocus();
	  
	// Load the SimpleExoPlayerView with the created player
        mPlayerView.setPlayer(mPlayer);
	  
        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
	  
	// Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(
		getContext(),
                Util.getUserAgent(getContext(), "MyAppName"),
		defaultBandwidthMeter);
	  
	// Produces Extractor instances for parsing the media data.
        ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
	  
	// This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ExtractorMediaSource(
		Uri.parse(videoURL),
                dataSourceFactory,
		extractorsFactory,
		null,
		null);
	  
	// Prepare the player with the source.
        mPlayer.prepare(videoSource);
	  
	// Autoplay the video when the player is ready
        mPlayer.setPlayWhenReady(true);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
	    
	// Release the player when it is not needed
        mPlayer.release();
    }
}
