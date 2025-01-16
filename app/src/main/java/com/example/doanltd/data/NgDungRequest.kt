import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity
import com.google.gson.annotations.SerializedName

// Dùng cho đăng ký
data class RegisterRequest(
    @SerializedName("TenNgD") val tenNgD: String,
    @SerializedName("SDT") val sdt: String,
    @SerializedName("TKNgD") val tkNgD: String,
    @SerializedName("MatKhauNgD") val matKhauNgD: String
)

// Dùng cho đăng nhập (chỉ cần tài khoản + mật khẩu)
data class LoginRequest(
    @SerializedName("TKNgD") val tkNgD: String,
    @SerializedName("MatKhauNgD") val matKhauNgD: String
)

// Phản hồi từ server (có thể dùng chung)
data class RegisterResponse(
    val status: Boolean,
    val message: String,
)

// phản hồi từ server login
data class LoginReponse(
    val status: Boolean,
    val user:NgDungEntity
)


data class HoaDonRequest(
    @SerializedName("MaNgD") val MaNgD : String,
    @SerializedName("TongTien") val TongTien: Double,
    @SerializedName("DiaChi") val Diachi: String,
)

data class HoaDonReponse(
    val success: Boolean,
    val message:String,
    val MaHD:String
)