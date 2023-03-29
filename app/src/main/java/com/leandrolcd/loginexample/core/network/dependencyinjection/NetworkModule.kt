package com.leandrolcd.loginexample.core.network.dependencyinjection

import com.leandrolcd.loginexample.ui.login.data.network.LoginClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/* Summary
Un mudulo debe recibir 2 anotaciones la primera @Module para indicar que es un module y la segunda
@InstallIn() que va recibir entre parentesis el alcance que tendra la inyección es decir la duración
de la clase que estamos inyectando la siguiente list muestra los alcances;
Clase de Android            Componente Generado             Alcance
Application                 ApplicationComponent            Singleton SingletonComponent::class
ViewModel                   ActivityRetainedComponent       ActivityRetainedScope ActivityRetainedComponent
Activity                    ActivityComponent               ActivityScoped ActivityComponent
Fragment                    FragmentComponent               FragmentScoped FragmentComponent
View                        ViewComponent                   ViewScoped ViewComponent
View @WithFragmentBindings  ViewWithFragmentBindings        ViewScoped ViewComponent
Service                     ServiceComponent                ServiceScoped ServiceComponent
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun providerRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providerLoginClient(retrofit: Retrofit):LoginClient{
        return retrofit.create(LoginClient::class.java)
    }
}