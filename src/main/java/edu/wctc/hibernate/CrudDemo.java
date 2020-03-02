package edu.wctc.hibernate;

import edu.wctc.hibernate.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


public class CrudDemo {
    private SessionFactory factory;

    public CrudDemo() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        CrudDemo demo = new CrudDemo();

        try {
            demo.updateImagesForDonuts();
        } finally {
            demo.close();
        }
    }

    private void close() {
        factory.close();
    }

    private int createMarbleDonut() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut donut = new Donut("Marble", 300);
        LocalDate sepFirst = LocalDate.of(2019, 9, 1);
        donut.setDateAdded(sepFirst);

        session.save(donut);

        session.getTransaction().commit();

        return donut.getId();
    }

    private int createPewaukeeAndStacysShop() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        City city = new City("Pewaukee", "WI");
        session.save(city);

        DonutShop shop = new DonutShop("Stacy's Donuts");
        city.add(shop);
        session.save(shop);

        session.getTransaction().commit();

        return shop.getId();
    }

    private int createPineappleDonutAndReviews() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut newDonut = new Donut("Pineapple", 300);
        DonutReview review1 = new DonutReview("Who puts pineapple in a donut??", 1.5, LocalDate.now());
        DonutReview review2 = new DonutReview("A revelation!", 4.9, LocalDate.now());

        newDonut.add(review1);
        newDonut.add(review2);

        session.save(newDonut);

        session.getTransaction().commit();

        return newDonut.getId();
    }

    private void createStacysShopAndDetail() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        DonutShop shop = new DonutShop("Stacy's Donuts");
        DonutShopDetail detail = new DonutShopDetail(2019, "Waukesha, WI");
        shop.setDetail(detail);

        session.save(shop);

        System.out.println(shop);

        session.getTransaction().commit();
    }

    private void createTwoDonutsForShop(int shopId) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        DonutShop aShop = session.get(DonutShop.class, shopId);

        Donut donut1 = new Donut("Bavarian Créme", 390);
        Donut donut2 = new Donut("Cherry", 285);

        aShop.add(donut1);
        aShop.add(donut2);

        session.save(donut1);
        System.out.println(donut1);
        session.save(donut2);
        System.out.println(donut2);

        session.getTransaction().commit();
    }

    private void deleteDetail() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        DonutShopDetail detail = session.get(DonutShopDetail.class, 1);
        detail.getShop().setDetail(null);
        //detail.setShop(null);

        session.delete(detail);

        session.getTransaction().commit();
    }

    private void deleteDonutById(int donutId) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut doomedDonut = session.get(Donut.class, donutId);

        if (doomedDonut == null) {
            System.out.println("No donut for ID " + donutId);
        } else {
            session.delete(doomedDonut);
        }

        session.getTransaction().commit();
    }

    private void deleteDonutTen() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        session.createQuery("delete from Donut where id = 10").executeUpdate();

        session.getTransaction().commit();
    }

    private void deleteShopAndDetail() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        DonutShop myShop = session.get(DonutShop.class, 301);

        session.delete(myShop);

        //System.out.println(myShop);

        session.getTransaction().commit();
    }

    private void getDonutById(int donutId) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut aDonut = session.get(Donut.class, donutId);

        if (aDonut == null) {
            System.out.println("No donut found for ID " + donutId);
        } else {
            System.out.println(aDonut);
        }

        session.getTransaction().commit();
    }

    private void getDonutsForShop(int shopId) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        DonutShop aShop = session.get(DonutShop.class, shopId);

        for (Donut aDonut : aShop.getDonuts()) {
            System.out.println(aDonut);
        }

        session.getTransaction().commit();
    }

    private void getReviewsForPineappleDonut() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut anotherDonut = session.createQuery("from Donut where name = 'Pineapple'", Donut.class).getSingleResult();

        for (DonutReview review : anotherDonut.getReviews()) {
            System.out.println(review);
        }

        session.delete(anotherDonut);

        session.getTransaction().commit();
    }

    private void listDonutsUnder300Calories() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        List<Donut> list = session.createQuery("from Donut where calories < 300 order by dateAdded desc").getResultList();

        printDonutList(list);

        session.getTransaction().commit();
    }

    private void printDonutList(List<Donut> list) {
        if (list.isEmpty()) {
            System.out.println("No donuts in list");
        } else {
            for (Donut donut : list) {
                System.out.println(donut);
            }
        }
    }

    private void updateAllDonuts() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        session.createQuery("update Donut set calories = calories + 1").executeUpdate();

        session.getTransaction().commit();
    }

    private void updateDate() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut aDonut = session.get(Donut.class, 2);
        aDonut.setDateAdded(LocalDate.of(2019, 1, 1));

        session.getTransaction().commit();
    }

    private void updateDonutById(int donutId) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Donut aDonut = session.get(Donut.class, donutId);
        if (aDonut != null)
            aDonut.setName(aDonut.getName() + " UPDATED");

        session.getTransaction().commit();
    }

    private void updateHighestCalorieDonut() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        List<Donut> list = session.createQuery("from Donut order by calories desc").getResultList();
        list.get(0).setName("Definitely not a Diet Donut");

        session.getTransaction().commit();
    }

    private void updateImageForDonut(String donutName, String imageFilename) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        // Do stuff
        Query query = session.createQuery("from Donut where name = :name");
        query.setParameter("name", donutName);

        Donut donut = (Donut) query.getSingleResult();

        if (donut == null) {
            System.out.println("No donut for name " + donutName);
        } else {
            String imagePath = "images-to-load/";
            imagePath += donut.getShop().getId() == 1 ? "dunkin-donuts/" : "krispy-kreme/";
            imagePath += imageFilename;

            Image image = new Image();

            try {
                Path path = Paths.get(imagePath);
                image.setFile(Files.readAllBytes(path));

                String suffix = imageFilename.toLowerCase().substring(imageFilename.lastIndexOf('.'));

                image.setName(System.currentTimeMillis() + suffix);

                if (suffix.endsWith("png"))
                    image.setMimeType(MediaType.IMAGE_PNG_VALUE);
                else if (suffix.endsWith("jpg") || suffix.endsWith("jpeg"))
                    image.setMimeType(MediaType.IMAGE_JPEG_VALUE);
                else if (suffix.endsWith("gif"))
                    image.setMimeType(MediaType.IMAGE_GIF_VALUE);
                else
                    image.setMimeType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            } catch (IOException e) {
                e.printStackTrace();
            }

            session.save(image);

            donut.setImage(image);

            session.save(donut);
        }

        session.getTransaction().commit();
    }

    private void updateImagesForDonuts() {
        String[] donutNames = {
                "Glazed Donut",
                "French Cruller",
                "Sour Cream",
                "Bavarian Kreme-Filled",
                "Jelly",
                "Original Glazed",
                "Chocolate Iced Glazed",
                "Cake Batter",
                "Apple Fritter"
        };
        String[] fileNames = {
                "glazed.png",
                "french-cruller.png",
                "sour-cream.png",
                "bavarian-kreme-filled.png",
                "jelly.png",
                "original-glazed.png",
                "chocolate-iced-glazed.png",
                "cake-batter.png",
                "apple-fritter.png"
        };
        for (int i = 0; i < donutNames.length; i++) {
            updateImageForDonut(donutNames[i], fileNames[i]);
        }
    }
}
