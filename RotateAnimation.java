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
		mCamera.save(); //���浱ǰ״̬
		if (interpolatedTime > 0.5f) { //���������е�һ���ʱ���滻ͼƬ
			mImageView.setImageBitmap(mBitmap);
		} 		
		mCamera.rotateY(180f * interpolatedTime);//��ת180��
		mCamera.getMatrix(matrix);
		matrix.preTranslate(-mCenterX, -mCenterY);
		matrix.postTranslate(mCenterX, mCenterY);
		mCamera.restore(); //����֮ǰ�����״̬
	}

	public void setTargetBitmap(Bitmap targetBitmap) {
		mBitmap = targetBitmap;
	}

	public void setImageView(ImageView imageView) {
		mImageView = imageView;
	}

}
