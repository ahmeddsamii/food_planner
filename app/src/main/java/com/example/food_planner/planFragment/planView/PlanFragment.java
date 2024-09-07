package com.example.food_planner.planFragment.planView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.food_planner.R;
import com.example.food_planner.Repo.Repo;
import com.example.food_planner.helpers.networkUtils.NetworkUtils;
import com.example.food_planner.model.dtos.PlanDto;
import com.example.food_planner.planFragment.planPresenter.PlanPresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class PlanFragment extends Fragment implements OnPlansView , OnPlanMealDeleteListener{

    TextView tv_dateFormat;
    CardView cv_dateFormat;
    RecyclerView planMealsRecyclerView;
    PlansAdapter plansAdapter;
    int tempDay;
    FirebaseAuth user;
    PlanPresenter presenter;
    List<PlanDto> plans;
    LottieAnimationView lottieAnimationView;
    TextView empty_plan_text;
    private static final String TAG = "PlanFragment";


    public PlanFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_dateFormat = view.findViewById(R.id.tv_date_format);
        cv_dateFormat = view.findViewById(R.id.cv_date_format);
        planMealsRecyclerView = view.findViewById(R.id.rv_date_format);
        lottieAnimationView = view.findViewById(R.id.empty_plan_icon);
        empty_plan_text = view.findViewById(R.id.empty_plan_text);
        plansAdapter = new PlansAdapter(new ArrayList<>(), getContext(), this);
        planMealsRecyclerView.setAdapter(plansAdapter);
        planMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new PlanPresenter(Repo.getInstance(getContext()),this);
        user = FirebaseAuth.getInstance();

        cv_dateFormat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                tv_dateFormat.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                tempDay = dayOfMonth;

                                if (NetworkUtils.isInternetAvailable(getContext())){
                                    presenter.fetchDataForPlanMealsFromFirebase(user.getUid(), dayOfMonth);
                                }else {
                                    presenter.getMealsByDay(dayOfMonth);
                                }




                            }
                        },
                        year, month, day);

                datePickerDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button positiveButton = datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);

                        positiveButton.setTextColor(ContextCompat.getColor(getContext(), R.color.secondaryColor));
                        negativeButton.setTextColor(ContextCompat.getColor(getContext(), R.color.secondaryColor));
                    }
                });

                datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onPlansSuccess(List<PlanDto> planDtos) {
        if(planDtos == null || planDtos.isEmpty()){
            empty_plan_text.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            planMealsRecyclerView.setVisibility(View.GONE);
        }else{
            empty_plan_text.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.GONE);
            planMealsRecyclerView.setVisibility(View.VISIBLE);
            plansAdapter.setUpdateList(planDtos);
            plansAdapter.notifyDataSetChanged();
            planMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            Log.i(TAG, "onPlansSuccess: " + planDtos.size());
        }

    }

    @Override
    public void onPlansFailure(String errMessage) {
        Toast.makeText(getContext(), "Failed to load the list", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPlansSuccessFromFirebaseByDay(List<PlanDto> planDtos) {
        if(planDtos == null || planDtos.isEmpty()){
            empty_plan_text.setVisibility(View.VISIBLE);
            lottieAnimationView.setVisibility(View.VISIBLE);
            planMealsRecyclerView.setVisibility(View.GONE);
        }else{
            empty_plan_text.setVisibility(View.GONE);
            lottieAnimationView.setVisibility(View.GONE);
            planMealsRecyclerView.setVisibility(View.VISIBLE);
            plansAdapter.setUpdateList(planDtos);
            plansAdapter.notifyDataSetChanged();
            planMealsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            Log.i(TAG, "onPlansSuccess: " + planDtos.size());
        }

    }

    @Override
    public void onPlansFailureFromFirebaseByDay(String errMessage) {
        Log.i(TAG, "onPlansFailureFromFirebase: " + errMessage);
    }

    @Override
    public void onDeleteClick(PlanDto planDto) {
        if(isAdded() && getContext() != null) {
            if (NetworkUtils.isInternetAvailable(getContext()) && getContext() != null) {
                presenter.deletePlanFromFirebase(user.getUid(), planDto.getId(),tempDay);
                presenter.deletePlanMeal(planDto);
                presenter.getMealsByDay(tempDay);
            }else{
                Toast.makeText(getContext(), "You have to be connected to delete", Toast.LENGTH_SHORT).show();
            }
        }
    }
}