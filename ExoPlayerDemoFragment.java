
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
	String videoURL = "https://raw.githubusercontent.com/quuen2024/LiveGo/refs/heads/queen/newslive.m3u8";
	  String videoURL = "https://raw.githubusercontent.com/quuen2024/LiveGo/refs/heads/queen/newssport.m3u8";
		
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

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MNCTV</title>
    <style>
        html,
        body {
            height: 100%
        }
    </style>
    <script src="https://content.jwplatform.com/libraries/xZ88RwO4.js"></script>
    <div id="player-container"></div>
    <script>
        var player = jwplayer('player-container');
        player.setup({
            width: "100%",
            key: "cLGMn8T20tGvW+0eXPhq4NNmLB57TrscPjd1IyJF84o=",
            height: "100%",
            sources: [{
                file: "https://cdn10jtedge.indihometv.com/atm/DASH/balitv/manifest.mpd"
            }
            ],
            mediaid: 'EqK9JUO2',
            autostart: 'true',
            hlsjsdefault: 'true',
            "playlist": [{
                "file": "https://r-plus.sedotcw3.workers.dev/index.m3u8?id=mnctv",
                "image": "img/tvindo/mnctv.jpg",
                "title": "MNCTV",
                "mediaid": "ddra573",
                "recommendations" : "google.com"
            }, {
                "file": "https://r-plus.sedotcw3.workers.dev/index.m3u8?id=rcti",
                "image": "img/tvindo/rcti.jpg",
                "title": "RCTI",
                "mediaid": "ddrx3v2"
            }, {
                "file": "https://r-plus.sedotcw3.workers.dev/index.m3u8?id=gtv",
                "image": "img/tvindo/gtv.jpg",
                "title": "GTV",
                "mediaid": "ddrx3v2"
            }, {
                "file": "https://raw.githubusercontent.com/quuen2024/m3u8/refs/heads/queen/transt2.m3u8",
                "image": "img/tvindo/inews.jpg",
                "title": "TransTv",
                "mediaid": "ddrx3v2"
					  }, {
                }, {
                "file": "https://raw.githubusercontent.com/quuen2024/m3u8/refs/heads/queen/trans72.m3u8",
                "image": "img/tvindo/inews.jpg",
                "title": "Trans7",
                "mediaid": "ddrx3v2"
            }]
        }).on('play', function (event) {
            console.log('playing');
        });

    </script>
</head>

<body>

</body>

</html>

