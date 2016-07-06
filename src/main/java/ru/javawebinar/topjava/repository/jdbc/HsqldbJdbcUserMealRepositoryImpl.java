package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by mart on 06.07.16.
 */
@Repository
@Profile({Profiles.HSQLDB})
public class HsqldbJdbcUserMealRepositoryImpl extends JdbcUserMealRepositoryImpl {

    @Autowired
    public HsqldbJdbcUserMealRepositoryImpl(DataSource dataSource) {
        super(dataSource);
        rowMapper = new RowMapper<UserMeal>() {
            @Override
            public UserMeal mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserMeal(rs.getInt("id"),
                        rs.getTimestamp("date_time").toLocalDateTime(),
                        rs.getString("description"),
                        rs.getInt("calories"));
            }
        };
    }
}