package com.sistemaAeropuerto.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.sistemaAeropuerto.Conexion.ConexionBd;
import com.sistemaAeropuerto.Entidades.Avion;
import com.sistemaAeropuerto.Entidades.Clases;

public class ClsClase {

	ConexionBd cn = new ConexionBd();
    Connection conexion = cn.RetornarConexion();

    public ArrayList<Clases> MostrarClase(int idAvion) {
        ArrayList<Clases> clases = new ArrayList<>();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_Clase(?)");
            Statement.setInt("PidAvion", idAvion);
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
                Clases clase = new Clases();
                clase.setIdClase(rs.getInt("idClases"));
                clase.setnAsientos(rs.getInt("nAsientos"));
                clase.setNombreClase(rs.getString("nombreClase"));
                clase.setPorcentajeEPrecio(rs.getDouble("porcentajeEPrecio"));
                clase.setIdAvion(rs.getInt("idAvion"));
                clases.add(clase);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return clases;
    }

    public void AgregarClase(Clases clase) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_I_Clase(?,?,?,?)");
            Statement.setString("PnombreClase", clase.getNombreClase());
            Statement.setInt("PnAsientos", clase.getnAsientos());
            Statement.setInt("PidAvion", clase.getIdAvion());
            Statement.setDouble("PPorcentajeEprecio", clase.getPorcentajeEPrecio());
            Statement.execute();
            System.out.println("Guardado");
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void BorrarClase(Clases clase) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_D_Clase(?)");
            Statement.setInt("PidClase", clase.getIdClase());
            Statement.execute();
            System.out.println("Eliminado");
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public void ActualizarClase(Clases clase) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_U_Clase(?,?,?,?,?)");
            Statement.setString("PnombreClase", clase.getNombreClase());
            Statement.setInt("PnAsientos", clase.getnAsientos());
            Statement.setInt("PidAvion", clase.getIdAvion());
            Statement.setDouble("PPorcentajeEprecio", clase.getPorcentajeEPrecio());
            Statement.setDouble("PidClase", clase.getIdClase());
            Statement.execute();
            System.out.println("Actualizado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public Clases SeleccionarClas(int idClase) {
       Clases clase = new Clases();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_1Clase(?)");
            Statement.setInt("PidClases", idClase);
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
                clase.setIdClase(rs.getInt("idClases"));
                clase.setnAsientos(rs.getInt("nAsientos"));
                clase.setNombreClase(rs.getString("nombreClase"));
                clase.setPorcentajeEPrecio(rs.getDouble("porcentajeEPrecio"));
                clase.setIdAvion(rs.getInt("idAvion"));
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return clase;
    }
	
    public int MaximoAsientosDisponibles(int idAvion) {
    	ClsAvion clsAvion = new ClsAvion();
    	Avion avion = new Avion();
    	avion = clsAvion.SeleccionarAvion(idAvion);
        int Asientos = avion.getCapacidad();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_Clase(?)");
            Statement.setInt("PidAvion", idAvion);
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
            	Asientos = Asientos -(rs.getInt("nAsientos"));
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return Asientos;
    }
    
}
