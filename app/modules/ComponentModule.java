package modules;

import database.*;
import com.google.inject.AbstractModule;

public class ComponentModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DatabaseAdapter.class).to(InMemoryHashDatabaseAdapter.class);
    }

    public ComponentModule() {
    }
}