package com.rajsuvariya.bakingapp.injection.component;

import com.rajsuvariya.bakingapp.injection.module.ApplicationModule;
import com.rajsuvariya.bakingapp.widget.BakingAppWidgetProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by @raj on 17/07/18.
 */


@Singleton
@Component(modules = ApplicationModule.class)
public interface WidgetComponent {
    void inject(BakingAppWidgetProvider bakingAppWidgetProvider);
}
