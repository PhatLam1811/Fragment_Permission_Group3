package com.example.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        // Nên khởi tạo những data gốc (chỉ truyền một lần trong vòng đời Fragment) trong Override này
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnVerify = view.findViewById(R.id.btnVerify);

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Gọi getView() để lấy được View gốc của Fragment
                EditText edtUsername = getView().findViewById(R.id.edittxtUsername);
                EditText edtPassword = getView().findViewById(R.id.edittxtPassword);

                if (edtUsername.getText().toString().equals("user") && edtPassword.getText().toString().equals("user")) {

                    // getActivity() tham chiếu đến Host Activity
                    FragmentManager fragManage = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragTrans = fragManage.beginTransaction();

                    // Kiểm tra Activity đã tồn tại instance HomeFragment chưa?
                    //      True (đã có): sử dụng tiếp instance đó
                    //      False (chưa có): tạo instance mới
                    HomeFragment fragHome = (HomeFragment) getActivity().getSupportFragmentManager().findFragmentByTag("frgHome");
                    if (fragHome == null) fragHome = new HomeFragment();

                    // Thiết lập hiệu ứng vào/ra cho fragment
                    /*fragTrans.setCustomAnimations(
                        // Enter
                        // Exit
                        ...
                    );*/

                    // Thay fragment mới bằng replace()
                    // Gán nhãn cho instance vừa replace để tiện kiểm tra
                    // AddToBackStack(): truy lại các fragment đã mở khi nhấn nút Back cứng trên điện thoại
                    fragTrans.replace(R.id.contentContainer, fragHome , "frgHome");
                    fragTrans.addToBackStack(null);
                    fragTrans.commit();
                }
            }
        });
        }
}