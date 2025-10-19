package com.example.demo.repository;

import com.example.demo.entity.UsuariosEntity;
import org.springframework.jdbc.core.RowMapper;

public class UserEntityRowMapper implements RowMapper<UsuariosEntity> {
    @Override
    public UsuariosEntity mapRow(java.sql.ResultSet rs, int rowNum) throws java.sql.SQLException {
        UsuariosEntity usuario = new UsuariosEntity();
        usuario.setId(rs.getLong("id"));
        usuario.setNombre(rs.getString("nombre"));
        usuario.setApellidos(rs.getString("apellidos"));
        usuario.setEdad(rs.getInt("edad"));
        usuario.setActivado(rs.getBoolean("activado"));
        usuario.setTelefono(rs.getLong("telefono"));
        usuario.setGenero(rs.getInt("genero"));
        usuario.setAficion(rs.getString("aficion"));
        return usuario;
    }
}
