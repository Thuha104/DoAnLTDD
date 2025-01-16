import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity
import com.google.gson.annotations.SerializedName

// Dùng cho đăng ký
data class RegisterRequest(
    @SerializedName("TenNgD") val TenNgD: String,
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

<<<<<<< HEAD

data class HoaDonRequest(
    @SerializedName("MaNgD") val MaNgD : String,
    @SerializedName("TongTien") val TongTien: Double,
    @SerializedName("DiaChi") val Diachi: String,
)

data class HoaDonReponse(
    val success: Boolean,
    val message:String,
    val MaHD:String
=======
// phan hoa them hoa don
data class HoaDonRequest(
    @SerializedName("MaNgD") val MaNgD: String,
    @SerializedName("TongTien") val TongTien: Double,
    @SerializedName("DiaChi") val DiaChi: String)

data class HoaDonReponse(
    val status:Boolean,
    val message:String,
    val MaHD:String
)
data class UpdatePasswordRequest(
    @SerializedName("MaNgD") val MaNgD: String,
    @SerializedName("MatKhauCu") val MatKhauCu: String,
    @SerializedName("MatKhauMoi") val MatKhauMoi: String
)

data class UpdatePasswordResponse(
    val message: String,
    val success: Boolean
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b
)