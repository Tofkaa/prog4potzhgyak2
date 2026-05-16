package hu.pte.mik.prog4.potpotzh.repository;

import hu.pte.mik.prog4.potpotzh.entity.UserEntity;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository extends  Repository{

    public UserEntity findByUsername(String username) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT ID, felhasznalonev, jelszo FROM felhasznalo WHERE  felhasznalonev = ?")){
            stmt.setString(1,username);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                return new UserEntity(rs.getLong("ID"),
                        rs.getString("felhasznalonev"),
                        rs.getString("jelszo"));
            }
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
