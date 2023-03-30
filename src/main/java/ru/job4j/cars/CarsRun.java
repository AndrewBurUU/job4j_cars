package ru.job4j.cars;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.cars.model.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CarsRun {

    private static final Logger LOG = LoggerFactory.getLogger(CarsRun.class.getName());

    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            var stored = listOf("SELECT p FROM Post p WHERE p.files.size > 0", Post.class, sf);
            for (Post post : stored) {
                System.out.println(post.getDescription());
            }
            Post post = new Post();
            List<File> files = post.getFiles();
            String s = files.get(0).getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static <T> List<T> listOf(String query, Class<T> model, SessionFactory sf) {
        Session session = sf.openSession();
        var rsl = session.createQuery(query, model)
                .getResultList();
        session.close();
        return rsl;
    }

}
