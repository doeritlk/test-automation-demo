package com.doeritlk.test.data;

import java.util.List;

public interface DataSource<T> {
    List<T> read();
}
