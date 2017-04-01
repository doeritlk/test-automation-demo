package com.rit.test.data;

import java.util.List;

public class DatabaseDataSource implements DataSource<User> {
    @Override
    public List<User> read() {
        throw new UnsupportedOperationException("not yet implemented!");
    }
}
