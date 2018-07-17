
package com.rajsuvariya.bakingapp.injection.component;

import android.app.Application;
import android.content.Context;


import com.rajsuvariya.bakingapp.IfaApp;
import com.rajsuvariya.bakingapp.data.DataManager;
import com.rajsuvariya.bakingapp.injection.ApplicationContext;
import com.rajsuvariya.bakingapp.injection.module.ApplicationModule;

import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(IfaApp app);


    @ApplicationContext
    Context context();

    Application application();

    DataManager getDataManager();
}