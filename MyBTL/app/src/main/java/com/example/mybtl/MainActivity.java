package com.example.mybtl;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rcvMovie;
    private ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    private BottomNavigationView bottomNavigationView;
    private TabLayout tabCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String email = intent.getStringExtra("email");

        DatabaseHelper dbHelper = new DatabaseHelper(this);
        dbHelper.insertMovie("Vùng Đất Câm Lặng: Ngày Một", R.drawable.a_quiet_place, "Sam, nữ bệnh nhân ung thư chỉ còn sống được vài ngày. Trong những ngày cuối cùng của mình, cô không ngần ngại giúp đỡ những người sống sót đang tìm cách thoát khỏi bọn quái vật.", "Kinh dị", R.raw.a_quiet_place, "2024-06-28", 50000);
        dbHelper.insertMovie("Deadpool và Wolverine", R.drawable.deadpool3, "Wolverine đang hồi phục sau vết thương khi anh ấy đi ngang qua con đường với Deadpool mồm mép. Họ hợp sức để đánh bại kẻ thù chung.", "Hành động", R.raw.deadpool3, "2024-07-26", 50000);
        dbHelper.insertMovie("Kẻ Trộm Mặt Trăng 4", R.drawable.minion4, "Gru phải đối mặt với kẻ thù mới là Maxime Le Mal và Valentina đang lên kế hoạch trả thù anh, buộc gia đình anh phải lẩn trốn đi nơi khác. Bên cạnh việc đấu tranh bảo vệ gia đình, Gru đồng thời còn phải tìm ra điểm chung với thành viên mới nhất trong nhà đó là Gru Jr.", "Hài hước", R.raw.minion4, "2024-07-05", 50000);
        dbHelper.insertMovie("Những Mảnh Ghép Cảm Xúc 2", R.drawable.inside_out2, "Cuộc sống tuổi mới lớn của cô bé Riley lại tiếp tục trở nên hỗn loạn hơn bao giờ hết với sự xuất hiện của 4 cảm xúc hoàn toàn mới: Lo u, Ganh Tị, Xấu Hổ, Chán Nản. Mọi chuyện thậm chí còn rối rắm hơn khi nhóm cảm xúc mới này nổi loạn và nhốt nhóm cảm xúc cũ gồm Vui Vẻ, Buồn Bã, Giận Dữ, Sợ Hãi và Chán Ghét lại, khiến cô bé Riley rơi vào những tình huống dở khóc dở cười.", "Hoạt hình", R.raw.inside_ou2, "2024-06-14", 75000);
        dbHelper.insertMovie("Mùa Hè Đẹp Nhất", R.drawable.mua_he_dep_nhat, "Mùa hè năm ấy, chúng ta chẳng có gì trong tay ngoài tuổi trẻ, ước mơ, và…có nhau. Nhưng cũng chính mùa Hè ấy - mùa hè mang theo những điều mà ta chưa kịp hoàn thành… Bạn đã sẵn sàng gặp lại phiên bản của chính mình, trong những ngày Hè đẹp nhất của tuổi trẻ năm ấy và viết tiếp câu chuyện còn dở dang chưa?", "Lãng mạn", R.raw.mua_he_dep_nhat, "2024-06-28", 75000);
        dbHelper.insertMovie("Thám Tử Lừng Danh Conan: Ngôi Sao 5 Cánh 1 Triệu Đô", R.drawable.conan, "Trong khi đến Hakodate tham gia một giải kiếm đạo, Conan và Heiji đụng độ siêu trộm Kaito Kid - khi hắn đang nhắm tới một thanh kiếm Nhật được cất giấu trong nhà kho của một gia đình tài phiệt. Thi thể một tay buôn vũ khí khét tiếng được phát hiện với vết chém hình chữ thập, và trùng hợp thay, \"kho báu\" mà gã truy lùng dường như cũng có liên quan mật thiết đến thanh kiếm cổ mà Kid đang nhắm tới.", "Trinh thám", R.raw.conan, "2024-08-02", 75000);
        dbHelper.insertMovie("Ôi Ma Ơi: Hồi Kết", R.drawable.oi_ma_oi, "Rùng rợn nhưng không kém phần hài hước, bộ phim theo chân hành trình “bắt ma” đầy bí ẩn của hai chị em Panda, Pancake tại ngôi làng Sapayod đầy rẫy những sự việc kỳ quái.", "Kinh dị", R.raw.oi_ma_oi, "2024-07-12", 75000);
        dbHelper.insertMovie("Hijack 1971", R.drawable.hijack_1971, "Bộ phim khắc họa câu chuyện của những con người đấu tranh giành lấy sự sống trong hoàn cảnh cực kỳ khó khăn khi một chiếc máy bay chở khách bị cướp trên không phận Hàn Quốc vào năm 1971.", "Hành động", R.raw.hijack_1971, "2024-07-19", 75000);

        rcvMovie = findViewById(R.id.rcv_movie);
        movies = new ArrayList<>(MovieDAO.getAll(this)); // Lấy tất cả phim từ MovieDAO
        movieAdapter = new MovieAdapter(this, movies, email);
        rcvMovie.setAdapter(movieAdapter);
        // Thiết lập hiển thị 2 cột
        rcvMovie.setLayoutManager(new GridLayoutManager(this, 2));

        // Thanh điều hướng của app
        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.history) {
                if (email == null || email.isEmpty()) {
                    showDialogLogin();
                } else {
                    Intent intent2 = new Intent(MainActivity.this, BookingHistoryActivity.class);
                    intent2.putExtra("email", email);
                    startActivity(intent2);
                }
                return false;
            } else if (id == R.id.account) {
                if (email == null || email.isEmpty()) {
                    showDialogLogin();
                } else {
                    Intent intent3 = new Intent(MainActivity.this, ProfileActivity.class);
                    intent3.putExtra("email", email);
                    startActivity(intent3);
                }
                return false;
            } else {
                return false;
            }
        });

        tabCategories = findViewById(R.id.tab_categories);
        String[] tabItems = getResources().getStringArray(R.array.category_items);
        for (String label : tabItems) {
            tabCategories.addTab(tabCategories.newTab().setText(label));
        }

        tabCategories.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                String tabItem = tab.getText().toString();
                movies.clear();
                if (tabItem.equals("Tất cả")) {
                    movies.addAll(MovieDAO.getAll(MainActivity.this));
                } else {
                    movies.addAll(MovieDAO.filterByCategory(MainActivity.this, tabItem));
                }
                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void showDialogLogin() {
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        View dialogView = inflater.inflate(R.layout.dialog_login, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setView(dialogView);

        AlertDialog dialog = builder.create();
        dialog.show();

        Button dialogButtonLogin = dialogView.findViewById(R.id.btnLogin);
        dialogButtonLogin.setOnClickListener(v -> {
            Intent intent1 = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent1);
        });

        Button dialogButtonRegister = dialogView.findViewById(R.id.btnRegister);
        dialogButtonRegister.setOnClickListener(v -> {
            Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
            startActivity(intent2);
        });
    }

    public void resetData() {
        movies.clear();
        movies.addAll(MovieDAO.getAll(this));
        movieAdapter.notifyDataSetChanged();
    }
}
