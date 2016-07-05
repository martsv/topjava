package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.Profiles;

/**
 * Created by mart on 05.07.16.
 */
@ActiveProfiles({Profiles.ACTIVE_DB,Profiles.DATAJPA})
public class DataJpaUserServiceTest extends UserServiceTest {
}
