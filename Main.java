import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        String sql = "SELECT name, students_count FROM Courses";
        NativeQuery q = session.createSQLQuery(sql);
       q.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
       List courses = q.list();
       for(Object o : courses){
           Map m = (Map)o;
           System.out.println(m.get("name") + " : " + m.get("students_count"));
       }

    }
}
