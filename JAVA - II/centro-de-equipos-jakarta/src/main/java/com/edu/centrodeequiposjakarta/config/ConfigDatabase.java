package com.edu.centrodeequiposjakarta.config;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;


public class ConfigDatabase {

    //Url que conecta la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/tienda";

    //Usuario por el cual se conectara
    private static final String USER = "root";

    //Contraseña del usuario para conectarse a la BD
    private static final String PASSWORD = "Naranjo_0312";


    // Configuración del pool de conexiones
    private static BasicDataSource dataSource;


    public static BasicDataSource getInstance() throws SQLException {

        // Si dataSource ya ha sigo instanciada una vez, ya no requiere volver a tener una nueva instancia (Singleton)
        if (dataSource == null){
            dataSource = new BasicDataSource();
            dataSource.setUrl(URL);
            dataSource.setUsername(USER);
            dataSource.setPassword(PASSWORD);
        }
        return dataSource;
    }

    //Metodo por el cual se obtiene la conexion a la BD
    public static Connection getConnection() throws SQLException {

        //Obtiene la instancia de dataSource y realiza la conexion, como los datos ya esta en dataSource este no requiere
        // que se le envien por parametros a diferencia del anterior
        return getInstance().getConnection();
    }
}
