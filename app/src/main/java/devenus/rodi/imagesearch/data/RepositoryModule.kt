package devenus.rodi.imagesearch.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import devenus.rodi.imagesearch.network.service.SearchImageService

@Module
@InstallIn(ActivityRetainedComponent::class)
object RepositoryModule {

    @Provides
    @ActivityRetainedScoped
    fun provideSearchImageRepository(searchImageService: SearchImageService): SearchImageRepository =
        SearchImageRepositoryImpl(searchImageService)
}