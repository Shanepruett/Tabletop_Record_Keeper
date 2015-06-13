package subliminality.tabletoprecordkeeper;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import subliminality.tabletoprecordkeeper.character.Avatar;


public class StartingActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

/*        Button startButton = (Button) findViewById(R.id.button);
        startButton.setOnClickListener(new AdapterView.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });*/


    }

    public void startNewCharacter(View v){

        Toast.makeText(getApplicationContext(), "Starting New Avatar",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this,RecordActivity.class);


        intent.putExtra("character", "");
        startActivity(intent);
    }

    public void loadCharacter(View v){

    }

    public void exitApplication(View v) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_starting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
/*        if (id*//* == R.id.action_settings) {
            return true;
        }*/

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_main_menu:
                return true;
            case R.id.action_exit:
                exitApplication(findViewById(R.id.action_bar));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
