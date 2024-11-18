package com.albertorusso.mytodo.data.injection

import android.app.Application
import com.albertorusso.mytodo.data.database.AppDatabase
import com.albertorusso.mytodo.data.datasource.TodoDao
import com.albertorusso.mytodo.data.repository.TodoRepositoryImpl
import com.albertorusso.mytodo.domain.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return AppDatabase.getInstance(app)
    }
    
    @Provides
    fun provideTodoDao(database: AppDatabase): TodoDao {
        return database.todoDao()
    }
    
    @Provides
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository {
        return TodoRepositoryImpl(todoDao)
    }
}
