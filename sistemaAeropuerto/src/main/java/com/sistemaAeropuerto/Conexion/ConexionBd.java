package com.sistemaAeropuerto.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexionBd {
	
	 private Connection coonec;
	    
	    public ConexionBd()
	    {
	    	
	    try{
	    	Class.forName("com.mysql.jdbc.Driver");
	        coonec = DriverManager.getConnection("jdbc:mysql://localhost/sistemaaeropuerto","root","root");
	        
	    }catch (Exception e)
	    {
	        System.out.println("ERROR DE CONEXION A LA BD "+ e);
	    }
	    }
	    public Connection RetornarConexion(){   
	        return coonec;   
	    }
	
}
