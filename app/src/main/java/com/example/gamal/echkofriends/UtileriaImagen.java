package com.example.gamal.echkofriends;

/**
 * Created by Gama on 23/05/2018.
 */

import android.graphics.BitmapFactory;
import android.util.Base64;

        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.util.Base64;

        import java.io.ByteArrayOutputStream;

public class UtileriaImagen {

    public static Bitmap decodeBase64(String base64Str) throws IllegalArgumentException
    {
        byte[] decodedBytes = Base64.decode(
                base64Str.substring(base64Str.indexOf(",")  + 1),
                Base64.DEFAULT
        );

        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }

    public static String encodeBase64(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

        return Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
    }
}
