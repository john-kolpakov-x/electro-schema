package kz.pompei.electro_schema.frontend.ids;

import kz.greetgo.util.RND;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UploadDataToDb {
  public static void main(String[] args) throws Exception {
    new UploadDataToDb().exec();
  }

  private Connection connection;

  private void exec() throws Exception {

    Class.forName("org.postgresql.Driver");

    try (Connection connection = DriverManager.getConnection(
      "jdbc:postgresql://localhost:15432/electro_schema", "electro_schema", "111"
    )) {
      this.connection = connection;
      uploadData();
    }

  }

  private static final int BatchSize = 100_000;

  private void uploadData() throws Exception {

    connection.setAutoCommit(false);
    try (PreparedStatement ps = connection.prepareStatement(
      "insert into client(id, name1, name2, name3, name4, name5) values (?,?,?,?,?,?)")) {

      int totalCount = 0;
      int batchCount = 0;

      for (int i = 0; i < 10_000_000; i++) {

        ps.setInt(1, i + 1);
        ps.setString(2, RND.str(10));
        ps.setString(3, RND.str(15));
        ps.setString(4, RND.str(30));
        ps.setString(5, RND.str(10));
        ps.setString(6, RND.str(10));
        ps.addBatch();

        totalCount++;
        batchCount++;

        if (batchCount >= BatchSize) {
          ps.executeBatch();
          connection.commit();
          batchCount = 0;
          System.out.println("b97kla8idx :: totalCount = " + totalCount);
        }

      }

      if (batchCount > 0) {
        ps.executeBatch();
        connection.commit();
      }

      System.out.println("H5PeLABFzI :: totalCount = " + totalCount);

    } finally {
      connection.setAutoCommit(true);
    }

  }
}
