package com.youyoucy.tanghy.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * 图片处理工具
 */
public class ImageUtils {

    private ImageUtils(){
        new Throwable().printStackTrace();
    }

    /**
     * 图片压缩处理
     * @param image 图片数据
     * @return 返回压缩后的数据
     */
    public static Bitmap compressImage(Bitmap image) {
        Bitmap bitmap = compressImage(image,100);
        return bitmap;
    }

    /**
     * 压缩图片
     * @param image 图片数据
     * @param size 将图片压缩的大小单位kb
     * @return 返回压缩后的大小
     */
    public static Bitmap compressImage(Bitmap image,int size) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024 > size) {    //循环判断如果压缩后图片是否大于size的kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }


}
