package hu.havasig.alcoholcalendar.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import hu.havasig.alcoholcalendar.data.api.ChallengeApi
import hu.havasig.alcoholcalendar.data.api.DrinkApi
import hu.havasig.alcoholcalendar.data.api.DrinkTypeApi
import hu.havasig.alcoholcalendar.data.api.StatisticApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {
	@Provides
	@Singleton
	fun provideOkHttpClient(): OkHttpClient {
		val interceptor = HttpLoggingInterceptor()
		interceptor.level = HttpLoggingInterceptor.Level.BODY
		return OkHttpClient.Builder().addInterceptor(interceptor).build()
	}

	@Provides
	@Singleton
	fun provideChallengeApi(client: OkHttpClient): ChallengeApi {
		val retrofit = Retrofit.Builder()
			.client(client)
			.baseUrl(NetworkConfig.CHALLENGE_API_ADDRESS)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(ChallengeApi::class.java)
	}

	@Provides
	@Singleton
	fun provideDrinkApi(client: OkHttpClient): DrinkApi {
		val retrofit = Retrofit.Builder()
			.client(client)
			.baseUrl(NetworkConfig.DRINK_API_ADDRESS)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(DrinkApi::class.java)
	}

	@Provides
	@Singleton
	fun provideDrinkTypesApi(client: OkHttpClient): DrinkTypeApi {
		val retrofit = Retrofit.Builder()
			.client(client)
			.baseUrl(NetworkConfig.DRINK_TYPE_API_ADDRESS)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(DrinkTypeApi::class.java)
	}

	@Provides
	@Singleton
	fun provideStatisticsApi(client: OkHttpClient): StatisticApi {
		val retrofit = Retrofit.Builder()
			.client(client)
			.baseUrl(NetworkConfig.STATISTICS_API_ADDRESS)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(StatisticApi::class.java)
	}
}
