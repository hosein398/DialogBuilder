package ir.oxima.dialogbuilder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DialogBuilder {


    private Context context;
    private boolean isAlertDailog;
    private TextView txt_title;
    private TextView txt_message;
    private Dialog dialog;
    private Button btn_ok;
    private Button btn_cancel;
    private ViewGroup container_custom_view;
    private OnClickListener onClickListener;

    public interface OnClickListener{
        void onClick(Dialog dialog);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public DialogBuilder(Context context) {
        if(context == null){
            return;
        }
        this.context = context;


    }

    public DialogBuilder asBottomSheetDialog(boolean canelable){
        isAlertDailog = false;
        if(context == null){
            return this;
        }
        dialog = new BottomSheetDialog(context);
        if(context == null || dialog == null){
            return this;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_dialog_builder, null);
        dialog.setContentView(view);
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setPeekHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, context.getResources().getDisplayMetrics()));
        dialog.setCancelable(canelable);
        initViews(view);
        return this;
    }

    public DialogBuilder asAlertDialog(boolean canelable){
        isAlertDailog = true;
        if(context == null){
            return this;
        }
        AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(context);
        if(context == null || mAlertDialog == null){
            return this;
        }
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_dialog_builder, null);
        mAlertDialog.setView(view);
        mAlertDialog.setCancelable(canelable);
        dialog = mAlertDialog.create();
        initViews(view);
        return this;
    }

    private void initViews(View view) {
        txt_title = view.findViewById(R.id.txt_title);
        txt_message = view.findViewById(R.id.txt_message);
        container_custom_view = view.findViewById(R.id.container_custom_view);
        btn_cancel = view.findViewById(R.id.btn_cancel);
        btn_ok = view.findViewById(R.id.btn_ok);
        btn_cancel.setVisibility(View.GONE);
        btn_ok.setVisibility(View.GONE);
        setConfig();
    }

    private void setConfig() {
        DialogBuilderConfig config = DialogBuilderConfig.builder();
        if(config.getTitle() != null){
            txt_title.setText(config.getTitle());
        }

        if(config.getTitleFontPath() != null){
            txt_title.setTypeface(Utilities.getTypeface(context,config.getTitleFontPath()));
        }

        if(config.getMessageFontPath() != null){
            txt_message.setTypeface(Utilities.getTypeface(context,config.getMessageFontPath()));
        }

        if(config.getActionFontPath() != null){
            btn_cancel.setTypeface(Utilities.getTypeface(context,config.getActionFontPath()));
            btn_ok.setTypeface(Utilities.getTypeface(context,config.getActionFontPath()));
        }

        if (config.getColor() != 0){
            txt_title.setTextColor(config.getColor());
            btn_ok.setTextColor(config.getColor());
            btn_cancel.setTextColor(config.getColor());
        }
    }

    public DialogBuilder show(){
        if(dialog == null || dialog.isShowing()){
            return this;
        }

        if (Build.VERSION.SDK_INT >= 21 && isAlertDailog) {
            dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.popup_fixed_alert));
        }

        dialog.show();
        return this;
    }

    public DialogBuilder dismiss(){
        if(dialog == null || !dialog.isShowing()){
            return this;
        }
        dialog.dismiss();
        return this;
    }


    public boolean isShowing(){
        if(dialog == null){
            return false;
        }
        return dialog.isShowing();
    }

    public DialogBuilder setTitle(CharSequence title){
        if(title == null){
            txt_title.setVisibility(View.GONE);
            return this;
        }
        txt_title.setVisibility(View.VISIBLE);
        txt_title.setText(title);
        return this;
    }

    public DialogBuilder setMessage(CharSequence msg){
        if(msg == null){
            txt_message.setVisibility(View.GONE);
            return this;
        }
        txt_message.setVisibility(View.VISIBLE);
        txt_message.setText(msg);
        return this;
    }

    public DialogBuilder setMessageGravity(int gravity){
        txt_message.setGravity(gravity);
        return this;
    }

    public DialogBuilder setCustomView(View view){
        container_custom_view.addView(view);
        return this;
    }

    public DialogBuilder setCustomView(View view, ViewGroup.LayoutParams layoutParams){
        container_custom_view.addView(view,layoutParams);
        return this;
    }

    public DialogBuilder setTitleColor(int color){
        txt_title.setTextColor(color);
        return this;
    }

    public void setPositiveButton(CharSequence text,final OnClickListener onClickListener){
        if(text != null){
            btn_ok.setVisibility(View.VISIBLE);
            btn_ok.setText(text);

            btn_ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null){
                        onClickListener.onClick(dialog);
                        return;
                    }
                }
            });
        }else {
            btn_ok.setVisibility(View.GONE);
        }
    }

    public void setNegativeButton(CharSequence text,final OnClickListener onClickListener){
        if(text != null){
            btn_cancel.setVisibility(View.VISIBLE);
            btn_cancel.setText(text);
            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onClickListener != null){
                        onClickListener.onClick(dialog);
                        return;
                    }
                }
            });
            return;
        }
        btn_cancel.setVisibility(View.GONE);
    }


}
