package hu.pte.mik.prog4.potpotzh.repository;

import hu.pte.mik.prog4.potpotzh.entity.CompanyEntity;
import org.apache.log4j.Logger;

import javax.naming.NamingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyRepository extends Repository{

    private static final Logger LOGGER = Logger.getLogger(CompanyRepository.class);

    public List<CompanyEntity> listAll() {

        try(Connection connection = this.getConnection();
        Statement stmt = connection.createStatement()){

            ResultSet rs = stmt.executeQuery("SELECT  ID, nev, alapitasi_ev, orszag, ismert_termek FROM technologiai_ceg");
            List<CompanyEntity> companies = new ArrayList<>();

            while (rs.next()){
                companies.add(this.mapCompanyEntity(rs));
            }

            return companies;
        } catch (SQLException | NamingException e) {
            LOGGER.error("Hiba a listázáskor: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    private CompanyEntity mapCompanyEntity(ResultSet rs) throws SQLException {
        return new CompanyEntity(
                rs.getLong("ID"),
                rs.getString("nev"),
                rs.getLong("alapitasi_ev"),
                rs.getString("orszag"),
                rs.getString("ismert_termek")
        );
    }

    public CompanyEntity findById(Long id) {
        try(Connection connection = this.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT  ID, nev, alapitasi_ev, orszag, ismert_termek FROM technologiai_ceg WHERE ID = ?")){

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            return rs.next() ? this.mapCompanyEntity(rs) : null;
        } catch (SQLException | NamingException e) {
            LOGGER.error("Hiba a kereséskor: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public CompanyEntity save(CompanyEntity entity) {
        return entity.getId() == null ? this.create(entity) : this.update(entity);
    }

    private CompanyEntity create(CompanyEntity entity) {
        try(Connection connection = this.getConnection();
        PreparedStatement stmt = connection.prepareStatement("INSERT INTO technologiai_ceg (nev, alapitasi_ev, orszag, ismert_termek) VALUES (?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1,entity.getCompanyName());
            stmt.setLong(2, entity.getEstYear());
            stmt.setString(3, entity.getCountry());
            stmt.setString(4, entity.getKnownProducts());

            stmt.executeUpdate();
            ResultSet keys = stmt.getGeneratedKeys();
            return keys.next() ? this.findById(keys.getLong(1)) : null;
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }

    private CompanyEntity update(CompanyEntity entity) {
        try(Connection connection = this.getConnection();
        PreparedStatement stmt = connection.prepareStatement("UPDATE technologiai_ceg SET nev=?, alapitasi_ev=?, orszag=?, ismert_termek=? WHERE ID = ?")){
            stmt.setString(1,entity.getCompanyName());
            stmt.setLong(2,entity.getEstYear());
            stmt.setString(3,entity.getCountry());
            stmt.setString(4,entity.getKnownProducts());

            stmt.executeUpdate();

            return this.findById(entity.getId());
        } catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }


}
