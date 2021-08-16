package sg.edu.rp.c346.id19029863.liningxin_app;
import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String name;
    private String description;
    private int area;
    private int stars;

    public Island(String name, String description, int area, int stars) {
        this.name = name;
        this.description = description;
        this.area = area;
        this.stars = stars;
    }

    public Island(int id, String name, String description, int area, int stars) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.area = area;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getStars() {
        return stars;
    }

    public Island setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @Override
    public String toString() {
        String starsString = "";
      /*  if (stars == 5){
            starsString = "*****";
        } else if (stars == 4){
            starsString = "****";
        }*/

        //or
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return starsString;

    }
}
