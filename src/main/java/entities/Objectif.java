package entities;

import java.util.Date;
import java.util.List;

public class Objectif {

    private int id ;
    private String sexe ;
    private int age ;
    private int height ;
    private int weight ;
    private String activity_level ;
    private String choix ;
    private List<SuivieObjectif> suivieObjectifs;
    private int calorie ;


    public Objectif(int id, String sexe, int age, int height, int weight, String activity_level, String choix, int calorie) {
        this.id = id;
        this.sexe = sexe;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activity_level = activity_level;
        this.choix = choix;
        this.calorie = calorie;
    }




    public Objectif() {

    }


    @Override
    public String toString() {
        return "objectif{" +
                "id=" + id +
                ", sexe='" + sexe + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", activity_level='" + activity_level + '\'' +
                ", choix='" + choix + '\'' +
                ", calorie=" + calorie +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getActivity_level() {
        return activity_level;
    }

    public void setActivity_level(String activity_level) {
        this.activity_level = activity_level;
    }

    public String getChoix() {
        return choix;
    }

    public void setChoix(String choix) {
        this.choix = choix;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }





    public Objectif( String sexe, int age, int height, int weight, String activity_level, String choix, int calorie) {

        this.sexe = sexe;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activity_level = activity_level;
        this.choix = choix;
        this.calorie = calorie;
    }


}
