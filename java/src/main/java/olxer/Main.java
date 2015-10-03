package olxer;

import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import olxer.threading.NewAdsSearchThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException, JAXBException {

        new Thread(new NewAdsSearchThread()).start();

    }

}
