package com.versilistyson.welldone.di.module

import com.versilistyson.welldone.di.component.AuthComponent
import dagger.Module

@Module(subcomponents = [AuthComponent::class])
class SubComponentsModule {}