package hu.pte.mik.prog4.potpotzh.repository;

import hu.pte.mik.prog4.potpotzh.entity.RoleEntity;
import hu.pte.mik.prog4.potpotzh.entity.UserEntity;


import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository extends Repository {

    public List<RoleEntity> findRolesByUser(UserEntity user) {
        try(Connection connection = this.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT r.ID, r.kod, r.leiras FROM szerepkor r " +
                "JOIN felhasznalo_szerepkor ur ON r.ID = ur.szerepkor_id " +
                                "JOIN felhasznalo u ON ur.felhasznalo_id = u.ID " +
                                "WHERE u.ID = ?"
        )){

            stmt.setLong(1,user.getId());
            ResultSet rs = stmt.executeQuery();
            List<RoleEntity> roles = new ArrayList<>();
            while (rs.next()){
                roles.add(new RoleEntity(rs.getLong("ID"),
                        rs.getString("kod"),
                        rs.getString("leiras")));
            }
            return roles;
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }

}
