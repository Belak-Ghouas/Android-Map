package com.mzd.myapp.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static com.mzd.myapp.utils.Constant.OPTIM_FOLDER;

public class MediaUtils {

    public static String getRootPath() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), OPTIM_FOLDER);
        if (!mediaStorageDir.exists()){
            try {
                mediaStorageDir.mkdirs();
            }catch (Exception e ) {
                Log.e(OPTIM_FOLDER, "failed to create directory");
                return null;
            }

        }
        return mediaStorageDir.getPath();
    }

    public static String getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path).toString();
    }

}
