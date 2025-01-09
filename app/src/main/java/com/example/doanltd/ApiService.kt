import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService{
 @GET("sanpham/dssanpham.php")
 suspend fun getSanPham():List<SanPham>
}
object RetrofitInstance{
    val api:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.1.7/restful_api_php/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}