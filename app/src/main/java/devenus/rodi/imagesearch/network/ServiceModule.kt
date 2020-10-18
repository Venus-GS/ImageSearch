package devenus.rodi.imagesearch.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import devenus.rodi.imagesearch.network.service.SearchImageService
import retrofit2.Retrofit

@Module
@InstallIn(ApplicationComponent::class)
class ServiceModule {

    @Provides
    fun provideImageSearchService(retrofit: Retrofit): SearchImageService =
        retrofit.create(SearchImageService::class.java)
}
