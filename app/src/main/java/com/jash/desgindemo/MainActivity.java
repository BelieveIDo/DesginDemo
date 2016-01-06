package com.jash.desgindemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SwipeDismissBehavior.OnDismissListener, View.OnClickListener {

    private CoordinatorLayout coordinator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView text = (TextView) findViewById(R.id.main_text);
        text.setOnClickListener(this);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) text.getLayoutParams();
        SwipeDismissBehavior behavior = new SwipeDismissBehavior();
        behavior.setListener(this);
        params.setBehavior(behavior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //把Toolbar当做ActionBar使用()
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onDismiss(final View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        parent.removeView(view);
        ViewCompat.setAlpha(view, 1);
//        ViewCompat.setTranslationX(view, 0);
//        Toast.makeText(this, "删除了一个TextView", Toast.LENGTH_SHORT).show();
        //如果View是CoordinatorLayout的话自带滑动删除
        Snackbar.make(coordinator, "删除了一个TextView", Snackbar.LENGTH_LONG)
                .setAction("撤销", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        coordinator.addView(view);
                    }
                })
                .show();
    }

    @Override
    public void onDragStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, CoordinatorActivity.class));
    }
}
