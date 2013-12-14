package lspu.it.humanbody;

import rajawali.RajawaliActivity;
import android.os.Bundle;
import android.view.Menu;

public class HumanbodyActivity extends RajawaliActivity {

	private HumanbodyRenderer mRenderer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mRenderer = new HumanbodyRenderer(this);
		mRenderer.setSurfaceView(mSurfaceView);
		super.setRenderer(mRenderer);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.humanbody, menu);
		return true;
	}

}
