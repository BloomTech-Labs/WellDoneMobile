package com.versilistyson.welldone.di.component

import com.versilistyson.welldone.di.module.SubComponentsModule
import com.versilistyson.welldone.di.module.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, SubComponentsModule::class])
interface AppComponent {

}