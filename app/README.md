Ứng dụng khởi động không lưu và khôi phục trạng thái ứng dụng trong quá trình thay đổi cấu hình
chẳng hạn như khi điều hướng thiết bị thay đổi hoặc tắt ứng dụng và mở lại
để giải quyết vấn đề này bằng cách sử dụng:
- sử dụng onSaveInstanceState() call back, là một phương pháp được sử dụng để lưu trữ dữ liệu trước khi tạm dừng hoạt động
+ yêu cầu viết thêm mã để lưu trạng thái bundle và triển khai logic để truy xuất trạng thái đó
+ số lượng dữ liệu có thể được lưu trữ là tối thiểu

- onRestoreInstanceState() là phương thức được sử dụng để lấy lại dữ liệu đó.

các hàm có thể sử dụng trong gói (Gói là nơi chứa tất cả thông tin bạn muốn lưu)
putString
putBoolean
putByte
putChar
putFloat
putLong
putShort
putParcelable (used for objects but they must implement Parcelable)

Trong chức năng onCreate của bạn, Gói này được trao lại cho chương trình. Cách tốt nhất để kiểm tra xem ứng dụng đang được tải lại hoặc khởi động lần đầu tiên là:
if (savedInstanceState != null) {
    // Then the application is being reloaded
}

@Override
public void onSaveInstanceState(Bundle outState) {
   outState.putString("message", "This is my message to be reloaded");
   super.onSaveInstanceState(outState);
}

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (savedInstanceState != null) {
        String message = savedInstanceState.getString("message");
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}

ngoài ra achitecture component : có thể giải quyết bài toán bảo toàn state
 - thiết kế ra kiến trúc nhằm :
    + hoạt động tốt trong các tình huống
    + dễ làm việc

UI controller:
- Bộ điều khiển giao diện người dùng là một lớp dựa trên giao diện người dùng như Activity hoặc Fragment
- Bộ điều khiển giao diện người dùng chỉ nên chứa logic xử lý các tương tác giữa giao diện người dùng và hệ điều hành

ViewModel:
- ViewModel giữ dữ liệu được hiển thị trong một phân đoạn hoặc hoạt động được liên kết với ViewModel
- ViewModel có thể thực hiện các phép tính và biến đổi đơn giản trên dữ liệu để chuẩn bị dữ liệu được hiển thị
bởi bộ điều khiển giao diện người dùng
- Lớp ViewModel được thiết kế để lưu trữ và quản lý dữ liệu liên quan đến giao diện người dùng.

ViewModelFactory:
- ViewModelFactory khởi tạo các đối tượng ViewModel, có hoặc không có tham số phương thức khởi tạo.

Using ViewModel:
Các bước thực hiện:
Step 1: Add the GameViewModel class
Step 2: Override onCleared() and add logging
- ViewModel bị hủy khi phân đoạn liên kết bị tách ra hoặc khi hoạt động kết thúc.
 Ngay trước khi ViewModel bị hủy, lệnh gọi lại onCleared () được gọi để dọn dẹp tài nguyên
Step 3: Associate GameViewModel with the game fragment
- ViewModel cần được liên kết với bộ điều khiển giao diện người dùng.
 Để kết hợp cả hai, bạn tạo một tham chiếu đến ViewModel bên trong bộ điều khiển giao diện người dùng
Step 4: Initialize the ViewModel
- Khi thay đổi cấu hình hay xoay màn hình UI controller sẽ được tạo lại, tuy nhiên ViewModel vẫn tồn tại
- nếu bạn tạo thể hiện ViewModel nó sẽ bị gọi lại mỗi khi UI controller tạo lại, thay vào đó
hãy tạo ra phiên bản ViewModel bằng ViewModelProvider.
Note: Luôn sử dụng ViewModelProvider để tạo các đối tượng ViewModel thay vì tạo trực tiếp một phiên bản của ViewModel.

ViewModelProvider?
- ViewModelProvider trả về một ViewModel hiện có nếu một cái tồn tại hoặc nó tạo một cái mới nếu nó chưa tồn tại.
- ViewModelProvider tạo một thể hiện ViewModel liên kết với phạm vi đã cho (một hoạt động hoặc một phân đoạn)
- ViewModel đã tạo được giữ lại miễn là phạm vi còn tồn tại. Ví dụ: nếu phạm vi là một phân đoạn,
 thì ViewModel được giữ lại cho đến khi phân đoạn được tách ra.

ViewModel vẫn tồn tại các thay đổi cấu hình, vì vậy đây là một nơi tốt cho dữ liệu cần tồn tại các thay đổi cấu hình:

Hoạt động của với ViewModel?
- Đặt dữ liệu được hiển thị trên màn hình và mã để xử lý dữ liệu đó trong ViewModel
- ViewModel không bao giờ được chứa các tham chiếu đến các phân đoạn, hoạt động hoặc chế độ xem,
 vì các hoạt động, phân đoạn và chế độ xem không tồn tại sau các thay đổi cấu hình

Init trong ViewModel?
- Nó được gọi khi ViewModel được tạo
