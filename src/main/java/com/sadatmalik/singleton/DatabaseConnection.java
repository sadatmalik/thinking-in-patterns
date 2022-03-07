package com.sadatmalik.singleton;

public final class DatabaseConnection {

    public static final int MAX_CONNECTIONS = 10;

    private static int count = 0;

    private static int idCount = 0;
    private int id;

    private DatabaseConnection() {
        id = idCount++;
        count++;
    }

    public static DatabaseConnection getConnection() {
        if (count < MAX_CONNECTIONS)
            return new DatabaseConnection();
        return null;
    }

    public int getId() {
        return id;
    }

    public static void releaseConnection(DatabaseConnection c) {
        System.out.println("Releasing db connection id - " + c.getId());
        count--;
    }

}

class ConnectionPoolTest {

    public static void main(String[] args) {
        DatabaseConnection[] dbc = new DatabaseConnection[10];
        for (int i = 0; i < DatabaseConnection.MAX_CONNECTIONS + 3; i++) {
            DatabaseConnection dbConn = DatabaseConnection.getConnection();
            if (dbConn != null) {
                // do DB stuff
                System.out.println("Db connection id - " + dbConn.getId());
                dbc[i] = dbConn;
            } else {
                System.out.println("No connections available");
            }
        }

        DatabaseConnection.releaseConnection(dbc[4]);
        DatabaseConnection dbConn = DatabaseConnection.getConnection();
        System.out.println("Db connection id - " + dbConn.getId());
        dbc[4] = dbConn;
    }


}