
package com.rajsuvariya.bakingapp.injection.component;

import com.rajsuvariya.bakingapp.injection.PerService;
import com.rajsuvariya.bakingapp.injection.module.ServiceModule;

import dagger.Component;

/**
 * Created by @rajsuvariya on 15/03/17.
 */

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {


}
