package fr.laboratoire.calculatricetest;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;

public final class MainActivity extends Activity {
    private final CalculatorEngine engine = new CalculatorEngine();
    private TextView result;

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(16, 24, 16, 24);
        root.setBackgroundColor(0xFFF6F7FB);
        result = new TextView(this);
        result.setId(R.id.result_view);
        result.setText(engine.display());
        result.setTextSize(42);
        result.setTextColor(0xFF17202A);
        result.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        result.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
        result.setSingleLine(true);
        root.addView(result, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));

        String[][] keys = {
            {"7","8","9","÷"},
            {"4","5","6","×"},
            {"1","2","3","-"},
            {"C","0",".","+"},
            {"="}
        };
        for (String[] row : keys) {
            LinearLayout line = new LinearLayout(this);
            for (String key : row) {
                Button button = new Button(this);
                button.setText(key);
                button.setContentDescription("Touche " + key);
                button.setTextSize(24);
                button.setOnClickListener(v -> { engine.press(key); result.setText(engine.display()); });
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, 72, key.equals("=") ? 4f : 1f);
                params.setMargins(2, 2, 2, 2);
                line.addView(button, params);
            }
            root.addView(line, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
        setContentView(root);
    }
}
