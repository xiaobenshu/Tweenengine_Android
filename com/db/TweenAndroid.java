package com.db;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.annotation.SuppressLint;
import android.os.SystemClock;
import android.util.Log;
import android.view.Choreographer;
import android.view.Choreographer.FrameCallback;
import android.widget.Chronometer;
import aurelienribon.tweenengine.TweenManager;

public class TweenAndroid {

	@SuppressLint("NewApi")
	private Choreographer mChoronemetor;
	private TweenManager mTweenManager;
	private long mLastFrameTime;
	private UpdateFerFrame mUpdateCallBackFerFrame =new UpdateFerFrame();
	private static TweenAndroid gForAndroid; 
	
	@SuppressLint("NewApi")
	public TweenAndroid() {
		mChoronemetor = Choreographer.getInstance();
		mTweenManager =  new TweenManager();
		mChoronemetor.postFrameCallback(mUpdateCallBackFerFrame);
	}
	
	public static TweenAndroid Instance(){
		if (gForAndroid == null) {
			gForAndroid =  new TweenAndroid();
		}
		return gForAndroid;
	}
	
	public TweenManager getTweenManager(){
		return mTweenManager;
	}
	
	@SuppressLint("NewApi")
	class UpdateFerFrame implements FrameCallback{	
		@Override
		public void doFrame(long frameTimeNanos) {
			if (mLastFrameTime == 0) {
				mLastFrameTime = SystemClock.uptimeMillis();
			}
			mTweenManager.update(SystemClock.uptimeMillis()-mLastFrameTime);
			mLastFrameTime = SystemClock.uptimeMillis();
			mChoronemetor.postFrameCallback(mUpdateCallBackFerFrame);
		}
		
	}
	
}
