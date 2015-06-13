package subliminality.tabletoprecordkeeper.character;

/**
 * Created by Shaneluv on 5/24/2015.
 */
public class Proficiency {

    private boolean proficient;
    private String name;


    public Proficiency(){

    }

    public Proficiency (String name, boolean proficient){
        this.proficient = proficient;
        this.name = name;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setProficient(boolean proficient) {
        this.proficient = proficient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
