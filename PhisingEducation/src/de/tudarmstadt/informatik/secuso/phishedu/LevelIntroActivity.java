package de.tudarmstadt.informatik.secuso.phishedu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import de.tudarmstadt.informatik.secuso.phishedu.backend.BackendController;

/**
 * 
 * @author Gamze Canova This class covers the awareness part of the app This
 *         Activity should only be invoked if the user has not done this part
 *         before
 */
public class LevelIntroActivity extends SwipeActivity {
	private ActionBar ab;

	protected static int[][] levelLayoutIds = {
			{ 
				R.layout.level_00_intro_00, 
				R.layout.level_00_intro_01 
				},
			{ 
				R.layout.level_01_splash,
				R.layout.level_01_intro_00,
				R.layout.level_01_intro_01,
				},
			{ 
				R.layout.level_02_splash, 
				R.layout.level_02_intro_00,
				R.layout.level_02_intro_01, 
				R.layout.level_02_intro_02,
				R.layout.level_02_intro_03, 
				R.layout.level_02_intro_04,
				R.layout.level_02_intro_05, 
				R.layout.level_02_intro_06,
				R.layout.level_02_intro_07, 
				R.layout.level_02_intro_08,
				R.layout.level_02_intro_09, 
				R.layout.level_02_intro_10, },

			{ 
				R.layout.level_03_splash, 
				R.layout.level_03_intro_00,
				R.layout.level_03_intro_01, 
				R.layout.level_03_intro_02,
				R.layout.level_03_intro_03 },
			{ 
				R.layout.level_04_splash, 
				R.layout.level_04_intro_00,
				R.layout.level_04_intro_01, 
				R.layout.level_04_intro_02,
				R.layout.level_04_intro_03 },
			{ 
				R.layout.level_05_splash,
				R.layout.level_05_intro_00, 
				R.layout.level_05_intro_01,
				R.layout.level_05_intro_02 },
			{ 
				R.layout.level_06_splash,	
				R.layout.level_06_intro_00, 
				R.layout.level_06_intro_01,
				R.layout.level_06_intro_02 },
				{ 
					R.layout.level_07_splash,	
					R.layout.level_07_intro_00, 
					R.layout.level_07_intro_01,
					R.layout.level_07_intro_02,
					R.layout.level_07_intro_03,
					R.layout.level_07_intro_04,
					R.layout.level_07_intro_05,
					R.layout.level_07_intro_06} };

	public int real_level = 0;
	public int index_level = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.real_level = getIntent().getIntExtra(Constants.EXTRA_LEVEL,
				0);
		this.index_level = Math.min(this.real_level, levelLayoutIds.length - 1);

		super.onCreate(savedInstanceState);
	}

	public void onStartClick(View view){
		this.onStartClick();
	}
	
	protected void onStartClick() {
		Class next_activity= URLTaskActivity.class;
		if (this.real_level == 0) {
			next_activity=AwarenessActivity.class;
		} else if (this.real_level == 1) {
			next_activity=FindAddressBarActivity.class;
		}
		Intent levelIntent = new Intent(this, next_activity);
		levelIntent.putExtra(Constants.EXTRA_LEVEL, this.real_level);
		startActivity(levelIntent);
	}

	@Override
	protected String startButtonText() {
		return "Start Level";
	}

	@Override
	protected int getPageCount() {
		return this.levelLayoutIds[this.index_level].length;
	}

	@Override
	protected View getPage(int page, LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {

		setTitles();

		return inflater.inflate(this.levelLayoutIds[this.index_level][page],
				container, false);
	}

	private void setTitles() {
		ab = getSupportActionBar();
		String title = getString(Constants.levelTitlesIds[this.real_level]);
		String subtitle = getString(Constants.levelSubtitlesIds[this.real_level]);

		if (!title.equals(subtitle)) {
			// if subtitle and title are different, subtitle is set
			ab.setSubtitle(subtitle);
		}
		// title is set in anyway
		ab.setTitle(title);

		// if awareness is shown - no icon change
		if (this.real_level > 0) {
			ab.setIcon(getResources().getDrawable(R.drawable.emblem_library));
		}
		
	}
	
	/**
	 * User is getting back to the main menu from the introductionary texts.
	 */
	@Override
	public void onBackPressed() {
		NavUtils.navigateUpFromSameTask(this);
		return;		
	}

}
