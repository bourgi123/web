package entities;

import java.util.Date;

public class SuivieObjectif {
    int obj_id;
     int id;
     int nouvPoid;
     Date date;
    private Objectif objectif;


    public SuivieObjectif( int nouvPoid, Date date, int obj_id ,Objectif objectif) {
        this.nouvPoid = nouvPoid;
        this.date = date;
        this.obj_id = objectif.getId();
        this.objectif = objectif;
    }

    public SuivieObjectif(Integer value, int i, java.sql.Date sqlDate) {

    }


    public int getObj_id() {
        return obj_id;
    }

    public void setObj_id(int obj_id) {
        this.obj_id = obj_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNouvPoid() {
        return nouvPoid;
    }

    public void setNouvPoid(int nouvPoid) {
        this.nouvPoid = nouvPoid;
    }

    public Date getDate() {
        return  date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Objectif getObjectif() {
        return objectif;
    }

    public void setObjectif(Objectif objectif) {
        this.objectif = objectif;
    }

    public SuivieObjectif() {
    }
    public String compareWeight() {
        if (objectif.getWeight() > nouvPoid) {
            return "You have lost weight";
        } else if (objectif.getWeight() < nouvPoid) {
            return "You have gained weight";
        } else {
            return "Your weight is stable";
        }
    }



}
