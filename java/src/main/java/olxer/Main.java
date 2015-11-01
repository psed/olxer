package olxer;

import java.sql.SQLException;
import javax.xml.bind.JAXBException;
import olxer.threading.NewAdsSearchThread;

public class Main {

    public static void main(String[] args) throws SQLException, JAXBException {

        new Thread(new NewAdsSearchThread()).start();

    }

}
