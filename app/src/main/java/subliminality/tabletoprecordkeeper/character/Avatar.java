package subliminality.tabletoprecordkeeper.character;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shaneluv on 5/24/2015.
 */
public class Avatar {

    private Race race;
    private String name = "";

    private List<Role> classes = new ArrayList<>();

    private List<Skill> skills = new ArrayList<>();

    private List<Spell> spells = new ArrayList<>();

    private List<Consumable> consumables = new ArrayList<>();
    private List<Item> items = new ArrayList<>();

    /*
    ** New Avatar, Blank Canvas
     */
    public Avatar(){
        race = new Race();
    }

    public Avatar(File filename){

    }

    public List<Role> getClasses() {
        return classes;
    }
    public void removeClass(String role){
        classes.remove(role);
    }

    public String getName(){return name;};
    public void setName(String name){ this.name = name;}

    public String getRace(){return race.getRace();}
    public void setRace(String race){
        this.race.setRace(race);
    }

    public int getTotalLevels(){
        int total = 0;
        for (Role c : classes)
            total += c.getLevels();
        return total;
    }

    public int addClass(String classname){

        int success = 0;
        Role newRole = new Role(classname);

        if (!classes.contains(newRole)){
            success = 1;
            classes.add(newRole);
        }

        return success;
    }



}
