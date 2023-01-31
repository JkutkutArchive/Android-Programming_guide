package com.jkutkut.androidprogramming_guide;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private String currentLanguage;
    private int fontSize = Programming.DEFAULT_FONT_SIZE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFontSize = findViewById(R.id.btnFontSize);

        btnFontSize.setOnClickListener(v -> {
            FontSizeDialog dialog = new FontSizeDialog();
            dialog.show(getSupportFragmentManager(), "FontSizeDialog");
        });

        setLanguage(null); // Default
    }

    public void setFontSize(int newFontSize) {
        if (newFontSize <= 0)
            throw new RuntimeException("Invalid font size");
        fontSize = newFontSize;
        updateLanguage();
    }

    private void setLanguage(String language) {
        currentLanguage = language;
        updateLanguage();
    }

    private void updateLanguage() {
        String codestr = "";
        if (currentLanguage == null)
            codestr = getString(R.string.fragment_programming_example);
        else {
            String[] keys = getResources().getStringArray(R.array.coding_keys);
            String[] values = getResources().getStringArray(R.array.coding_code);
            for (int i = 0; i < keys.length; i++) {
                if (keys[i].equals(currentLanguage))
                    codestr = values[i];
            }
        }
        getSupportFragmentManager().beginTransaction()
            .replace(
                R.id.flLanguage,
                Programming.newInstance(codestr, fontSize)
            ).addToBackStack(null).commit();
    }


    // ******** Option Menu ********
    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        String[] languages = getResources().getStringArray(R.array.coding_keys);
        for (String language : languages)
            m.add(language);
        getMenuInflater().inflate(R.menu.menu_main, m);
        return super.onCreateOptionsMenu(m);
    }

    @Override
    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == R.id.mnExit)
            this.confirmExit();
        else
            setLanguage(item.getTitle().toString());
        return super.onOptionsItemSelected(item);
    }

    private void confirmExit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getText(R.string.exit_confirmation_msg));
        builder.setPositiveButton(
            R.string.exit_confirmation_positive,
            (dialog, which) -> finish()
        );
        builder.setNegativeButton(
            R.string.exit_confirmation_negative,
            (dialog, which) -> dialog.cancel()
        );
        AlertDialog ad = builder.create();
        ad.setCanceledOnTouchOutside(true);
        ad.show();
    }
}