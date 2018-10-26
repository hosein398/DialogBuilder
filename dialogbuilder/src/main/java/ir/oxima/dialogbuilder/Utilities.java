package ir.oxima.dialogbuilder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;

import java.util.Hashtable;

public class Utilities {

    private static final Hashtable<String, Typeface> typefaceCache = new Hashtable<>();

    public static int dp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static Typeface getTypeface(Context context,String assetPath) {
        synchronized (typefaceCache) {
            if (!typefaceCache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(context.getAssets(), assetPath);
                    typefaceCache.put(assetPath, t);
                } catch (Exception e) {
                    return null;
                }
            }
            return typefaceCache.get(assetPath);
        }
    }

}
