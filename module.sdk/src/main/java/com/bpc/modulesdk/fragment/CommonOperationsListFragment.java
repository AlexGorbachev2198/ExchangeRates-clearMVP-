package com.bpc.modulesdk.fragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.solver.Goal;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bpc.modulesdk.errors.RestErrorHandler;
import com.bpc.modulesdk.fragment.dialogs.DatePickerDialogFragment;
import com.bpc.modulesdk.fragment.dialogs.OperationDetailsDialog;
import com.bpc.modulesdk.rest.dto.pojo.AccountOperationRecord;
import com.bpc.modulesdk.rest.dto.pojo.OperationRecord;
import com.bpc.modulesdk.rest.dto.response.HistoryResponse;
import com.bpc.modulesdk.ui.adapter.OperationRecordAdapter;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import ru.bpc.mobilebanksdk.R;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

public abstract class CommonOperationsListFragment extends Fragment {

    public static final String EXTRA_KEY_PERIOD = "period";
    public static final String EXTRA_KEY_ID = "number";
    public static final String EXTRA_KEY_FORCE_OPTIONS_MENU = "forceOptionsMenu";

    private static final int AUTO_LOAD_THRESHOLD = 4;

    private Date dateStartWithOutFilter;
    private Date dateEndWithOutFilter;
    private Date dateStart;
    private Date dateEnd;


    private RecyclerView recyclerView;
    private TextView textEmpty;
    private SwipeRefreshLayout refreshLayout;
    private FrameLayout viewCollapsing;
    private Button buttonDateStart;
    private Button buttonDateEnd;
    private ImageButton buttonCloseFilter;
    private View viewFilterParams;

    private LinearLayoutManager linearLayoutManager;
    private int page = 1;
    private String moneyResourceID;
    private Period period;
    private AtomicBoolean loading = new AtomicBoolean(true);
    private boolean noMoreData;
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private OperationRecordAdapter adapter;// = new OperationRecordAdapter();
    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    /**
     * Don't use directly
     */
    public abstract Observable<? extends HistoryResponse> getDataRequest(String number, int page, Date from, Date to);

    public abstract OperationRecordAdapter getAdapter();

    //public abstract OperationDetailsDialog getOperationDetailsDialog();

    private void initAdapter() {
        adapter = getAdapter();
    }

    private void setDateFilter(Period period) {
        if (period == null) {
            setDateFilter(null, null);
            return;
        }
        Calendar calendar = Calendar.getInstance();
        if (period == Period.DAY) ;//calendar.add(Calendar.DAY_OF_MONTH, -1);
        else if (period == Period.WEEK)
            calendar.add(Calendar.DAY_OF_MONTH, -7);
        else if (period == Period.MONTH)
            calendar.add(Calendar.MONTH, -1);
        else if (period == Period.YEAR)
            calendar.add(Calendar.YEAR, -1);

        setDateFilter(calendar.getTime(), null);
    }

    private void setDateFilter(Date dateStart, Date dateEnd) {
        Date now = new Date();

        if (dateEnd != null && dateEnd.after(now))
            dateEnd = now;
        if (dateStart != null && dateStart.after(now))
            dateStart = now;
        if (dateEnd != null && dateStart != null && dateEnd.before(dateStart))
            dateStart = dateEnd;

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;

        String chooseStr = getString(R.string.choose);

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
        buttonDateStart.setText(dateStart != null ? dateFormat.format(dateStart) : chooseStr);
        buttonDateEnd.setText(dateEnd != null ? dateFormat.format(dateEnd) : chooseStr);

        refresh();
    }

    /**
     * Set view that will collapsing, when recycler scroll down
     *
     * @param v any view
     */
    public void setCollapsingView(View v) {
        viewCollapsing.removeAllViews();
        viewCollapsing.addView(v);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof SwipeRefreshLayout.OnRefreshListener))
            throw new IllegalStateException("Parent Activity must implement " + SwipeRefreshLayout.OnRefreshListener.class.getSimpleName() + " interface");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_common_operations_list, container, false);
        bindView(view);
        return view;
    }

    private void bindView(View view) {
        refreshListener = (SwipeRefreshLayout.OnRefreshListener) getActivity();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        textEmpty = (TextView) view.findViewById(R.id.text_empty);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        viewCollapsing = (FrameLayout) view.findViewById(R.id.view_collapsing);
        buttonDateStart = (Button) view.findViewById(R.id.button_date_start);
        buttonDateEnd = (Button) view.findViewById(R.id.button_date_end);
        buttonCloseFilter = (ImageButton) view.findViewById(R.id.button_close_filter);
        viewFilterParams = view.findViewById(R.id.view_filter_params);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();

        setHasOptionsMenu(getActivity().getIntent().getBooleanExtra(EXTRA_KEY_FORCE_OPTIONS_MENU, true));

        refreshLayout.setColorSchemeResources(R.color.swipe_refresh_color_scheme1, R.color.swipe_refresh_color_scheme2,
                R.color.swipe_refresh_color_scheme3, R.color.swipe_refresh_color_scheme4);
        refreshLayout.setOnRefreshListener(refreshListener);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setOnRecyclerItemClickListener((view1, item) -> showOperationDetails(item));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (!noMoreData && dy > 0) {
                    if (linearLayoutManager.findLastVisibleItemPosition() >= linearLayoutManager.getItemCount() - AUTO_LOAD_THRESHOLD) {
                        executeRequestWithLock();
                    }
                }
            }
        });
        buttonDateStart.setOnClickListener(v -> showDataPickerForStart());
        buttonDateEnd.setOnClickListener(v -> showDataPickerForEnd());
        buttonCloseFilter.setOnClickListener(v -> closeFilterView());

        moneyResourceID = getActivity().getIntent().getStringExtra(EXTRA_KEY_ID);
        period = (Period) getActivity().getIntent().getSerializableExtra(EXTRA_KEY_PERIOD);
        setDateFilter(period);
        dateStartWithOutFilter = dateStart;
        dateEndWithOutFilter = dateEnd;
    }

    public void refresh() {
        page = 1;
        loading.set(false);
        executeRequestWithLock();
    }

    private void showOperationDetails(OperationRecord operation) {
        if (operation == null)
            return;
        Activity activity = getActivity();
        if (activity != null) {
            //final DialogFragment details = getOperationDetailsDialog(); /*OperationDetailsDialog.newInstance(moneyResourceID, operation);*/

            final DialogFragment details = OperationDetailsDialog.newInstance(moneyResourceID, operation);

            try {
                details.show(getFragmentManager(), "details");
                //Exception: java.lang.IllegalStateException: Can not perform this action after onSaveInstanceState
                //Возможно проблема возникает из-за фрагмент менеджера. Можно попробовать использочать getChildFragmentManager()
                //Либо перенести отображение диалога в отдельную активность
            } catch (IllegalStateException e) {
                //Не открылся и хрен с ним.
            }
        }
    }



    private void executeRequestWithLock() {
        if (!loading.compareAndSet(false, true))
            return;
        Activity activity = getActivity();
        if (activity == null)
            return;

        //SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_SERVER, Locale.getDefault());
        //String startDate = dateStart != null ? dateFormat.format(dateStart) : null;
        //String endDate = dateEnd != null ? dateFormat.format(dateEnd) : null;

        //compositeSubscription.add(getDataRequest(moneyResourceID, page, startDate, endDate)
        compositeSubscription.add(getDataRequest(moneyResourceID, page, dateStart, dateEnd)
                .doOnSubscribe(this::doOnStartLoading)
                .doAfterTerminate(this::doOnStopLoading)
                .subscribe(this::handleResponse, t -> RestErrorHandler.handle(recyclerView, t)));

    }

    private void doOnStartLoading() {
        showProgress(true);
    }

    private void doOnStopLoading() {
        showProgress(false);
        refreshLayout.setRefreshing(false);
        loading.set(false);
    }

    private void handleResponse(HistoryResponse response) {
        if (response.isSuccess()) {
            OperationRecordAdapter adapter = (OperationRecordAdapter) recyclerView.getAdapter();
            if (response.getHistory() != null) {
                if (page == 1)
                    adapter.clean();
                adapter.addAll((List<OperationRecord>) response.getHistory());
                page++;
            } else {
                noMoreData = true;
            }

            showEmptyView(response.getHistory().isEmpty());
        } else {
            RestErrorHandler.handle(recyclerView, response);
            noMoreData = true;
        }
    }

    private void showEmptyView(boolean isShow) {
        if ((recyclerView.getVisibility() == View.VISIBLE) == isShow) {
            recyclerView.setVisibility(isShow ? View.GONE : View.VISIBLE);
            textEmpty.setVisibility(isShow ? View.VISIBLE : View.GONE);
        }
    }

    private void showProgress(boolean isShow) {
        ((OperationRecordAdapter) recyclerView.getAdapter()).showProgressFooter(isShow);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //Have alternative menu for GBP in app module
        inflater.inflate(R.menu.operations_list_menu, menu);
        MenuItem item = menu.findItem(R.id.filter);
        item.setVisible(false);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filter) {
            if (viewFilterParams.getVisibility() != View.VISIBLE)
                viewFilterParams.setVisibility(View.VISIBLE);
            return true;
        } else
            return super.onOptionsItemSelected(item);
    }

    private void showDataPickerForStart() {
        Activity activity = getActivity();
        if (activity == null)
            return;
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                setDateFilter(calendar.getTime(), dateEnd);
            }
        });
        datePickerDialogFragment.setDate(dateStart);
        datePickerDialogFragment.show(activity.getFragmentManager(), null);
    }

    private void showDataPickerForEnd() {
        Activity activity = getActivity();
        if (activity == null)
            return;
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.setListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth);
                setDateFilter(dateStart, calendar.getTime());
            }
        });
        datePickerDialogFragment.setDate(dateEnd);
        datePickerDialogFragment.show(activity.getFragmentManager(), null);
    }

    private void closeFilterView() {
        viewFilterParams.setVisibility(View.GONE);
        //Check that the dates did not change after opening the fragment
        if (isEquals(dateStartWithOutFilter, dateStart) && isEquals(dateEndWithOutFilter, dateEnd))
            return;
        dateStart = null;
        dateEnd = null;
        setDateFilter(period);
    }

    private boolean isEquals(Date first, Date second) {
        //Log.e("OperationsList", "first = " + (first != null ? first.getTime() : "null"));
        //Log.e("OperationsList", "second = " + (second != null ? second.getTime() : "null"));
        if (first == second)
            return true;
        return first != null && first.equals(second);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    public enum Period {
        DAY(1), WEEK(2), MONTH(3), YEAR(4);

        int code;

        Period(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }
    }
}
