package cdu.connexion3A37.tests;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClass extends Application {


    public static void main(String[] args) {
       /* Myconnection mc = new Myconnection();
        ServiceObjectif so = new ServiceObjectif();
      //  objectif objectif= new objectif(51,"Hommme",89,44,"asba",900,"perte");
        //objectif.setUser_id(1);
    //    System.out.println(objectif.getUser_id());
     //   so.add(objectif);
   // System.out.println(so.getAll());
        objectif objectif1=so.getById(2);
    //so.delete(objectif1);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Donner l'Id à modifier: ");
        int idUpdate = scanner.nextInt();

        objectif menuToUpdate = so.getById(idUpdate);

        if (menuToUpdate != null) {
            System.out.println("id menu: " + menuToUpdate.getId());
            System.out.println("sex " + menuToUpdate.getSexe());
            System.out.println("AGE " + menuToUpdate.getAge());
            System.out.println("HEIGHT " + menuToUpdate.getHeight());
            System.out.println(" WEIGHT " + menuToUpdate.getWeight());
            System.out.println(" ACTIVITY " + menuToUpdate.getActivity_level());
            System.out.println(" choix " + menuToUpdate.getChoix());
            System.out.println(" calorie " + menuToUpdate.getCalorie());

            System.out.println("sex: ");
            String updatesex = scanner.next();

            System.out.println("AGE: ");
            int updatedAge = scanner.nextInt();

            System.out.println("HEIGHT: ");
            int updatedHeight = scanner.nextInt();

            System.out.println("WEIGHT: ");
            int updatedWeight = scanner.nextInt();

            System.out.println("ACTIVITY: ");
            String updatedACTIVITY = scanner.next();

            System.out.println("choix: ");
            String updatedchoix = scanner.next();
            System.out.println("calorie: ");
            int updatedcalorie = scanner.nextInt();

            menuToUpdate.setId(menuToUpdate.getId());
            menuToUpdate.setSexe(updatesex);
            menuToUpdate.setAge(updatedAge);
            menuToUpdate.setHeight(updatedHeight);
            menuToUpdate.setWeight(updatedWeight);
            menuToUpdate.setActivity_level(updatedACTIVITY);
            menuToUpdate.setChoix(updatedchoix);
            menuToUpdate.setCalorie(updatedcalorie);

            so.update(menuToUpdate);

        } else {
            System.out.println("Menu: " + idUpdate + "not found.");
        }*/
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CRUD.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            stage.setTitle("CRUD");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

