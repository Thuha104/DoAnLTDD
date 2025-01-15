package com.example.doanltd.View

import ApiService
import LoginRequest
import NgDung
import RegisterRequest
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.doanltd.RoomDatabase.NgDungRoom.NgDungEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthViewModel : ViewModel(){
    val apiService: ApiService = RetrofitInstance.api

    private val _dangKyThanhCong = MutableStateFlow<Boolean?>(null)
    val dangKyThanhCong: StateFlow<Boolean?> = _dangKyThanhCong
    suspend fun dangKyNguoiDung(tenNgD: String, sdt: String, tkNgD: String, matKhauNgD: String) {
        try {
            val response = apiService.dangky(RegisterRequest(tenNgD, sdt, tkNgD, matKhauNgD))
            Log.d("API", "Đăng ký thành công: ${response}")
            // Cập nhật trạng thái đăng ký
            _dangKyThanhCong.value = response.status
        } catch (e: Exception) {
            Log.e("API", "Lỗi đăng ký: ${e.message}")
        }
    }


    private  val _dangNhapThanhCong=MutableStateFlow<Boolean?>(null)
    val dangNhapThanhCong:StateFlow<Boolean?> = _dangNhapThanhCong

    private  val _duLieuNguoiDung=MutableStateFlow<NgDungEntity?>(null)
    val duLieuNguoiDung:StateFlow<NgDungEntity?> = _duLieuNguoiDung

    suspend fun dangNhapNguoiDung( tkNgD: String, matKhauNgD: String) {
        try {
            val response = apiService.dangnhap(LoginRequest(tkNgD, matKhauNgD))
            Log.d("API", "Đăng nhap thành công: ${response}")
            _dangNhapThanhCong.value=response.status
            _duLieuNguoiDung.value= response.user
        } catch (e: Exception) {
            Log.e("API", "Lỗi đăng nhap: ${e.message}")
        }
    }
}