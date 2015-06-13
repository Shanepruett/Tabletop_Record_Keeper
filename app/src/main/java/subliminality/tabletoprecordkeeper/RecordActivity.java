package subliminality.tabletoprecordkeeper;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.File;

import subliminality.tabletoprecordkeeper.character.Avatar;
import subliminality.tabletoprecordkeeper.character.Role;


public class RecordActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, Details.OnFragmentInteractionListener{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;



    public static Avatar character;

    public static Avatar getCharacter(){
        return character;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        String filename = intent.getStringExtra("character");

        if (filename.matches(""))
            character = new Avatar();
        else
            character = new Avatar(new File(filename));


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));



    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        if (position == 1) {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, Details.newInstance("blah1", position + 1))
                    .commit();
        }
        else {
            fragmentManager.beginTransaction()
                    .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                    .commit();
        }
    }

    public void addClass(View v){

        int check = 0;

        String classname= ((Spinner) findViewById(R.id.classSpinner)).getSelectedItem().toString();

        if (!classname.matches("Classes:")) {
            check = character.addClass(classname);
        }

        if (check > 0){

            LinearLayout mCharacterClasses = (LinearLayout) findViewById(R.id.characterList);
            mCharacterClasses.removeAllViews();


            //setContentView(R.layout.fragment_details);
            mCharacterClasses = (LinearLayout) findViewById(R.id.characterList);

            character = RecordActivity.getCharacter();

            for (Role c : character.getClasses()){
                mCharacterClasses.addView(c.getClassLayout(this));
            }

            TextView totalLevel = new TextView(this);
            totalLevel.setId(R.id.TotalLevels);
            totalLevel.setText("Character Level: " + character.getTotalLevels());
            mCharacterClasses.addView(totalLevel);

        }

    }

    public void onSectionAttached(int number) {

        mTitle = getString(R.string.title_section1 + (number - 1));

/*        switch (number) {



            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
        }*/
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /*
    **  Detail Fragment Interaction method
     */
    public void onFragmentInteraction(String string){


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /* if (id == R.id.action_settings) {
            return true;
        }*/

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_main_menu:
                return true;
            case R.id.action_exit:
                exitApplication();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void exitApplication() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((RecordActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
