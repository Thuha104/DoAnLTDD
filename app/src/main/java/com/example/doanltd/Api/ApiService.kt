import com.example.doanltd.data.HoaDon
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

 @POST("ngdung/capnhapmatkhau.php")
 suspend fun capnhatmatkhau(@Body request: UpdatePasswordRequest): UpdatePasswordResponse

 @Headers("Content-Type: application/json")
 @POST("ngdung/dangky.php")
 suspend fun dangky(@Body request: RegisterRequest):RegisterResponse

 @POST("ngdung/dangnhap.php")
 suspend fun dangnhap(@Body request: LoginRequest):LoginReponse

 @GET("sanpham/laySanPhamTheoMaSP.php")
<<<<<<< HEAD
    suspend fun getChiTietSanPham(@Query("id") productId: String): SanPham


@POST("hoadon/themhoadon.php")
    suspend fun themhoadon(@Body request: HoaDonRequest):HoaDonReponse



=======
 suspend fun getChiTietSanPham(@Query("id") productId: String): SanPham

 @GET("hoadon/dshoadon.php")
 suspend fun  getHoaDon():List<HoaDon>

 @Headers("Content-Type: application/json")
 @POST("hoadon/themhoadon.php")
 suspend fun themhoadon(@Body request: HoaDonRequest):HoaDonReponse
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b
}


object RetrofitInstance{
    val api:ApiService by lazy {
        Retrofit.Builder()
<<<<<<< HEAD
            .baseUrl("http://192.168.1.131/restful_api_php/api/")
=======
            .baseUrl("http://192.168.1.11/restful_api_php/api/")
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .build()
            .create(ApiService::class.java)
    }
}