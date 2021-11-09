package com.example.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class subjectAdapter extends RecyclerView.Adapter <subjectAdapter.ItemViewHolder> {
    ArrayList<SubjectInfo> mySubjectList;
    ArrayList<SubjectInfo> checkedList = new ArrayList();
    private int ck = 0;

    public subjectAdapter(ArrayList<SubjectInfo> list){
        this.mySubjectList=list;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false);

        return new ItemViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull subjectAdapter.ItemViewHolder holder, int position) {
        SubjectInfo subjectInfo=mySubjectList.get(position);

        holder.tv_name_sub.setText(subjectInfo.getSubjectName());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(subjectInfo.checked);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                subjectInfo.checked = isChecked;
                checkedList.add(subjectInfo);
            }
        });

        if(ck==1){
            holder.checkBox.setVisibility(View.VISIBLE);
        }
        else
            holder.checkBox.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return mySubjectList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name_sub;
        CheckBox checkBox;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name_sub = itemView.findViewById(R.id.tv_name_sub);
            checkBox = itemView.findViewById(R.id.checkBox);

        }
    }

    public void updateCheckBox(int n){
        ck = n;
    }

    public ArrayList<SubjectInfo> getcheckedList() {
        return checkedList;
    }

}