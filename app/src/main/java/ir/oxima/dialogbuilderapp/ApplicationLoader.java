package ir.oxima.dialogbuilderapp;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import ir.oxima.dialogbuilder.DialogBuilderConfig;

public class ApplicationLoader extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DialogBuilderConfig.builder()
                .setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark))
                .setTitle("Dear user")
                .setActionFontPath("fonts/irsans_m.ttf")
                .setTitleFontPath("fonts/irsans_b.ttf")
                .setMessageFontPath("fonts/irsans_n.ttf");
    }
}
