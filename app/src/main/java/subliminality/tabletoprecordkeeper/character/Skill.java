package subliminality.tabletoprecordkeeper.character;

/**
 * Created by Shaneluv on 5/24/2015.
 */
public class Skill extends Proficiency {

    private String attribute;

    public Skill (String name, boolean proficient, String attribute){
        super(name, proficient);
        this.attribute = attribute;


    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

}
