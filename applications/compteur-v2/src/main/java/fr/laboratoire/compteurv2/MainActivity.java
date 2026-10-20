package fr.laboratoire.compteurv2;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.widget.*;
import android.view.ViewGroup;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;

public final class MainActivity extends Activity {
  private final ArrayList<String> names = new ArrayList<>();
  private final ArrayList<Long> values = new ArrayList<>();
  private int selected = 0;
  private LinearLayout root;
  private TextView display;
  private SharedPreferences prefs;
  @Override public void onCreate(Bundle state) {
    super.onCreate(state);
    prefs = getSharedPreferences("compteur_v2", MODE_PRIVATE);
    load(); render();
  }
  private void load() {
    try {
      JSONArray a = new JSONArray(prefs.getString("counters", "[]"));
      for (int i=0;i<a.length();i++) { JSONObject o=a.getJSONObject(i); names.add(o.getString("name")); values.add(o.getLong("value")); }
    } catch(Exception ignored) {}
    if(names.isEmpty()) { names.add("Compteur 1"); values.add(0L); }
    selected=Math.max(0,Math.min(prefs.getInt("selected",0),names.size()-1));
  }
  private void save() {
    JSONArray a=new JSONArray();
    try { for(int i=0;i<names.size();i++){ JSONObject o=new JSONObject();o.put("name",names.get(i));o.put("value",values.get(i));a.put(o); } } catch(Exception ignored) {}
    prefs.edit().putString("counters",a.toString()).putInt("selected",selected).apply();
  }
  private void button(String label,Runnable action) {
    Button b=new Button(this); b.setText(label); b.setOnClickListener(v->action.run());
    root.addView(b,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,56));
  }
  private void render() {
    root=new LinearLayout(this);root.setOrientation(LinearLayout.VERTICAL);root.setPadding(18,20,18,12);
    TextView title=new TextView(this);title.setText("Compteur V2 — "+names.get(selected));title.setTextSize(22);title.setGravity(Gravity.CENTER);
    root.addView(title);
    display=new TextView(this);display.setId(1001);display.setText(String.valueOf(values.get(selected)));display.setTextSize(64);display.setGravity(Gravity.CENTER);
    root.addView(display,new LinearLayout.LayoutParams(-1,0,1));
    display.setOnClickListener(v->change(1));
    button("+1",()->change(1));button("-1",()->change(-1));
    button("Réinitialiser",()->new AlertDialog.Builder(this).setMessage("Réinitialiser ce compteur ?").setNegativeButton("Annuler",null).setPositiveButton("Confirmer",(d,w)->{values.set(selected,0L);save();render();}).show());
    button("Choisir un compteur",()->new AlertDialog.Builder(this).setItems(names.toArray(new String[0]),(d,w)->{selected=w;save();render();}).show());
    button("Nouveau compteur",()->editName(true));
    button("Renommer",()->editName(false));
    button("Supprimer",()->{if(names.size()==1){Toast.makeText(this,"Un compteur minimum",Toast.LENGTH_SHORT).show();return;}new AlertDialog.Builder(this).setMessage("Supprimer ce compteur ?").setNegativeButton("Annuler",null).setPositiveButton("Supprimer",(d,w)->{names.remove(selected);values.remove(selected);selected=0;save();render();}).show();});
    ScrollView scroll=new ScrollView(this);scroll.addView(root);setContentView(scroll);
  }
  private void editName(boolean create){
    EditText input=new EditText(this);input.setSingleLine(true);input.setText(create?"":names.get(selected));
    new AlertDialog.Builder(this).setTitle(create?"Nouveau compteur":"Renommer").setView(input).setNegativeButton("Annuler",null).setPositiveButton("Valider",(d,w)->{
      String n=input.getText().toString().trim();if(n.isEmpty())return;
      if(create){names.add(n);values.add(0L);selected=names.size()-1;}else names.set(selected,n);
      save();render();
    }).show();
  }
  private void change(int delta) {
    long v=values.get(selected);if((delta>0&&v==Long.MAX_VALUE)||(delta<0&&v==Long.MIN_VALUE))return;
    values.set(selected,v+delta);save();display.setText(String.valueOf(v+delta));
  }
}
