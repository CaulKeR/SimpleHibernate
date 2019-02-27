import org.hibernate.Session;
import java.util.List;

public class Main {

    // Метод добавляет новую запись
    private void addGame(String name, int year) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Game game = new Game();
        game.setName(name);
        game.setYear(year);
        session.save(game);
        session.getTransaction().commit();
    }

    // Метод возвращает список всех записей
    private void listOfGames() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Game> result = (List<Game>) session.createQuery("from Game").list();
        for (Game game : result) {
            System.out.println(game.getId() + " | " + game.getName() + " | " + game.getYear());
        }
        session.getTransaction().commit();
    }

    // Методу удаляет запись
    private void deleteEntity(long id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Game game = session.get(Game.class, id);
        if (game != null) {
            session.delete(game);
        }
        session.getTransaction().commit();
    }

    // Методу обновляет запись
    private void updateEntity(long id,String newName, int newYear) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Game game = session.get(Game.class, id);
        game.setName(newName);
        game.setYear(newYear);
        session.update(game);
        session.getTransaction().commit();
    }

    public static void main(String[] args) {
        Main main = new Main();

        // Добавление
        main.addGame("Hitman ", 2000);

        // Показать всех
        main.listOfGames();

        System.out.println("--------------------------------------------------------");

        // Удаление
        main.deleteEntity(38L);

        // Показать всех
        main.listOfGames();

        System.out.println("--------------------------------------------------------");

        // Изменить игру
        main.updateEntity(16, "CS 1.6", 2001);

        // Показать всех
        main.listOfGames();

    }
}