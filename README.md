# Dialog Builder

DialogBuilder is simple library for show easily custom AlertDialog and BottomSheet in android. 

<img align="top" width="50%" src="https://www.oxima.ir/resources/images/articles/source_hub/dialogbuilder1.jpg">

## Features

* MinSdk 14
* set font for title,message and button
* set color for title and button
* set cancelable dialog
* add custom view in dialog
* show dialog as an AlertDialog or BottomSheet 

## Getting Started

### Gradle setup

In your gradle project :

```xml
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
In your gradle module :

```xml
implementation 'com.github.hosein398:DialogBuilder:0.1.0'
```

### Usage

you can use DialogBuilder very easy , create object from DialogBuilder like this:

```java
DialogBuilder dialogBuilder = new DialogBuilder(this).asAlertDialog(true);
or
DialogBuilder dialogBuilder = new DialogBuilder(this).asBottomSheetDialog(true);
```
You can set message and title for your dialog :

```java
dialogBuilder.setMessage("Your message");
dialogBuilder.setTitle("Your Title");
```

And you can add Positive and Negative Button for dialog :
```java
dialogBuilder.setPositiveButton("Ok", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
		
dialogBuilder.setNegativeButton("Cancel", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
```
And end you should call show() method:
```java
dialogBuilder.show();
```
If you want set custom view in your dialog you can do it very easily :
```java
EditText editText = new EditText(this);
editText.setHint("Phone number");
editText.setTextSize(16);

dialogBuilder.setCustomView(editText); // Your custom view
```

## Config

You can change default config by DialogBuilderConfig , you shoud call this in Application class
```java
DialogBuilderConfig.builder()
		.setColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark))
                .setTitle("Dear user")
                .setActionFontPath("fonts/irsans_m.ttf")
                .setTitleFontPath("fonts/irsans_b.ttf")
                .setMessageFontPath("fonts/irsans_n.ttf");
```


## Authors

* **Hosein Raeisi** - *work at* - [Oxima](https://oxima.ir)
See also the list of [projects](https://github.com/hosein398?tab=repositories).

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
