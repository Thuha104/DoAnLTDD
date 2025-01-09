import com.example.doanltd.data.SanPham
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    // gọi dường dẫn ?
 @GET("sanpham/dssanpham.php")
 suspend fun getSanPham():List<SanPham>

 @GET("loaisp/dsloaisp.php")
 suspend fun getLoaiSP():List<LoaiSP>

 @GET("sanpham/laySanPhamTheoMaSP.php")
    suspend fun getChiTietSanPham(@Query("id") productId: String): SanPham

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