package edu.ucne.registroestudiantes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.registroestudiantes.data.local.dao.EstudianteDao
import edu.ucne.registroestudiantes.data.local.database.AppDatabase
import edu.ucne.registroestudiantes.data.repository.EstudianteRepositoryImpl
import edu.ucne.registroestudiantes.domain.repository.EstudianteRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "estudiantes_db"
        ).build()
    }

    @Provides
    fun provideEstudianteDao(
        db: AppDatabase
    ): EstudianteDao = db.estudianteDao()

    @Provides
    @Singleton
    fun provideEstudianteRepository(
        dao: EstudianteDao
    ): EstudianteRepository {
        return EstudianteRepositoryImpl(dao)
    }
}