import com.example.doanltd.data.SanPham
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService{
    // gọi dường dẫn ?
 @GET("sanpham/dssanpham.php")
 suspend fun getSanPham():List<SanPham>

 @GET("loaisp/dsloaisp.php")
 suspend fun getLoaiSP():List<LoaiSP>

 @Headers("Content-Type: application/json")
 @POST("ngdung/dangky.php")
 suspend fun dangky(@Body request: RegisterRequest):RegisterResponse


 @POST("ngdung/dangnhap.php")
    suspend fun dangnhap(@Body request: LoginRequest):LoginReponse

 @GET("sanpham/laySanPhamTheoMaSP.php")
    suspend fun getChiTietSanPham(@Query("id") productId: String): SanPham
}

object RetrofitInstance{
    val api:ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://192.168.x.x/restful_api_php/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ApiService::class.java)

    }
}