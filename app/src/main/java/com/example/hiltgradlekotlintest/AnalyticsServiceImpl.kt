package com.example.hiltgradlekotlintest

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Qualifier

// Constructor-injected, because Hilt needs to know how to
// provide instances of AnalyticsServiceImpl, too.

class AnalyticsServiceImpl @Inject constructor() : AnalyticsService {

    override fun analyticsMethods() {

    }
}

// As a dependency of a constructor-injected class.
class ExampleServiceImpl @Inject constructor(
    @AuthInterceptorOkHttpClient private val okHttpClient: OkHttpClient
)

@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

    /**
     * Inject interface instances with @Binds
    Consider the AnalyticsService example.
    If AnalyticsService is an interface, then you cannot constructor-inject it.
    Instead, provide Hilt with the binding information by creating an abstract function
    annotated with @Binds inside a Hilt module.
    The @Binds annotation tells Hilt which implementation to use when it
    needs to provide an instance of an interface.
     * **/
    @Binds
    abstract fun bindAnalyticsService(
        analyticsServiceImpl: AnalyticsServiceImpl
    ): AnalyticsService

    /***
     * Inject instances with @Provides
    Interfaces are not the only case where you cannot constructor-inject a type.
    Constructor injection is also not possible if you don't own the
    class because it comes from an external library (classes like Retrofit,
    OkHttpClient, or Room databases), or if instances must be created with the builder pattern.
     * */

    @Provides
    fun provideAnalyticsService(
        @AuthInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): AnalyticsService {
        return Retrofit.Builder()
            .baseUrl("https://example.com")
            .build()
            .create(AnalyticsService::class.java)
    }

}


/***
 * Consider the example. If you need to intercept calls to AnalyticsService,
 * you could use an OkHttpClient object with an interceptor. For other services,
 * you might need to intercept calls in a different way.
 * In that case, you need to tell Hilt how to provide two different implementations of OkHttpClient.
First, define the qualifiers that you will use to annotate the @Binds or @Provides methods:
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient

