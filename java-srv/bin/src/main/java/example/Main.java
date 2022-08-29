package example;

import org.apache.log4j.PropertyConfigurator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.DesignDocument;

public class Main {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  public static void main(String[] args) {

    String PWD = System.getenv("PWD");

    PropertyConfigurator.configure(PWD + "/src/main/resources/log4j.xml");

    try {
      HttpClient httpClient = new StdHttpClient.Builder()
      .url("http://db:5984")
      .username("maria")
      .password("pass")
      .build();
      CouchDbInstance dbInstance = new StdCouchDbInstance(httpClient);
      //--------------- Creating database----------------------------//
      CouchDbConnector db = dbInstance.createConnector("javatpoint", true);
      //db.createDatabaseIfNotExists();
      //--------------- Creating Document----------------------------//
      DesignDocument dd = new DesignDocument("light");
      db.create(dd);
      logger.info("Connected");
    } catch(Exception e) {
      logger.error("Not Connected");
    }
  }
}
