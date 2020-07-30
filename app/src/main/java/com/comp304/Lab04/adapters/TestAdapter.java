package com.comp304.Lab04.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.comp304.Lab04.models.Patient;
import com.comp304.Lab04.models.Test;
import com.comp304.Lab04.views.R;

import java.util.ArrayList;
import java.util.List;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder> {
    private List<Test> tests = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_cardview, parent, false);
        return new TestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test currentTest = tests.get(position);
        Log.d("TestList:", "currentTest.getTestId(): " + currentTest.getTestId());
        Log.d("TestList:", "tvTestId: " + holder.tvTestId);//
       holder.tvTestId.setText("Test Id: "+ String.valueOf(currentTest.getTestId()));
        holder.tvPatientId.setText("Patient Id: "+ String.valueOf(currentTest.getPatientId())); //
        holder.tvNurseId.setText("Nurse Id: " + String.valueOf(currentTest.getNurseId()));

        holder.tvBPL.setText("BPL(mmHg): " +String.valueOf(currentTest.getBPL()) );
        holder.tvBPH.setText("BPH(mmHg): " +String.valueOf(currentTest.getBPH()) );
        holder.tvTemperature.setText("Temperature(C): " +String.valueOf(currentTest.getTemperature()) );
        holder.tvWeight.setText("Weight(kg): " +String.valueOf(currentTest.getWeight()) );
        holder.tvHeight.setText("Height(cm): " +String.valueOf(currentTest.getHeight()) );

    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
        notifyDataSetChanged();
    }

    public Test getTestAct(int position) {
        return tests.get(position);
    }


    class TestHolder extends RecyclerView.ViewHolder {
        private TextView tvTestId;
        private TextView tvPatientId;
        private TextView tvNurseId;
        private TextView tvBPL;
        private TextView tvBPH;
        private TextView tvTemperature;
        private TextView tvWeight;
        private TextView tvHeight;

        public TestHolder(@NonNull View itemView) {
            super(itemView);
            tvTestId = itemView.findViewById(R.id.tvTestId);
            tvPatientId = itemView.findViewById(R.id.tvPatientId);
            tvNurseId = itemView.findViewById(R.id.tvNurseId);
            tvBPL = itemView.findViewById(R.id.tvBPL);
            tvBPH = itemView.findViewById(R.id.tvBPH);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            tvWeight = itemView.findViewById(R.id.tvWeight);
            tvHeight = itemView.findViewById(R.id.tvHeight);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(tests.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Test test);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
