package com.example.starwarspedia.data.daggerHilt

import com.example.starwarspedia.data.models.AllModels
import com.example.starwarspedia.data.RepositoryImpl
import com.example.starwarspedia.data.models.PeopleModel
import com.example.starwarspedia.data.models.PlanetsModel
import com.example.starwarspedia.data.models.SpeciesModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideImpl(
        retrofit: Retrofit,
        allList: AllModels
    ): RepositoryImpl {
        return RepositoryImpl(retrofit, allList)
    }

    @Provides
    @Singleton
    fun provideAllLists(): AllModels {
        return AllModels(mutableListOf(), mutableListOf(), mutableListOf())
    }

    @Provides
    @Singleton
    fun providePeopleList(): MutableList<PeopleModel> {
        return mutableListOf()
    }

    @Provides
    @Singleton
    fun providePlanetsList(): MutableList<PlanetsModel> {
        return mutableListOf()
    }

    @Provides
    @Singleton
    fun provideSpeciesList(): MutableList<SpeciesModel> {
        return mutableListOf()
    }
}
