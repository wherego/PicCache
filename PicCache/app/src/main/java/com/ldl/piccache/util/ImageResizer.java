package com.ldl.piccache.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by ${ldl} on 2017/1/13.
 */

public class ImageResizer {
    public ImageResizer() {
    }

    /**
     * 资源文件获取图片
     * @return
     */
    public Bitmap decodeSampledBitmapFromResource(Resources resource,int resId,int reqWidth,int reqHeight){
        BitmapFactory.Options option=new BitmapFactory.Options();
        option.inJustDecodeBounds=true;
        //返回为null得到了原图宽高
        BitmapFactory.decodeResource(resource,resId,option);
        option.inSampleSize=calculateInSampleSize(option,reqWidth,reqHeight);
        option.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(resource,resId,option);
    }

    /**
     * 计算缩放比例
     * @param option
     * @param reqWidth
     * @param reqHeigth
     * @return
     */
    private int calculateInSampleSize(BitmapFactory.Options option,int reqWidth,int reqHeigth){
        if(reqHeigth==0||reqHeigth==0) {
            return 1;
        }
        int heigth=option.outHeight;
        int width=option.outWidth;
        int inSampleSize=1;
        if(heigth>reqHeigth||width>reqWidth){
            int halfHeigth=heigth/2;
            int halfWidth=width/2;

            while((halfHeigth/inSampleSize)>=reqHeigth&&(halfWidth/inSampleSize)>=reqWidth){
                inSampleSize*=2;
            }
        }
        return inSampleSize;
    }

    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor descriptor,int reqWidth,int reqHeigth){
        BitmapFactory.Options opt=new BitmapFactory.Options();
        opt.inJustDecodeBounds=true;
        BitmapFactory.decodeFileDescriptor(descriptor,null,opt);
        opt.inSampleSize=calculateInSampleSize(opt,reqWidth,reqHeigth);
        opt.inJustDecodeBounds=false;
        return BitmapFactory.decodeFileDescriptor(descriptor,null,opt);

    }
}
