package fr.laboratoire.compteursimple;

import android.app.Activity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public final class MainActivity extends Activity {
    private long count;
    private int step;
    private Button stepButton;
    private TextView counter;
    private SharedPreferences preferences;

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        preferences = getSharedPreferences("counter", MODE_PRIVATE);
        count = preferences.getLong("value", 0L);
        step = preferences.getInt("step", 1);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 36, 24, 36);
        root.setBackgroundColor(0xFFF7F8FA);

        TextView heading = new TextView(this);
        heading.setText("Compteur simple");
        heading.setTextSize(25);
        heading.setTextColor(0xFF25304A);
        heading.setGravity(Gravity.CENTER);
        root.addView(heading, new LinearLayout.LayoutParams(-1, 70));

        counter = new TextView(this);
        counter.setId(R.id.counter_value);
        counter.setText(Long.toString(count));
        counter.setTextSize(68);
        counter.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        counter.setTextColor(0xFF18243A);
        counter.setGravity(Gravity.CENTER);
        root.addView(counter, new LinearLayout.LayoutParams(-1, 0, 1));
        counter.setContentDescription("Ajouter");
        counter.setOnClickListener(v -> change(step));
        addButton(root, "Ajouter", () -> change(step));
        stepButton = addButton(root, "Pas : " + step, () -> {
            step = step == 1 ? 2 : step == 2 ? 5 : step == 5 ? 10 : 1;
            preferences.edit().putInt("step", step).apply();
            stepButton.setText("Pas : " + step);
        });

        addButton(root, "+1", () -> change(step));
        addButton(root, "-1", () -> change(-step));
        addButton(root, "Remettre à zéro", () -> setCount(0L));
        setContentView(root);
    }

    private Button addButton(LinearLayout root, String label, Runnable action) {
        Button button = new Button(this);
        button.setText(label);
        button.setContentDescription(label);
        button.setTextSize(22);
        button.setOnClickListener(v -> action.run());
        root.addView(button, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 76));
        return button;
    }

    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) { change(step); return true; }
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) { change(-step); return true; }
        return super.onKeyDown(keyCode, event);
    }

    private void change(int delta) {
        if (delta > 0 && count > Long.MAX_VALUE - delta) return;
        if (delta < 0 && count < Long.MIN_VALUE - delta) return;
        setCount(count + delta);
    }

    private void setCount(long value) {
        count = value;
        counter.setText(Long.toString(count));
        preferences.edit().putLong("value", count).apply();
    }
}
