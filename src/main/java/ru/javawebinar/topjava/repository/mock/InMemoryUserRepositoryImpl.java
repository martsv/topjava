package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UserUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * GKislin
 * 15.06.2015.
 */
@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UserUtil.USER_LIST.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        User user = repository.remove(id);
        LOG.info("delete " + id);
        return user != null;
    }

    @Override
    public User save(User user) {
        if (user.isNew()) {
            user.setId(counter.incrementAndGet());
        }
        repository.put(user.getId(), user);
        LOG.info("save " + user);
        return user;
    }

    @Override
    public User get(int id) {
        User user = repository.get(id);
        LOG.info("get " + id);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>(repository.values());
        Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        LOG.info("getAll");
        return list.isEmpty()? Collections.emptyList(): list;
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = repository.values().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst();
        LOG.info("getByEmail " + email);
        return user.orElse(null);
    }
}
