package edu.ucne.registroestudiantes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.registroestudiantes.data.local.dao.AsignaturaDao
import edu.ucne.registroestudiantes.data.local.dao.EstudianteDao
import edu.ucne.registroestudiantes.data.local.dao.TiposPenalidadesDao
import edu.ucne.registroestudiantes.data.local.database.AppDatabase
import edu.ucne.registroestudiantes.data.repository.AsignaturaRepositoryImpl
import edu.ucne.registroestudiantes.data.repository.EstudianteRepositoryImpl
import edu.ucne.registroestudiantes.data.repository.TipoPenalidadRepositoryImpl
import edu.ucne.registroestudiantes.domain.repository.AsignaturaRepository
import edu.ucne.registroestudiantes.domain.repository.EstudianteRepository
import edu.ucne.registroestudiantes.domain.repository.TipoPenalidadRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "estudiantes_db"
        ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideEstudianteDao(
        db: AppDatabase
    ): EstudianteDao = db.estudianteDao()

    @Provides
    fun provideAsignaturaDao(
        db: AppDatabase
    ): AsignaturaDao = db.asignaturaDao()

    @Provides
    fun provideTiposPenalidadesDao(db: AppDatabase): TiposPenalidadesDao =
        db.tiposPenalidadesDao()
    @Provides
    @Singleton
    fun provideEstudianteRepository(
        dao: EstudianteDao
    ): EstudianteRepository = EstudianteRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAsignaturaRepository(
        dao: AsignaturaDao
    ): AsignaturaRepository = AsignaturaRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideTipoPenalidadRepository(
        dao: TiposPenalidadesDao
    ): TipoPenalidadRepository = TipoPenalidadRepositoryImpl(dao)
}