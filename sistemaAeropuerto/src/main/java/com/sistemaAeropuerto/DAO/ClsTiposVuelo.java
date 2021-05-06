package com.sistemaAeropuerto.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;


import com.sistemaAeropuerto.Conexion.ConexionBd;
import com.sistemaAeropuerto.Entidades.Tipos_vuelo;

public class ClsTiposVuelo {

	ConexionBd cn = new ConexionBd();
    Connection conexion = cn.RetornarConexion();

    public ArrayList<Tipos_vuelo> MostrarTipos() {
        ArrayList<Tipos_vuelo> companies = new ArrayList<>();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_Tipos()");
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
                Tipos_vuelo tipo = new Tipos_vuelo();
                tipo.setIdTipos_vuelo(rs.getInt("idTipos_vuelo"));
                tipo.setTipo(rs.getString("Tipo"));
                tipo.setPorcentajeDesc(rs.getDouble("PorcentajeDesc"));
                companies.add(tipo);
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return companies;
    }

    public void AgregarTipo(Tipos_vuelo Tipo) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_I_Tipos(?,?)");
            Statement.setString("PTipo", Tipo.getTipo());
            Statement.setDouble("PDescuento", Tipo.getPorcentajeDesc());
            Statement.execute();
            System.out.println("Guardado");
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void BorrarTipo(Tipos_vuelo Tipo) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_D_Tipos(?)");
            Statement.setInt("PidTipos_vuelo", Tipo.getIdTipos_vuelo());
            Statement.execute();
            System.out.println("Eliminado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void ActualizarTipo(Tipos_vuelo Tipo) {
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_U_Tipos(?,?,?)");
            Statement.setInt("PidTipos_vuelo", Tipo.getIdTipos_vuelo());
            Statement.setString("PTipo", Tipo.getTipo());
            Statement.setDouble("PDescuento", Tipo.getPorcentajeDesc());
            Statement.execute();
            System.out.println("Actualizado");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Tipos_vuelo SeleccionarTipo(int idTipo) {
        Tipos_vuelo tipo = new Tipos_vuelo();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_1Tipo(?)");
            Statement.setInt("PidTipo", idTipo);
            ResultSet rs = Statement.executeQuery();
            while (rs.next()) {
                tipo.setIdTipos_vuelo(rs.getInt("idTipos_vuelo"));
                tipo.setTipo(rs.getString("Tipo"));
                tipo.setPorcentajeDesc(rs.getDouble("PorcentajeDesc"));
            }
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return tipo;
    }

}