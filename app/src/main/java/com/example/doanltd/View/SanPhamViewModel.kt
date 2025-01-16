package com.example.doanltd.View

import HoaDonRequest
import LoaiSP
import RetrofitInstance
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.doanltd.data.HoaDon
import com.example.doanltd.data.SanPham
<<<<<<< HEAD
import kotlinx.coroutines.flow.MutableSharedFlow
=======
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SanPhamViewModel :ViewModel(){

    // danh sach post
    var posts by mutableStateOf<List<SanPham>>(emptyList())
        private set

    var loaisanphams by mutableStateOf<List<LoaiSP>>(emptyList())
        private set

    var productDetail by mutableStateOf<SanPham?>(null)
        private set

    var hoadons by mutableStateOf<List<HoaDon>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchPosts()
        fetchLoaiSanPham()
        fetchHoaDon()
    }

    // ham gọi ds post
    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                isLoading = true
                // Lấy dữ liệu từ API
                val response = RetrofitInstance.api.getSanPham()
                posts = response
                isLoading = false
            } catch (e: Exception) {
                // Xử lý lỗi và cập nhật thông báo lỗi
                isLoading = false
                errorMessage = e.message
                e.printStackTrace()
            }
        }
    }

    private fun fetchLoaiSanPham() {
        viewModelScope.launch {
            try {
                isLoading = true
                // Lấy dữ liệu từ API
                val response = RetrofitInstance.api.getLoaiSP()
                loaisanphams = response
                isLoading = false
            } catch (e: Exception) {
                // Xử lý lỗi và cập nhật thông báo lỗi
                isLoading = false
                errorMessage = e.message
                e.printStackTrace()
            }
        }
    }

    fun fetchProductDetail(productId: String) {
        viewModelScope.launch {
            try {
                productDetail = RetrofitInstance.api.getChiTietSanPham(productId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

<<<<<<< HEAD

    private val _hoadonthanhcong = MutableStateFlow<Boolean?>(value = null)
    val hoadonthanhcong:StateFlow<Boolean?> = _hoadonthanhcong

    private val _hoadonthongbao = MutableStateFlow<String?>(value = null)
    val hoadonthongbao:StateFlow<String?> = _hoadonthongbao

    private val _MaHd = MutableStateFlow<String?>(value = null)
    val MaHd:StateFlow<String?> = _MaHd

    suspend fun themhoadon(MaNgD:String, TongTien:Double, DiaChi:String){
        try {
            val response = RetrofitInstance.api.themhoadon(HoaDonRequest(MaNgD,TongTien,MaNgD))
            _hoadonthanhcong.value=response.success
            _hoadonthongbao.value=response.message
            _MaHd.value=response.MaHD
            }
        catch (e:Exception){

=======
    private fun fetchHoaDon() {
        viewModelScope.launch {
            try {
                isLoading = true
                // Lấy dữ liệu từ API
                val response = RetrofitInstance.api.getHoaDon()
                hoadons = response
                isLoading = false
            } catch (e: Exception) {
                // Xử lý lỗi và cập nhật thông báo lỗi
                isLoading = false
                errorMessage = e.message
                e.printStackTrace()
            }
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b
        }
    }



<<<<<<< HEAD
=======
    // success
    private val _hoadonthanhcong = MutableStateFlow<Boolean?>(null)
    val hoadonthanhcong: StateFlow<Boolean?> = _hoadonthanhcong

    // message
    private val _hoadonthongbao = MutableStateFlow<String?>(null)
    val hoadonthongbao: StateFlow<String?> = _hoadonthongbao

    // mahd
    private val _MaHd = MutableStateFlow<String?>(null)
    val MaHd: StateFlow<String?> = _MaHd


    suspend fun themhoadon(MaNgD:String,TongTien:Double,DiaChi:String) {
        try {
            val response = RetrofitInstance.api.themhoadon(HoaDonRequest(MaNgD,TongTien,DiaChi))
            _hoadonthanhcong.value = response.status
            _hoadonthongbao.value = response.message
            _MaHd.value = response.MaHD
        } catch (e: Exception) {
            Log.e("API", "Lỗi đăng ký: ${e.message}")
        }
    }
>>>>>>> bcd2a864b3006d63cba77752185182e112377e7b


}