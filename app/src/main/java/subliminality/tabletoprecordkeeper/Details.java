package subliminality.tabletoprecordkeeper;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;

import subliminality.tabletoprecordkeeper.character.Avatar;
import subliminality.tabletoprecordkeeper.RecordActivity;
import subliminality.tabletoprecordkeeper.character.Role;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Details.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Details extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    LinearLayout mCharacterClasses;

    private Spinner mClassSpinner;
    private Spinner mRaceSpinner;
    private Spinner mSubRaceSpinner;
    private EditText mAvatarName;
    private Button mAddClass;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param sectionNumber Parameter 2.
     * @return A new instance of fragment Details.
     */
    // TODO: Rename and change types and number of parameters
    public static Details newInstance(String param1, int sectionNumber) {
        Details fragment = new Details();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public Details() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_details, container, false);

        View v = inflater.inflate(R.layout.fragment_details, container, false);

        final Avatar character = RecordActivity.getCharacter();

        mAvatarName = (EditText) v.findViewById(R.id.editAvatarName);
        mAvatarName.setEnabled(false);
        mAvatarName.setClickable(false);

        if (character.getName() == "")
            character.setName(mAvatarName.getText().toString());
        else {
            mAvatarName.setText(character.getName());
        }

        mAvatarName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                character.setName(s.toString());
            }
        });

        CheckBox nameCheckBox = (CheckBox) v.findViewById(R.id.changeName);
        nameCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ){
                    mAvatarName.setEnabled(false);
                    mAvatarName.setClickable(false);
                }
                else {
                    mAvatarName.setEnabled(true);
                    mAvatarName.setClickable(true);
                }

            }
        });


        mRaceSpinner = (Spinner) v.findViewById(R.id.raceSpinner);
        mRaceSpinner.setEnabled(false);
        mRaceSpinner.setClickable(false);

        CheckBox raceCheckBox = (CheckBox) v.findViewById(R.id.changeRace);
        raceCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ){
                    mRaceSpinner.setEnabled(false);
                    mRaceSpinner.setClickable(false);
                }
                else {
                    mRaceSpinner.setEnabled(true);
                    mRaceSpinner.setClickable(true);
                }

            }
        });


        if (character.getRace() == "")
            character.setRace("Dwarf");
        else {
            String[] races = this.getResources().getStringArray(R.array.races);
            int index = Arrays.asList(races).indexOf(character.getRace());
            mRaceSpinner.setSelection(index);
        }

        mRaceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String race = mRaceSpinner.getSelectedItem().toString();
                character.setRace(race);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        mCharacterClasses = (LinearLayout) v.findViewById(R.id.characterList);
        mClassSpinner = (Spinner) v.findViewById(R.id.classSpinner);
        mClassSpinner.setEnabled(false);
        mClassSpinner.setClickable(false);

        mAddClass = (Button) v.findViewById(R.id.addClass);
        mAddClass.setEnabled(false);

        CheckBox classCheckBox = (CheckBox) v.findViewById(R.id.changeClasses);
        classCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( ((CheckBox)v).isChecked() ){
                    mClassSpinner.setEnabled(false);
                    mClassSpinner.setClickable(false);
                    mAddClass.setEnabled(false);
                    for (Role c : character.getClasses())
                        c.disableButtons();
                }
                else {
                    mClassSpinner.setEnabled(true);
                    mClassSpinner.setClickable(true);
                    mAddClass.setEnabled(true);
                    for (Role c : character.getClasses())
                        c.enableButtons();
                }

            }
        });

        Activity main = getActivity();

        for (Role c : character.getClasses()){
            if (c.getLevels() < 0)
                character.removeClass(c.getClassName());
            else {
                mCharacterClasses.addView(c.getClassLayout(main));
                c.disableButtons();
            }
        }


        TextView totalLevel = new TextView(main);
        totalLevel.setId(R.id.TotalLevels);
        totalLevel.setText("Character Level: " + character.getTotalLevels());
        mCharacterClasses.addView(totalLevel);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction("button pressed");
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
        ((RecordActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(String string);
    }

}
