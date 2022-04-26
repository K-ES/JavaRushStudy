import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClass {
    static final Logger rootLogger = LogManager.getRootLogger();
    static final Logger userLogger = LogManager.getLogger(MainClass.class);

    public static void main(String[] args) {
        System.out.println("Всем привет!");

        userLogger.info("раз");
        userLogger.info("два");
        userLogger.fatal("fatal");
        userLogger.error("error");

        rootLogger.info("три");

    }
}
