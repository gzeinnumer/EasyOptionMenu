package com.gzeinnumer.eom.dialog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.gzeinnumer.edf.MyLibDialog;
import com.gzeinnumer.eom.adapter.OptionsAdapter;
import com.gzeinnumer.eom.R;
import com.gzeinnumer.eom.adapter.SelectedAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DynamicOptionMenu<T> extends MyLibDialog {

    public static final String TAG = "DynamicOptionMenu";

    private final List<T> listSelected;
    private final List<BaseList<T>> listBase;
    private SelectedAdapter<T> selectedAdapter;
    private OptionsAdapter<T> optionsAdapter;
    private int currentLevel = 0;
    private int currentCallBack = 0;
    private List<CallBack<T>> callBacks;
    private CallBackFinal<T> callBackFinal;
    private View view;
    private RecyclerView rvSelected;
    private RecyclerView rvOptions;
    private LinearLayout llEmpty;
    private TextInputEditText etSearch;
    private TextView tvTitle;
    private String title ="";

    public interface CallBack<T> {
        List<T> positionItem(T data);
    }

    public interface CallBackFinal<T> {
        void positionItem(T data);
    }

    public DynamicOptionMenu(List<T> list) {
        super(R.style.DynamicOptionMenuDefault);
        this.listSelected = new ArrayList<>();
        this.listBase = new ArrayList<>();
        this.callBacks = new ArrayList<>();
        this.listBase.add(new BaseList<T>(listBase.size(), list));
    }

    public void setCallBacks(CallBack<T>... callBacks) {
        this.callBacks.addAll(Arrays.asList(callBacks));
    }

    public void setCallBackFinal(CallBackFinal<T> callBackFinal) {
        this.callBackFinal = callBackFinal;
    }

    @Override
    public void onStart() {
        super.onStart();
        setCanvasWidth(1);
        setGravity(Gravity.BOTTOM);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dynamic_option_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;

        initView();
        initOnClick();
    }

    private void initView() {
        initBind();
        if (title.length()>0){
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        initSearchView();
        initSelected();
        initOption();
    }

    private void initOnClick() {
    }

    private void initBind() {
        rvSelected = view.findViewById(R.id.rv_selected);
        rvOptions = view.findViewById(R.id.rv_option);
        etSearch = view.findViewById(R.id.et_Search);
        llEmpty = view.findViewById(R.id.ll_empty);
        tvTitle = view.findViewById(R.id.tv_title);
    }

    private void initSearchView() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                optionsAdapter.getFilter().filter(s.toString());
            }
        });
    }

    private void initSelected() {
        selectedAdapter = new SelectedAdapter<T>(listSelected);
        if (selectedAdapter.getItemCount() > 0) {
            rvSelected.setVisibility(View.VISIBLE);
        } else {
            rvSelected.setVisibility(View.GONE);
        }
        rvSelected.setAdapter(selectedAdapter);
        rvSelected.hasFixedSize();
        rvSelected.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        selectedAdapter.setOnItemClickListener(new SelectedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                listSelected.subList(position, listSelected.size()).clear();
                currentLevel = position;
                currentCallBack = position;
                initSelected();
                initOption();
            }
        });
    }

    private void initOption() {
        optionsAdapter = new OptionsAdapter<T>(listBase.get(currentLevel).getData());
        rvOptions.setAdapter(optionsAdapter);
        rvOptions.hasFixedSize();
        rvOptions.setLayoutManager(new LinearLayoutManager(requireContext()));

        optionsAdapter.setOnItemClickListener(new OptionsAdapter.OnItemClickListener<T>() {
            @Override
            public void onItemClick(T position) {
                if (currentCallBack < callBacks.size()) {
                    List<T> cList = callBacks.get(currentCallBack).positionItem(position);
                    listBase.add(new BaseList<T>(listBase.size(), cList));
                    optionsAdapter.setList(cList, currentCallBack, callBacks.size());
                } else if (currentCallBack == callBacks.size()) {
                    if (callBackFinal != null) {
                        callBackFinal.positionItem(position);
                        getDialog().dismiss();
                    }
                }
                if (currentLevel < listBase.size()) {
                    if (currentCallBack < callBacks.size()) {
//                    selectedAdapter.add(position);
                        listSelected.add(position);
                        initSelected();
                    }
                }
                currentCallBack++;
                currentLevel++;
                etSearch.setText("");
            }
        });
        optionsAdapter.setSizeCallBack(new OptionsAdapter.SizeCallBack() {
            @Override
            public void sizeAfterFilter(int size) {
                if (size>0){
                    rvOptions.setVisibility(View.VISIBLE);
                    llEmpty.setVisibility(View.GONE);
                } else {
                    rvOptions.setVisibility(View.GONE);
                    llEmpty.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void setTitle(String title) {
        this.title = title;
    }
}