package io.wams.meli.injection.component;

import dagger.Subcomponent;
import io.wams.meli.injection.PerFragment;
import io.wams.meli.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}
