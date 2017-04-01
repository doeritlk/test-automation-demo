package com.doeritlk.test.data;

import org.immutables.value.Value;
import org.immutables.value.Value.Style.BuilderVisibility;

@Value.Immutable
@Value.Style(builderVisibility = BuilderVisibility.PUBLIC)
public interface User {
    String username();

    String password();
}
