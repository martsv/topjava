package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.model.UserMeal;

import javax.sql.DataSource;

/**
 * Created by mart on 07.07.16.
 */
@Repository
@Profile({Profiles.POSTGRES})
public class PostgresJdbcUserMealRepositoryImpl extends JdbcUserMealRepositoryImpl {

    @Autowired
    public PostgresJdbcUserMealRepositoryImpl(DataSource dataSource) {
        super(dataSource);
        rowMapper = BeanPropertyRowMapper.newInstance(UserMeal.class);
    }
}
