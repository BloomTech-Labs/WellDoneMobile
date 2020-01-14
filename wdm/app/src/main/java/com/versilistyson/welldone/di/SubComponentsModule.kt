package com.versilistyson.welldone.di

import com.versilistyson.welldone.di.auth.AuthComponent
import dagger.Module

@Module(subcomponents = [AuthComponent::class])
class SubComponentsModule {}