package com.tao.view;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class RotateAnimation extends Animation {

	private int mCenterX;

	private int mCenterY;

	private Camera mCamera;

	private ImageView mImageView;

	private Bitmap mBitmap;

	public RotateAnimation(ImageView imageView, Bitmap targetBitmap) {
		this.mImageView = imageView;
		this.mBitmap = targetBitmap;
	}

	@Override
	public void initialize(int width, int height, int parentWidth,
			int parentHeight) {
		super.initialize(width, height, parentWidth, parentHeight);
		mCamera = new Camera();
		mCenterX = width / 2;
		mCenterY = height / 2;
		setDuration(300);
		setFillAfter(true);
		setInterpolator(new LinearInterpolator());
	}

	@Override
	protected void applyTransformation(float interpolatedTime, Transformation t) {
		Matrix matrix = t.getMatrix();
		mCamera.save(); //保存当前状态
		if (interpolatedTime > 0.5f) { //当动画进行到一半的时候，替换图片
			mImageView.setImageBitmap(mBitmap);
		} 		
		mCamera.rotateY(180f * interpolatedTime);//旋转180°
		mCamera.getMatrix(matrix);
		matrix.preTranslate(-mCenterX, -mCenterY);
		matrix.postTranslate(mCenterX, mCenterY);
		mCamera.restore(); //载入之前保存的状态
	}

	public void setTargetBitmap(Bitmap targetBitmap) {
		mBitmap = targetBitmap;
	}

	public void setImageView(ImageView imageView) {
		mImageView = imageView;
	}

}
