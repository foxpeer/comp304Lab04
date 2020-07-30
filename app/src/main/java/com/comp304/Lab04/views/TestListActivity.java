package com.comp304.Lab04.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.comp304.Lab04.adapters.TestAdapter;
import com.comp304.Lab04.models.Test;
import com.comp304.Lab04.viewmodels.TestViewModel;

import java.util.List;

public class TestListActivity extends AppCompatActivity {
    public static final int ADD_TEST_REQUEST = 1;
    public static final int UPDATE_TEST_REQUEST =2;

    private TestViewModel testViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testlist);
        setTitle("All Tests");

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final TestAdapter adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);

        testViewModel.getAllTests().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                //update RecycleView
                //Toast.makeText(TestListActivity.this, "onChanged", Toast.LENGTH_SHORT).show();
                adapter.setTests(tests);
            }
        });

        //swipe left or right to delete patient
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                testViewModel.delete(adapter.getTestAct(viewHolder.getAdapterPosition()));
                Toast.makeText(TestListActivity.this, "Test deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);


        adapter.setOnItemClickListener(new TestAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Test test) {
                Intent intent = new Intent(TestListActivity.this, TestUpdateActivity.class);
                // send Test Id back to TestUpdateActivity for update
                intent.putExtra(TestUpdateActivity.EXTRA_TESTID, test.getTestId());
                startActivityForResult(intent, UPDATE_TEST_REQUEST);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ADD_TEST_REQUEST && resultCode ==RESULT_OK){
            int testId = Integer.parseInt(data.getStringExtra(TestAddActivity.EXTRA_TESTID));
            int patientId = Integer.parseInt(data.getStringExtra(TestAddActivity.EXTRA_PATIENTID));
            int nurseId = Integer.parseInt(data.getStringExtra(TestAddActivity.EXTRA_NURSEID));

            int BPL = Integer.parseInt(data.getStringExtra(TestAddActivity.EXTRA_BPL));
            int BPH = Integer.parseInt(data.getStringExtra(TestAddActivity.EXTRA_BPH));
            double temperature = Double.parseDouble(data.getStringExtra(TestAddActivity.EXTRA_TEMPERATURE));
            double weight = Double.parseDouble(data.getStringExtra(TestAddActivity.EXTRA_WEIGHT));
            double height = Double.parseDouble(data.getStringExtra(TestAddActivity.EXTRA_HEIGHT));

            Test test = new Test(testId, patientId, nurseId, BPL, BPH, temperature, weight, height );
            testViewModel.insert(test);

            Log.d("TestAddActivity:", "saved testId: "+ testId);

            Toast.makeText(this, "Test saved)", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Test NOT saved)", Toast.LENGTH_SHORT).show();
        }
    } //end of onActivityResult

    //add delete menu at top of testlist
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.testlist_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_tests:
               testViewModel.deleteAllTests();
                Toast.makeText(this, "All tests deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void FloatBtnAddTestClick(View view) {
        Intent intent = new Intent(this, TestAddActivity.class);
        startActivityForResult(intent, ADD_TEST_REQUEST);
    }
}