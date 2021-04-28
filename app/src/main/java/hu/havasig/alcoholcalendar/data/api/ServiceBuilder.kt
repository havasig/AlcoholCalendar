package hu.havasig.alcoholcalendar.data.api

import hu.havasig.alcoholcalendar.data.AppPreferences
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


object ServiceBuilder {
	private const val URL = "http://192.168.1.84:8080/"
	private const val URLBin = "https://enl5jux18g89ibb.m.pipedream.net"

	fun <T> buildService(serviceType: Class<T>): T {
		val okHttpClient = OkHttpClient.Builder()
		val headerInterceptor = HeaderInterceptor()
		okHttpClient.addInterceptor(headerInterceptor)
		okHttpClient.callTimeout(3, TimeUnit.SECONDS)
		okHttpClient.connectTimeout(3, TimeUnit.SECONDS)
		okHttpClient.writeTimeout(3, TimeUnit.SECONDS)
		okHttpClient.readTimeout(3, TimeUnit.SECONDS)

		return Retrofit.Builder().baseUrl(URL)
			.addConverterFactory(GsonConverterFactory.create())
			.client(okHttpClient.build())
			.build()
			.create(serviceType)
	}
}

class HeaderInterceptor : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response = chain.run {
		var userId = AppPreferences.userId
		if (userId == null) {
			userId = UUID.randomUUID().toString()
			AppPreferences.userId = userId
		}
		proceed(
			request()
				.newBuilder()
				.addHeader("user-id", userId)
				.build()
		)
	}
}