package hu.havasig.alcoholcalendar.network

import dagger.Module
import dagger.Provides
import hu.havasig.alcoholcalendar.network.api.ChallengeApi
import hu.havasig.alcoholcalendar.network.api.DrinkApi
import hu.havasig.alcoholcalendar.network.api.DrinkTypesApi
import hu.havasig.alcoholcalendar.network.api.StatisticsApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
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
	fun provideDrinkTypesApi(client: OkHttpClient): DrinkTypesApi {
		val retrofit = Retrofit.Builder()
			.client(client)
			.baseUrl(NetworkConfig.DRINK_TYPE_API_ADDRESS)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(DrinkTypesApi::class.java)
	}

	@Provides
	@Singleton
	fun provideStatisticsApi(client: OkHttpClient): StatisticsApi {
		val retrofit = Retrofit.Builder()
			.client(client)
			.baseUrl(NetworkConfig.STATISTICS_API_ADDRESS)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
		return retrofit.create(StatisticsApi::class.java)
	}
}
