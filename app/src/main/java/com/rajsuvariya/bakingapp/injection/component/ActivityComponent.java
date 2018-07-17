
package com.rajsuvariya.bakingapp.injection.component;

import com.rajsuvariya.bakingapp.injection.PerActivity;
import com.rajsuvariya.bakingapp.injection.module.ActivityModule;
import com.rajsuvariya.bakingapp.ui.login.LoginActivity;
import com.rajsuvariya.bakingapp.ui.recipeList.RecipeListActivity;
import com.rajsuvariya.bakingapp.ui.splash.SplashActivity;

import dagger.Component;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity splashActivity);
    void inject(LoginActivity loginActivity);

    void inject(RecipeListActivity recipeListActivity);
}
