package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class PokedexProjectDependency extends DelegatingProjectDependency {

    @Inject
    public PokedexProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":app"
     */
    public AppProjectDependency getApp() { return new AppProjectDependency(getFactory(), create(":app")); }

    /**
     * Creates a project dependency on the project at path ":core-model"
     */
    public CoreModelProjectDependency getCoreModel() { return new CoreModelProjectDependency(getFactory(), create(":core-model")); }

    /**
     * Creates a project dependency on the project at path ":core-network"
     */
    public CoreNetworkProjectDependency getCoreNetwork() { return new CoreNetworkProjectDependency(getFactory(), create(":core-network")); }

}
