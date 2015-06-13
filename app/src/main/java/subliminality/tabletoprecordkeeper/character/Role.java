package subliminality.tabletoprecordkeeper.character;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import subliminality.tabletoprecordkeeper.R;
import subliminality.tabletoprecordkeeper.RecordActivity;

/**
 * Created by Shaneluv on 5/24/2015.
 */
public class Role{

    private String className;
    private int levels;
    private int idForLevel;
    private Button plus;
    private Button minus;
    private Button remove;

    public Role(){

    }

    public Role(String classname){
        this.className = classname;
        this.levels = 1;
    }

    public int getLevels() {return levels;}

    public void setLevels(int levels) {this.levels = levels;}

    public String getClassName() {return className;}

    public void setClassName(String className) {this.className = className;}

    public Feature[] getFeatures(){
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (!className.equals(role.className)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }

    public void disableButtons(){
        plus.setEnabled(false);
        minus.setEnabled(false);
    }

    public void enableButtons(){
        plus.setEnabled(true);
        minus.setEnabled(true);
    }

    public LinearLayout getClassLayout(Activity main){
        final LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout line = new LinearLayout(main);
        line.setLayoutParams(lparams);



        TextView text = new TextView(main);
        text.setText(className);
        text.setWidth(300);

        line.addView(text);

        TextView lvl = new TextView(main);
        lvl.setText("" + levels);
        lvl.setWidth(60);

        if (idForLevel == 0)
            idForLevel = main.getResources().getIdentifier(className, "id", main.getPackageName());


        lvl.setId(idForLevel);

//        CharSequence text2 = className+ " " + idForLevel + " " + lvl.getId();
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(main, text2, duration);
//        toast.show();


        line.addView(lvl);

        this.plus = new Button(main);
        plus.setText("+");
        //plus.setBackgroundColor(Color.GREEN);
        plus.setHighlightColor(Color.GREEN);

        plus.setMaxHeight(100);
        plus.setMaxWidth(100);
        plus.setLayoutParams(new ViewGroup.LayoutParams(100,100));

        //int height = plus.getHeight();

//        CharSequence text2 = "" +  height;
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(main, text2, duration);
//        toast.show();



        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                levels++;

                Activity host = (Activity) v.getContext();

                TextView lvl = (TextView) host.findViewById(idForLevel);
                lvl.setText("" + levels);

                TextView totLevels = (TextView) host.findViewById(R.id.TotalLevels);
                totLevels.setText("Character Level: " + RecordActivity.character.getTotalLevels());

            }
        });

        minus = new Button(main);
        minus.setText("-");
        //minus.setBackgroundColor(Color.RED);
        minus.setHighlightColor(Color.RED);

        minus.setMaxHeight(100);
        minus.setMaxWidth(100);
        minus.setLayoutParams(new ViewGroup.LayoutParams(100, 100));

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity host = (Activity) v.getContext();
                if (levels > 0) {
                    levels--;


                    TextView lvl = (TextView) host.findViewById(idForLevel);
                    lvl.setText("" + levels);

                    TextView totLevels = (TextView) host.findViewById(R.id.TotalLevels);
                    totLevels.setText("Character Level: " + RecordActivity.character.getTotalLevels());

                    if (levels < 1){

                    }

                }
                //TODO Add option to add the remove button and have it work.

            }
        });

        line.addView(plus);
        line.addView(minus);

        if (levels < 1){
            remove = new Button(main);
            remove.setText("Remove");

            remove.setMaxHeight(100);
            remove.setMaxWidth(300);
            remove.setLayoutParams(new ViewGroup.LayoutParams(300, 100));

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    levels = -1;
                    ((Button) v).setText("removed");
                    ((Button) v).setEnabled(false);
                }
            });

            line.addView(remove);
        }

        if (line == null)
            System.err.println ("XXXXXX ERROR XXXXXXX");

        return line;
    }
}
