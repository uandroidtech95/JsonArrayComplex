package com.example.utsav.jsonarraycomplex.adapter;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utsav.jsonarraycomplex.R;
import com.example.utsav.jsonarraycomplex.model.UserResponse;

import java.util.ArrayList;

/**
 * Created by utsav on 14-06-2017.
 */

public class UserDataAdapter extends RecyclerView.Adapter<UserDataAdapter.UserDataHolder> {

    ArrayList<UserResponse> userResponseArrayList = new ArrayList<>();

    public UserDataAdapter(ArrayList<UserResponse> userResponseArrayList) {
        this.userResponseArrayList = userResponseArrayList;
    }

    @Override
    public UserDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user_data, parent, false);
        return new UserDataHolder(view);
    }

    @Override
    public void onBindViewHolder(UserDataHolder holder, int position) {
        UserResponse userResponse = userResponseArrayList.get(position);
        holder.tvName.setText(userResponse.getName());
        holder.tvCity.setText(userResponse.getAddress().getCity());
    }

    @Override
    public int getItemCount() {
        return userResponseArrayList.size();
    }

    public class UserDataHolder extends RecyclerView.ViewHolder {
        AppCompatTextView tvName, tvCity;

        public UserDataHolder(View itemView) {
            super(itemView);
            tvCity = (AppCompatTextView) itemView.findViewById(R.id.tv_city);
            tvName = (AppCompatTextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
