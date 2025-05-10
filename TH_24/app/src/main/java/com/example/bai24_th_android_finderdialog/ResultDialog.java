package com.example.bai24_th_android_finderdialog;



import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class ResultDialog extends Dialog {

    public ResultDialog(@NonNull Context context, List<File> resultList, OnFileSelectListener listener) {
        super(context);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        LinearLayout linearLayout = new LinearLayout(this.getContext());
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int p = convertToPixels(12);
        linearLayout.setPadding(p, p, p, p);
        linearLayout.setGravity(Gravity.CENTER);

        TextView textView = new TextView(this.getContext());
        textView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setText("~ Search Result ~");

        RecyclerView recyclerView = new RecyclerView(this.getContext());

        linearLayout.addView(textView);
        linearLayout.addView(recyclerView);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this.getContext(), this, listener, resultList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        this.setContentView(linearLayout);
        this.setCancelable(true);
    }

    private int convertToPixels(int dp) {
        float scale = this.getContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private int screenWidth() {
        return this.getContext().getResources().getDisplayMetrics().widthPixels;
    }

    private class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        private Context context;
        private List<File> files;
        private OnFileSelectListener listener;
        private Dialog dialog;

        public RecyclerViewAdapter(Context context, Dialog dialog, OnFileSelectListener listener, List<File> files) {
            this.context = context;
            this.files = files;
            this.listener = listener;
            this.dialog = dialog;
        }

        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            TextView txtName = new TextView(context);
            TextView txtPath = new TextView(context);
            txtPath.setTypeface(txtPath.getTypeface(), Typeface.ITALIC);
            txtPath.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);

            linearLayout.addView(txtName);
            linearLayout.addView(txtPath);

            return new RecyclerViewAdapter.ViewHolder(linearLayout);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public LinearLayout linearLayout;
            public TextView textViewFileName;
            public TextView textViewPath;

            public ViewHolder(View itemLayoutView) {
                super(itemLayoutView);
                this.linearLayout = (LinearLayout) itemLayoutView;
                this.textViewFileName = (TextView) linearLayout.getChildAt(0);
                this.textViewPath = (TextView) linearLayout.getChildAt(1);
            }
        }

        @Override
        public int getItemCount() {
            return files.size();
        }

        @Override
        public void onBindViewHolder(RecyclerViewAdapter.ViewHolder viewHolder, final int position) {
            final File selectedFile = files.get(position);
            final String path = selectedFile.getAbsolutePath();
            String[] split = path.split("/");
            final String name = split[split.length - 1];

            viewHolder.textViewFileName.setText(name);
            viewHolder.textViewPath.setText(path);

            viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    listener.onSelect(selectedFile);
                }
            });
        }
    }
}
