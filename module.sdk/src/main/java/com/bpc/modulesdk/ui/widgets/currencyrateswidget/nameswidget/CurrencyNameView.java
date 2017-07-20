package com.bpc.modulesdk.ui.widgets.currencyrateswidget.nameswidget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bpc.modulesdk.ui.widgets.currencyrateswidget.observerinterfaces.Observer;
import com.bpc.modulesdk.ui.widgets.currencyrateswidget.observerinterfaces.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ru.bpc.mobilebanksdk.R;

/**
 * Created by Smolyaninov on 03.02.2017.
 */

public class CurrencyNameView extends FrameLayout implements Subject {

    private CurrencyNameView sibling;
    private int startingPosition;
    private List<String> names;
    private int position;

    private String currentItem;

    private List<Observer> observers;
    private String ratesCouple;

    private RecyclerView nameRecycler;
    private ImageView scrollUp;
    private ImageView scrollDown;

    private RecyclerView.OnScrollListener onScrollListener;
    private LinearLayoutManager layoutManager;

    public CurrencyNameView(Context context) {
        super(context);
    }

    public CurrencyNameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CurrencyNameView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(o);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(ratesCouple);
        }
    }

    public void init(CurrencyNameView sibling, int startingPosition, Set<String> names, int position) {
        observers = new ArrayList<>();
        this.sibling = sibling;
        this.startingPosition = startingPosition;
        this.names = new ArrayList<>();
        this.names.addAll(names);
        this.position = position;
        init();
    }


    public void swap() {
        if (nameRecycler.getScrollState() == RecyclerView.SCROLL_STATE_IDLE && sibling.getScrollState() == 0) {
            int myCurrrentPosition = layoutManager.findFirstVisibleItemPosition();
            int siblingCurrentPosition = sibling.getLayoutManager().findFirstVisibleItemPosition();

            String curr = getCurrentItem();
            String siblCurr = sibling.getCurrentItem();

            layoutManager.scrollToPosition(siblingCurrentPosition);
            sibling.getLayoutManager().scrollToPosition(myCurrrentPosition);

            currentItem = siblCurr;
            sibling.setCurrentItem(curr);

            ratesCouple = buildRatesCouple();
            notifyObservers();
        }
    }

    public int getScrollState() {
        return nameRecycler.getScrollState();
    }

    public int getPosition() {
        return position;
    }

    public String getCurrentItem() {
        return currentItem;
    }

    public void setCurrentItem(String currentItem) {
        this.currentItem = currentItem;
    }

    public RecyclerView.OnScrollListener getOnScrollListener() {
        if (onScrollListener == null) {
            onScrollListener = new NameOnScrollListener(layoutManager);
        }
        return onScrollListener;
    }

    public LinearLayoutManager getLayoutManager() {
        if (layoutManager == null) {
            layoutManager = new SmoothScrollLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        }
        return layoutManager;
    }

    public void setLayoutManager(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    public String getRatesCouple() {
        return buildRatesCouple();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_currency_name, this);
        nameRecycler = (RecyclerView) findViewById(R.id.name_recycler);

        scrollUp = (ImageView) findViewById(R.id.button_scroll_up);
        scrollDown = (ImageView) findViewById(R.id.button_scroll_down);


        layoutManager = getLayoutManager();
        int pos = (Integer.MAX_VALUE / 2) + startingPosition;
        layoutManager.scrollToPosition(pos);

        if (names != null) nameRecycler.setAdapter(new NamesAdapter(names));

        NamesAdapter adapter = ((NamesAdapter) nameRecycler.getAdapter());
        currentItem = adapter.getItem(pos % names.size());

        nameRecycler.setLayoutManager(layoutManager);

        onScrollListener = getOnScrollListener();
        nameRecycler.addOnScrollListener(onScrollListener);
    }


    private String buildRatesCouple() {

        return position > sibling.getPosition() ?
                sibling.getCurrentItem() + "/" + currentItem :
                currentItem + "/" + sibling.getCurrentItem();
    }

    private class NameOnScrollListener extends RecyclerView.OnScrollListener {

        private final int UP = 1;
        private final int DOWN = -1;

        private int scrollDirection;

        private int scrollCallCounter = 0;

        private LinearLayoutManager lm;

        public NameOnScrollListener(LinearLayoutManager lm) {
            this.lm = lm;
            setScrollButtonListeners();
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                if (scrollDirection > 0) {
                    moveUp(recyclerView);
                } else {
                    moveDown(recyclerView);
                }
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            scrollDirection = dy;
        }

        private void setScrollButtonListeners() {
            scrollUp.setOnClickListener(view -> moveUpOnClick());
            scrollDown.setOnClickListener(view -> moveDownOnClick());
        }

        private void moveUpOnClick() {
            int lastPosition = layoutManager.findLastVisibleItemPosition() + 1;
            move(lastPosition, UP, nameRecycler);
        }

        private void moveDownOnClick() {
            int firstPosition = layoutManager.findFirstVisibleItemPosition() - 1;
            move(firstPosition, DOWN, nameRecycler);
        }

        private void moveUp(RecyclerView recyclerView) {
            int lastPosition = lm.findLastVisibleItemPosition();
            move(lastPosition, UP, recyclerView);
        }

        private void moveDown(RecyclerView recyclerView) {
            int firstPosition = lm.findFirstVisibleItemPosition();
            move(firstPosition, DOWN, recyclerView);
        }

        public void move(int position, int direction, RecyclerView recyclerView) {
            NamesAdapter adapter = ((NamesAdapter) recyclerView.getAdapter());
            String item = adapter.getItem(position % names.size());
            if (!sibling.getCurrentItem().equals(item)) {
                lm.smoothScrollToPosition(recyclerView, new RecyclerView.State(), position);
                currentItem = item;
            } else {
                lm.smoothScrollToPosition(recyclerView, new RecyclerView.State(), position + direction);
                currentItem = adapter.getItem((position + direction) % names.size());
            }
            if (scrollCallCounter == 0) scrollCallCounter++;
            else {
                ratesCouple = buildRatesCouple();
                notifyObservers();
                scrollCallCounter = 0;
            }
        }
    }


    private class SmoothScrollLayoutManager extends LinearLayoutManager {

        public SmoothScrollLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
            LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getContext()) {

                private static final float SPEED = 300f;

                @Override
                protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                    return SPEED / displayMetrics.densityDpi;
                }

            };
            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }
}
