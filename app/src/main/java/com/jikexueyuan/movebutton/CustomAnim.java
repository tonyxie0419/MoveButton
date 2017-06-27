package com.jikexueyuan.movebutton;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * 自定义动画，3D翻转
 */
public class CustomAnim extends Animation {

    private float mFromDegrees;//开始角度
    private float mToDegrees;//结束角度
    private float mCenterX;//中心X点
    private float mCenterY;//中心Y点

    public CustomAnim(float mFromDegrees, float mToDegrees) {
        super();
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        super.initialize(width, height, parentWidth, parentHeight);
        mCenterX = width / 2.0f;
        mCenterY = height / 2.0f;
        //动画完成后保持完成的状态
        setFillAfter(true);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        //计算每次变化的角度
        float degrees = mFromDegrees + ((mToDegrees - mFromDegrees) * interpolatedTime);

        Camera camera = new Camera();
        Matrix matrix = t.getMatrix();

        //保存坐标原点
        camera.save();
        //以Y轴为旋转中心轴，旋转degrees角度
        camera.rotateY(degrees);
        camera.getMatrix(matrix);
        //恢复到坐标原点
        camera.restore();

        //pre是前乘，参数给出的矩阵乘以当前的矩阵。在旋转之前，先平移(-centerX, -centerY)
        matrix.preTranslate(-mCenterX, -mCenterY);
        //post是后乘，当前的矩阵乘以参数给出的矩阵。旋转之后，再平移(centerX, centerY)
        matrix.postTranslate(mCenterX, mCenterY);
    }
}
