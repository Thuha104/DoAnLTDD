import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SanPhamViewModel :ViewModel(){
    var posts by mutableStateOf<List<SanPham>>(emptyList())
        private set

    var isLoading by mutableStateOf(true)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    init {
        fetchPosts()
    }

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
    }