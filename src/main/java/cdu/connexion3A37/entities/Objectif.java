package cdu.connexion3A37.entities;

import java.util.Date;

public class Objectif {

    private int id ;
    private int user_id;
    private String sexe ;
    private int age ;
    private int height ;
    private int weight ;
    private String activity_level ;
    private String choix ;
    private Date datee;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getDatee() {
        return datee;
    }

    public void setDatee(Date datee) {
        this.datee = datee;
    }

    private int calorie ;

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



    public Objectif( int age,String sexe, int height, int weight, String activity_level, int calorie,String choix) {

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

    public Objectif(int id, int age,String sexe, int height, int weight, String activity_level, int calorie,String choix) {
        this.id = id;
        this.sexe = sexe;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.activity_level = activity_level;
        this.choix = choix;
        this.calorie = calorie;
    }
}
