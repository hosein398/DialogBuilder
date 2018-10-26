package ir.oxima.dialogbuilderapp;

import android.app.Dialog;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import ir.oxima.dialogbuilder.DialogBuilder;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btn_normal;
    private Button btn_recycler_view;
    private Button btn_edit_text;
    private CheckBox chk_bottom_sheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(this,R.color.md_grey700));
        toolbar.setTitle(getText(R.string.app_name));
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        chk_bottom_sheet = findViewById(R.id.chk_bottom_sheet);
        btn_normal = findViewById(R.id.btn_normal);
        btn_recycler_view = findViewById(R.id.btn_recycler_view);
        btn_edit_text = findViewById(R.id.btn_edit_text);

        btn_normal.setOnClickListener(this);
        btn_recycler_view.setOnClickListener(this);
        btn_edit_text.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_normal:
                showNormalDialog();
                break;
            case R.id.btn_recycler_view:
                showRecyclerViewDialog();
                break;
            case R.id.btn_edit_text:
                showEditTextDialog();
                break;
        }
    }

    private void showNormalDialog(){
        DialogBuilder dialogBuilder;
        dialogBuilder = chk_bottom_sheet.isChecked() ? new DialogBuilder(this).asBottomSheetDialog(true) : new DialogBuilder(this).asAlertDialog(true);
        dialogBuilder.setMessage("This is simple and flexiable dialog , Do you like it?");
        dialogBuilder.setPositiveButton("Yes", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Tanks !", Toast.LENGTH_SHORT).show();

            }
        });
        dialogBuilder.setNegativeButton("No", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, "Why ?", Toast.LENGTH_SHORT).show();
            }
        });
        dialogBuilder.show();
    }

    private void showRecyclerViewDialog(){
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CountryAdapter countryAdapter = new CountryAdapter(this,getCountries());
        recyclerView.setAdapter(countryAdapter);

        DialogBuilder dialogBuilder;
        dialogBuilder = chk_bottom_sheet.isChecked() ? new DialogBuilder(this).asBottomSheetDialog(true) : new DialogBuilder(this).asAlertDialog(true);
        dialogBuilder.setMessage("Please choose your country : ");
        dialogBuilder.setCustomView(recyclerView);

        dialogBuilder.setNegativeButton("Cancel", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
        dialogBuilder.show();
    }

    private ArrayList<Country> getCountries() {
        ArrayList<Country> countries = new ArrayList<>();
        countries.add(new Country("Afghanistan","93"));
        countries.add(new Country("Albania","355"));
        countries.add(new Country("Australia","61"));
        countries.add(new Country("Belize","501"));
        countries.add(new Country("Bolivia","591"));
        countries.add(new Country("Cambodia","855"));
        countries.add(new Country("Canada","1"));
        countries.add(new Country("Colombia","57"));
        countries.add(new Country("Iran","98"));
        countries.add(new Country("Faroe Islands","298"));
        countries.add(new Country("Guinea-Bissau","245"));
        countries.add(new Country("Hong Kong","852"));
        countries.add(new Country("Latvia","371"));
        countries.add(new Country("Maldives","960"));
        countries.add(new Country("Netherlands Antilles","599"));
        countries.add(new Country("New Caledonia","687"));
        countries.add(new Country("Pakistan","92"));
        countries.add(new Country("Poland","48"));
        countries.add(new Country("Turkey","90"));
        countries.add(new Country("Zimbabwe","263"));
        return countries;
    }

    private void showEditTextDialog(){
        final EditText editText = new EditText(this);
        editText.setHint("Phone number");
        editText.setTextSize(16);

        DialogBuilder dialogBuilder;
        dialogBuilder = chk_bottom_sheet.isChecked() ? new DialogBuilder(this).asBottomSheetDialog(true) : new DialogBuilder(this).asAlertDialog(true);
        dialogBuilder.setMessage("Please enter yor phone number");
        dialogBuilder.setCustomView(editText);
        dialogBuilder.setPositiveButton("Confirm", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
                Toast.makeText(MainActivity.this, editText.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogBuilder.OnClickListener() {
            @Override
            public void onClick(Dialog dialog) {
                dialog.dismiss();
            }
        });
        dialogBuilder.show();
    }

}
