package com.example.finalp.home.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;
import com.example.finalp.model.data_models.Area;
import com.example.finalp.search.view.onClickAdapter;

import java.util.List;


    public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
        private final Context context;

        public void setAreaList(List<Area> areaList) {
            this.areaList = areaList;
        }

        List<Area> areaList;

        private onClickAdapter listener;
        private Handler handler = new Handler(Looper.getMainLooper());
        // private L
        private static final String TAG = "Recycle";

        //product
        public AreaAdapter(Context context, List<Area> areaList, onClickAdapter listener) {
            this.listener = listener;
            this.context = context;
            this.areaList = areaList;
        }


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View v = inflater.inflate(R.layout.single_country, parent, false);
            ViewHolder holder = new ViewHolder(v);
            Log.i(TAG, "onCreateViewHolder: ");
            return holder;

        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(areaList.get(position).getStrArea());
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onAreaClick(areaList.get(position),view);
                }
            });
        }

        @Override
        public int getItemCount() {
            return areaList.size();
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;

            public ConstraintLayout constraintLayout;
            public View layout;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                layout = itemView;
                title = itemView.findViewById(R.id.countryname);
                constraintLayout = itemView.findViewById(R.id.singlecount);
            }
        }

    }




