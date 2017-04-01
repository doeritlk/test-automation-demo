package com.doeritlk.test.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InMemoryDataSource implements DataSource<User> {
    private final List<User> users = Arrays.asList(
            ImmutableUser.builder().username("johnflower30@gmail.com").password("jflower$1").build(),
            ImmutableUser.builder().username("alexmarsh10@gmail.com").password("alexmarsh@1").build()
    );

    @Override
    public List<User> read() {
        return new ArrayList<>(users);
    }
}
