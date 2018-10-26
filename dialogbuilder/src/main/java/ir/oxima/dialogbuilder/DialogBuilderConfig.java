package ir.oxima.dialogbuilder;

public class DialogBuilderConfig {

    private static DialogBuilderConfig builder;
    private String titleFontPath;
    private String messageFontPath;
    private String actionFontPath;
    private CharSequence title;
    private int color;

    private DialogBuilderConfig(){
    }

    public static DialogBuilderConfig builder(){
        if (builder == null){
            builder = new DialogBuilderConfig();
        }
        return builder;
    }

    public int getColor() {
        return color;
    }

    public DialogBuilderConfig setColor(int color) {
        this.color = color;
        return this;
    }

    public CharSequence getTitle() {
        return title;
    }

    public DialogBuilderConfig setTitle(CharSequence title) {
        this.title = title;
        return this;
    }

    public String getTitleFontPath() {
        return titleFontPath;
    }

    public DialogBuilderConfig setTitleFontPath(String titleFontPath) {
        this.titleFontPath = titleFontPath;
        return this;
    }

    public String getMessageFontPath() {
        return messageFontPath;
    }

    public DialogBuilderConfig setMessageFontPath(String messageFontPath) {
        this.messageFontPath = messageFontPath;
        return this;
    }

    public String getActionFontPath() {
        return actionFontPath;
    }

    public DialogBuilderConfig setActionFontPath(String actionFontPath) {
        this.actionFontPath = actionFontPath;
        return this;
    }
}
