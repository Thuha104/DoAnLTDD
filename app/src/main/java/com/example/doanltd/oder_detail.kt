package com.example.doanltd

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailsScreen(navController: NavController) {
    var selectedPaymentMethod by remember { mutableStateOf<String?>(null) }
    var deliveryAddress by remember { mutableStateOf("192, Phạm Đức Sơn, Phường 16, Quận 8") }
    var showAddressDialog by remember { mutableStateOf(false) }
    var showResultDialog by remember { mutableStateOf<Pair<Boolean, String>?>(null) }
    var customerNote by remember { mutableStateOf("") }

    if (showAddressDialog) {
        AddressEditDialog(
            currentAddress = deliveryAddress,
            onDismiss = { showAddressDialog = false },
            onSave = {
                deliveryAddress = it
                showAddressDialog = false
            }
        )
    }

    showResultDialog?.let { (isSuccess, message) ->
        ResultDialog(
            isSuccess = isSuccess,
            message = message,
            onDismiss = { showResultDialog = null }
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Thông tin đơn hàng") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Delivery Address
            Text(
                "Địa chỉ: $deliveryAddress",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clickable { showAddressDialog = true }
            )

            // Order Details
            Row (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.anh1),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                )
                Column(
                    modifier = Modifier.weight(1f)

                ) {
                    Text(
                        "[COMBO 6] Bánh Tráng Phơi Sương Sốt Tắc Muối Ruốc Hành Phí",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Số lượng: 2")
                        Text("65.000đ")
                    }
                }
            }

            // Order Summary
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Tóm tắt đơn hàng",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Tiền sản phẩm:")
                        Text("65.000đ")
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Vận chuyển:")
                        Text("10.500đ",
                            style = MaterialTheme.typography.bodySmall.copy(
                                textDecoration = TextDecoration.LineThrough))
                    }
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            "Tổng:",
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "65.000đ",
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )
                    }
                }
            }

            // Payment Methods
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Hình thức thanh toán:",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedPaymentMethod == "COD",
                            onClick = { selectedPaymentMethod = "COD" }
                        )
                        Text(
                            "Thanh toán khi nhận hàng",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = selectedPaymentMethod == "DIGITAL",
                            onClick = { selectedPaymentMethod = "DIGITAL" }
                        )
                        Text(
                            "Thanh toán qua ví điện tử",
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }

            // Customer Note
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        "Lời nhắn từ khách hàng:",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = customerNote,
                        onValueChange = { customerNote = it },
                        label = { Text("Nhập lời nhắn...") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Order Button
            Button(
                onClick = {
                    if (selectedPaymentMethod == null) {
                        showResultDialog = false to "Bạn chưa chọn hình thức thanh toán!"
                    } else {
                        showResultDialog = true to "Đặt hàng thành công! Lời nhắn: $customerNote"
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {
                Text("Đặt hàng")
            }
        }
    }
}

@Composable
fun AddressEditDialog(currentAddress: String, onDismiss: () -> Unit, onSave: (String) -> Unit) {
    var newAddress by remember { mutableStateOf(currentAddress) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Chỉnh sửa địa chỉ", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = newAddress,
                    onValueChange = { newAddress = it },
                    label = { Text("Địa chỉ mới") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Hủy")
                    }
                    Button(onClick = { onSave(newAddress) }) {
                        Text("Lưu")
                    }
                }
            }
        }
    }
}

@Composable
fun ResultDialog(isSuccess: Boolean, message: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier.padding(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = if (isSuccess) Icons.Default.Check else Icons.Default.Close,
                    contentDescription = null,
                    tint = if (isSuccess) Color.Green else Color.Red,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(message, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onDismiss) {
                    Text("Đóng")
                }
            }
        }
    }
}
